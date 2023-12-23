package com.ozyegin.cs393.services;

import com.ozyegin.cs393.dto.AccountDTO;
import com.ozyegin.cs393.dto.TransactionDTO;
import com.ozyegin.cs393.entities.Account;
import com.ozyegin.cs393.entities.Currency;
import com.ozyegin.cs393.entities.Transaction;
import com.ozyegin.cs393.mappers.AccountMapper;
import com.ozyegin.cs393.mappers.TransactionMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class TransactionServiceTest {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountMapper accountMapper;

    @Test
    @Transactional
    public void testCreateTransaction() {
        Transaction transaction =
                new Transaction(null, null,
                        null, LocalDateTime.now(), null, 100);
        TransactionDTO createdTransaction = transactionService.createTransaction(transactionMapper.transactionToTransactionDto(transaction));

        assertNotNull(createdTransaction);
        assertNotNull(createdTransaction.getId());
        assertEquals(100, createdTransaction.getAmount());
    }

    @Test
    @Transactional
    public void testGetAllTransactions() {
        Transaction transaction1 =
                new Transaction(null, null,
                        null, LocalDateTime.now(), null, 100);
        Transaction transaction2 =
                new Transaction(null, null,
                        null, LocalDateTime.now(), null, 200);

        TransactionDTO createdTransaction1 = transactionService.createTransaction(transactionMapper.transactionToTransactionDto(transaction1));
        TransactionDTO createdTransaction2 = transactionService.createTransaction(transactionMapper.transactionToTransactionDto(transaction2));


        List<TransactionDTO> createdTransactions = transactionService.getAllTransactions();

        assertEquals(2, createdTransactions.size());
        assertEquals(100, createdTransactions.get(0).getAmount());
        assertEquals(200, createdTransactions.get(1).getAmount());
    }

    @Test
    @Transactional
    public void testUpdateTransaction() {
        Transaction transaction =
                new Transaction(null, null,
                        null, LocalDateTime.now(), null, 100);
        TransactionDTO createdTransaction = transactionService.createTransaction(transactionMapper.transactionToTransactionDto(transaction));
        createdTransaction.setAmount(200);
        TransactionDTO updatedTransaction = transactionService.updateTransaction(createdTransaction);

        assertNotNull(updatedTransaction);
        assertEquals(createdTransaction.getId(), updatedTransaction.getId());
        assertEquals(200, updatedTransaction.getAmount());
    }

    @Test
    @Transactional
    public void testDeleteTransaction() {
        Transaction transaction1 =
                new Transaction(null, null,
                        null, LocalDateTime.now(), null, 100);
        Transaction transaction2 =
                new Transaction(null, null,
                        null, LocalDateTime.now(), null, 200);

        TransactionDTO createdTransaction1 = transactionService.createTransaction(transactionMapper.transactionToTransactionDto(transaction1));
        TransactionDTO createdTransaction2 = transactionService.createTransaction(transactionMapper.transactionToTransactionDto(transaction2));

        transactionService.deleteTransactionById(createdTransaction1.getId());

        assertEquals(1, transactionService.getAllTransactions().size());
    }

    @Test
    @Transactional
    public void testGetSendingTransactionsOfAccount(){
        Currency currency = new Currency(null, "test", '$', 1);

        Account account1 = new Account(
                null, "testName1", currency, null, 100,
                LocalDate.now(), null, null, null, null);
        Account account2 = new Account(
                null, "testName2", currency, null, 200,
                LocalDate.now(), null, null, null, null);

        AccountDTO createdAccount1 = accountService.createAccount(accountMapper.accountToAccountDto(account1));
        AccountDTO createdAccount2 = accountService.createAccount(accountMapper.accountToAccountDto(account2));

        accountService.transferMoney(createdAccount1, createdAccount2, 50.0);
        accountService.transferMoney(createdAccount1, createdAccount2, 10.0);
        accountService.transferMoney(createdAccount2, createdAccount1, 15.0);

        List<TransactionDTO> transactionDTOS = transactionService.getSendingTransactionsOfAccount(createdAccount1);
        assertEquals(transactionDTOS.size(), 2);
        assertEquals(transactionDTOS.get(1).getAmount(), 10.0);
    }

    @Test
    @Transactional
    public void testGetReceivingTransactionsOfAccount(){
        Currency currency = new Currency(null, "test", '$', 1);

        Account account1 = new Account(
                null, "testName1", currency, null, 100,
                LocalDate.now(), null, null, null, null);
        Account account2 = new Account(
                null, "testName2", currency, null, 200,
                LocalDate.now(), null, null, null, null);

        AccountDTO createdAccount1 = accountService.createAccount(accountMapper.accountToAccountDto(account1));
        AccountDTO createdAccount2 = accountService.createAccount(accountMapper.accountToAccountDto(account2));

        accountService.transferMoney(createdAccount1, createdAccount2, 50.0);
        accountService.transferMoney(createdAccount1, createdAccount2, 10.0);
        accountService.transferMoney(createdAccount2, createdAccount1, 15.0);

        List<TransactionDTO> transactionDTOS = transactionService.getSendingTransactionsOfAccount(createdAccount2);
        assertEquals(transactionDTOS.size(), 2);
        assertEquals(transactionDTOS.get(1).getAmount(), 10.0);
    }
}

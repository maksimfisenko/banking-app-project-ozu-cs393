package com.ozyegin.cs393.services;

import com.ozyegin.cs393.dto.TransactionDTO;
import com.ozyegin.cs393.entities.Transaction;
import com.ozyegin.cs393.mappers.TransactionMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
}

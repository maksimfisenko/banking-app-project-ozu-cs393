package com.ozyegin.cs393.services;

import com.ozyegin.cs393.dto.AccountDTO;
import com.ozyegin.cs393.dto.TransactionDTO;
import com.ozyegin.cs393.entities.Account;
import com.ozyegin.cs393.entities.Transaction;
import com.ozyegin.cs393.mappers.AccountMapper;
import com.ozyegin.cs393.mappers.TransactionMapper;
import com.ozyegin.cs393.repositories.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private AccountMapper accountMapper;

    // CRUD Operations

    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = transactionMapper.transactionDtoToTransaction(transactionDTO);
        transaction = transactionRepository.save(transaction);
        return transactionMapper.transactionToTransactionDto(transaction);
    }

    public List<TransactionDTO> getAllTransactions() {
        List <Transaction> transactions = transactionRepository.findAll();
        return transactionMapper.TransactionsToTransactionDtos(transactions);
    }

    public TransactionDTO updateTransaction(TransactionDTO updatedTransactionDTO) {
        Transaction transaction = transactionMapper.transactionDtoToTransaction(updatedTransactionDTO);
        transaction = transactionRepository.save(transaction);
        return transactionMapper.transactionToTransactionDto(transaction);
    }

    public void deleteTransactionById(Long id) {
        transactionRepository.deleteById(id);
    }

    public List<TransactionDTO> getSendingTransactionsOfAccount(AccountDTO accountDTO){
        Account account = accountMapper.accountDtoToAccount(accountDTO);
        List<Transaction> transactions = transactionRepository.findBySendingAccount(account);
        return transactionMapper.TransactionsToTransactionDtos(transactions);
    }
    public List<TransactionDTO> getReceivingTransactionsOfAccount(AccountDTO accountDTO){
        Account account = accountMapper.accountDtoToAccount(accountDTO);
        List<Transaction> transactions = transactionRepository.findByReceivingAccount(account);
        return transactionMapper.TransactionsToTransactionDtos(transactions);
    }
}

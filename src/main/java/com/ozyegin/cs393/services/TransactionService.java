package com.ozyegin.cs393.services;

import com.ozyegin.cs393.entities.Account;
import com.ozyegin.cs393.entities.Transaction;
import com.ozyegin.cs393.repositories.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction updateTransaction(Transaction updatedTransaction) {

        Long id = updatedTransaction.getId();

        Transaction transaction = transactionRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Transaction with id " + id + " not found"));

        transaction.setSendingAccount(updatedTransaction.getSendingAccount());
        transaction.setReceivingAccount(updatedTransaction.getReceivingAccount());
        transaction.setTimeOfTransaction(updatedTransaction.getTimeOfTransaction());
        transaction.setCurrency(updatedTransaction.getCurrency());
        transaction.setAmount(updatedTransaction.getAmount());

        return transactionRepository.save(updatedTransaction);
    }

    public void deleteTransactionById(Long id) {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> getSendingTransactionsOfAccount(Account account){
        return transactionRepository.findBySendingAccount(account);
    }
    public List<Transaction> getReceivingTransactionsOfAccount(Account account){
        return transactionRepository.findByReceivingAccount(account);
    }
}

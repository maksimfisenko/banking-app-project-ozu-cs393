package com.ozyegin.cs393.services;

import com.ozyegin.cs393.entities.Transaction;
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

//    @Test
//    @Transactional
//    public void testCreateTransaction() {
//        Transaction createdTransaction = transactionService.createTransaction(
//                new Transaction(null, null,
//                        null, LocalDateTime.now(), null, 100));
//
//        assertNotNull(createdTransaction);
//        assertNotNull(createdTransaction.getId());
//        assertEquals(100, createdTransaction.getAmount());
//    }
//
//    @Test
//    @Transactional
//    public void testGetAllTransactions() {
//        Transaction createdTransaction1 = transactionService.createTransaction(
//                new Transaction(null, null,
//                        null, LocalDateTime.now(), null, 100));
//        Transaction createdTransaction2 = transactionService.createTransaction(
//                new Transaction(null, null,
//                        null, LocalDateTime.now(), null, 200));
//
//        List<Transaction> createdTransactions = transactionService.getAllTransactions();
//
//        assertEquals(2, createdTransactions.size());
//        assertEquals(100, createdTransactions.get(0).getAmount());
//        assertEquals(200, createdTransactions.get(1).getAmount());
//    }
//
//    @Test
//    @Transactional
//    public void testUpdateTransaction() {
//        Transaction createdTransaction = transactionService.createTransaction(
//                new Transaction(null, null,
//                        null, LocalDateTime.now(), null, 100));
//
//        createdTransaction.setAmount(200);
//        Transaction updatedTransaction = transactionService.updateTransaction(createdTransaction);
//
//        assertNotNull(updatedTransaction);
//        assertEquals(createdTransaction.getId(), updatedTransaction.getId());
//        assertEquals(200, updatedTransaction.getAmount());
//    }
//
//    @Test
//    @Transactional
//    public void testDeleteTransaction() {
//        Transaction createdTransaction1 = transactionService.createTransaction(
//                new Transaction(null, null,
//                        null, LocalDateTime.now(), null, 100));
//        Transaction createdTransaction2 = transactionService.createTransaction(
//                new Transaction(null, null,
//                        null, LocalDateTime.now(), null, 200));
//
//        transactionService.deleteTransactionById(createdTransaction1.getId());
//
//        assertEquals(1, transactionService.getAllTransactions().size());
//    }
}

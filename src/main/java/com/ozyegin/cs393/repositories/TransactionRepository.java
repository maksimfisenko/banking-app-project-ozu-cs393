package com.ozyegin.cs393.repositories;

import com.ozyegin.cs393.entities.Account;
import com.ozyegin.cs393.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySendingAccount(Account sendingAccount);
    List<Transaction> findByReceivingAccount(Account receivingAccount);
}

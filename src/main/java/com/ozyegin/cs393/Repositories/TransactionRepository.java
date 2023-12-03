package com.ozyegin.cs393.Repositories;

import com.ozyegin.cs393.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Some queries
}

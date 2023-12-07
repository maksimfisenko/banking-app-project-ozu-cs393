package com.ozyegin.cs393.repositories;

import com.ozyegin.cs393.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Some queries
}
package com.ozyegin.cs393.Repositories;

import com.ozyegin.cs393.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Some queries
}

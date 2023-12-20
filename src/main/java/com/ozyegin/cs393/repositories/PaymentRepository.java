package com.ozyegin.cs393.repositories;

import com.ozyegin.cs393.entities.DebitCard;
import com.ozyegin.cs393.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findBySendingCard(DebitCard sendingCard);
}

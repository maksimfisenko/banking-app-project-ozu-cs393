package com.ozyegin.cs393.services;


import com.ozyegin.cs393.entities.Payment;
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
public class PaymentServiceTest {
    @Autowired
    private PaymentService paymentService;

    @Test
    @Transactional
    public void testCreatePayment() {
        Payment createdPayment = paymentService.createPayment(new Payment(null,
                null, null, LocalDateTime.now(), null, 100));

        assertNotNull(createdPayment);
        assertNotNull(createdPayment.getId());
        assertEquals(100, createdPayment.getAmount());
    }

    @Test
    @Transactional
    public void testGetAllPayments() {
        Payment createdPayment1 = paymentService.createPayment(new Payment(null,
                null, null, LocalDateTime.now(), null, 100));
        Payment createdPayment2 = paymentService.createPayment(new Payment(null,
                null, null, LocalDateTime.now(), null, 200));

        List<Payment> createdPayments = paymentService.getAllPayments();

        assertEquals(2, createdPayments.size());
        assertEquals(100, createdPayments.get(0).getAmount());
        assertEquals(200, createdPayments.get(1).getAmount());
    }

    @Test
    @Transactional
    public void testUpdatePayment() {
        Payment createdPayment = paymentService.createPayment(new Payment(null,
                null, null, LocalDateTime.now(), null, 100));

        createdPayment.setAmount(200);
        Payment updatedPayment = paymentService.updatePayment(createdPayment);

        assertNotNull(updatedPayment);
        assertEquals(createdPayment.getId(), updatedPayment.getId());
        assertEquals(200, updatedPayment.getAmount());
    }

    @Test
    @Transactional
    public void testDeletePayment() {
        Payment createdPayment1 = paymentService.createPayment(new Payment(null,
                null, null, LocalDateTime.now(), null, 100));
        Payment createdPayment2 = paymentService.createPayment(new Payment(null,
                null, null, LocalDateTime.now(), null, 200));

        paymentService.deletePaymentById(createdPayment1.getId());

        assertEquals(1, paymentService.getAllPayments().size());
    }
}

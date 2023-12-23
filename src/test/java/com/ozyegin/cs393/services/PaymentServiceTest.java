package com.ozyegin.cs393.services;


import com.ozyegin.cs393.dto.PaymentDTO;
import com.ozyegin.cs393.entities.Payment;
import com.ozyegin.cs393.mappers.PaymentMapper;
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
    @Autowired
    private PaymentMapper paymentMapper;

    @Test
    @Transactional
    public void testCreatePayment() {
        Payment payment = new Payment(null,
                null, null, LocalDateTime.now(), null, 100);
        PaymentDTO createdPayment = paymentMapper.paymentToPaymentDto(payment);
        createdPayment = paymentService.createPayment(createdPayment);

        assertNotNull(createdPayment);
        assertNotNull(createdPayment.getId());
        assertEquals(100, createdPayment.getAmount());
    }

    @Test
    @Transactional
    public void testGetAllPayments() {
        Payment payment1 = new Payment(null,
                null, null, LocalDateTime.now(), null, 100);
        Payment payment2 = new Payment(null,
                null, null, LocalDateTime.now(), null, 200);

        PaymentDTO createdPayment1 = paymentService.createPayment(paymentMapper.paymentToPaymentDto(payment1));
        PaymentDTO createdPayment2 = paymentService.createPayment(paymentMapper.paymentToPaymentDto(payment2));


        List<PaymentDTO> createdPayments = paymentService.getAllPayments();

        assertEquals(2, createdPayments.size());
        assertEquals(100, createdPayments.get(0).getAmount());
        assertEquals(200, createdPayments.get(1).getAmount());
    }

    @Test
    @Transactional
    public void testUpdatePayment() {
        Payment payment = new Payment(null,
                null, null, LocalDateTime.now(), null, 100);
        PaymentDTO createdPayment = paymentMapper.paymentToPaymentDto(payment);
        createdPayment = paymentService.createPayment(createdPayment);

        createdPayment.setAmount(200);
        PaymentDTO updatedPayment = paymentService.updatePayment(createdPayment);

        assertNotNull(updatedPayment);
        assertEquals(createdPayment.getId(), updatedPayment.getId());
        assertEquals(200, updatedPayment.getAmount());
    }

    @Test
    @Transactional
    public void testDeletePayment() {
        Payment payment1 = new Payment(null,
                null, null, LocalDateTime.now(), null, 100);
        Payment payment2 = new Payment(null,
                null, null, LocalDateTime.now(), null, 200);

        PaymentDTO createdPayment1 = paymentService.createPayment(paymentMapper.paymentToPaymentDto(payment1));
        PaymentDTO createdPayment2 = paymentService.createPayment(paymentMapper.paymentToPaymentDto(payment2));

        paymentService.deletePaymentById(createdPayment1.getId());

        assertEquals(1, paymentService.getAllPayments().size());
    }
}

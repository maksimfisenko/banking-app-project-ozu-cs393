package com.ozyegin.cs393.services;

import com.ozyegin.cs393.entities.Payment;
import com.ozyegin.cs393.repositories.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment updatePayment(Payment updatedPayment) {

        Long id = updatedPayment.getId();

        Payment payment = paymentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Payment with id " + id + " not found"));

        payment.setSendingCard(updatedPayment.getSendingCard());
        payment.setReceivingAccount(updatedPayment.getReceivingAccount());
        payment.setTimeOfPayment(updatedPayment.getTimeOfPayment());
        payment.setCurrency(updatedPayment.getCurrency());
        payment.setAmount(updatedPayment.getAmount());

        return paymentRepository.save(updatedPayment);
    }

    public void deletePaymentById(Long id) {
        paymentRepository.deleteById(id);
    }
}

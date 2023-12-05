package com.ozyegin.cs393.Services;

import com.ozyegin.cs393.Entities.Payment;
import com.ozyegin.cs393.Repositories.PaymentRepository;
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
        return paymentRepository.save(updatedPayment);
    }

    public void deletePaymentById(Long id) {
        paymentRepository.deleteById(id);
    }
}

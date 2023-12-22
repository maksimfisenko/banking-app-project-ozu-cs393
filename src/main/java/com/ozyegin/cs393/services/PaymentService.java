package com.ozyegin.cs393.services;

import com.ozyegin.cs393.dto.PaymentDTO;
import com.ozyegin.cs393.entities.DebitCard;
import com.ozyegin.cs393.entities.Payment;
import com.ozyegin.cs393.mappers.PaymentMapper;
import com.ozyegin.cs393.repositories.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentMapper paymentMapper;

    // CRUD Operations

    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Payment payment = paymentMapper.paymentDtoToPayment(paymentDTO);
        payment = paymentRepository.save(payment);
        return paymentMapper.paymentToPaymentDto(payment);
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
    public List<Payment> getPaymentsBySendingCard(DebitCard sendingCard){
        return paymentRepository.findBySendingCard(sendingCard);
    }
}

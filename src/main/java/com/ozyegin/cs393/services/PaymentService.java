package com.ozyegin.cs393.services;

import com.ozyegin.cs393.dto.DebitCardDTO;
import com.ozyegin.cs393.dto.PaymentDTO;
import com.ozyegin.cs393.entities.DebitCard;
import com.ozyegin.cs393.entities.Payment;
import com.ozyegin.cs393.mappers.DebitCardMapper;
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
    @Autowired
    private DebitCardMapper debitCardMapper;

    // CRUD Operations

    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Payment payment = paymentMapper.paymentDtoToPayment(paymentDTO);
        payment = paymentRepository.save(payment);
        return paymentMapper.paymentToPaymentDto(payment);
    }

    public List<PaymentDTO> getAllPayments() {
        List <Payment> payments = paymentRepository.findAll();
        return paymentMapper.paymentsToPaymentDtos(payments);
    }

    public PaymentDTO updatePayment(PaymentDTO updatedPaymentDTO) {

        Payment payment = paymentMapper.paymentDtoToPayment(updatedPaymentDTO);
        payment = paymentRepository.save(payment);
        return paymentMapper.paymentToPaymentDto(payment);
    }

    public void deletePaymentById(Long id) {
        paymentRepository.deleteById(id);
    }
    public List<PaymentDTO> getPaymentsBySendingCard(DebitCardDTO sendingCardDTO){
        DebitCard sendingCard = debitCardMapper.debitCardDtoToDebitCard(sendingCardDTO);
        List <Payment> payments = paymentRepository.findBySendingCard(sendingCard);
        return paymentMapper.paymentsToPaymentDtos(payments);
    }
}

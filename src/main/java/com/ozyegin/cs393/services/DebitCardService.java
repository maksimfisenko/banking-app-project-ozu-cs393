package com.ozyegin.cs393.services;

import com.ozyegin.cs393.entities.Account;
import com.ozyegin.cs393.entities.DebitCard;
import com.ozyegin.cs393.entities.Payment;
import com.ozyegin.cs393.repositories.DebitCardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DebitCardService {
    @Autowired
    private DebitCardRepository debitCardRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PaymentService paymentService;

    // CRUD Operations

    public DebitCard createDebitCard(DebitCard debitCard) {
        return debitCardRepository.save(debitCard);
    }

    public List<DebitCard> getAllDebitCards() {
        return debitCardRepository.findAll();
    }

    public DebitCard updateDebitCard(DebitCard updatedDebitCard) {

        Long id = updatedDebitCard.getId();

        DebitCard debitCard = debitCardRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Debit Card with id " + id + " not found"));

        debitCard.setName(updatedDebitCard.getName());
        debitCard.setAccount(updatedDebitCard.getAccount());
        debitCard.setNumber(updatedDebitCard.getNumber());
        debitCard.setExpirationDate(updatedDebitCard.getExpirationDate());

        return debitCardRepository.save(updatedDebitCard);
    }

    public void deleteDebitCardById(Long id) {
        debitCardRepository.deleteById(id);
    }

    // Backend Service 7: Opening a New Card
    public DebitCard openDebitCard(Long accountNumber, String cardName) {

        DebitCard debitCard = new DebitCard();
        debitCard.setNumber(generateUniqueCardNumber());
        debitCard.setExpirationDate(LocalDate.now().plusYears(5));
        debitCard.setName(cardName);
        debitCard.setAccount(accountService.getAccountByNumber(accountNumber));

        return debitCardRepository.save(debitCard);
    }

    // Generate Card Number
    public String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumberBuilder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            cardNumberBuilder.append(random.nextInt(10));
        }
        return cardNumberBuilder.toString();
    }

    // Generate Unique Card Number
    public String generateUniqueCardNumber() {
        String cardNumber;
        do {
            cardNumber = generateCardNumber();
        } while (debitCardRepository.existsByNumber(cardNumber));
        return cardNumber;
    }

    // Backend Service 8: Make payment with debit card
    public boolean makePayment(Long debitCardId, Long receivingAccountNumber, double amount){

        DebitCard debitCard  = debitCardRepository.findById(debitCardId).orElseThrow(() ->
                new EntityNotFoundException("Card with id " + debitCardId + " not found"));

        double ret = accountService.transferMoney(debitCard.getAccount().getNumber(), receivingAccountNumber, amount);

        if (ret == -1.0)
            return false;

        Payment curPayment = new Payment();
        curPayment.setAmount(amount);
        curPayment.setCurrency(debitCard.getAccount().getCurrency());
        curPayment.setTimeOfPayment(LocalDateTime.now());
        curPayment.setSendingCard(debitCard);
        curPayment.setReceivingAccount(accountService.getAccountByNumber(receivingAccountNumber));
        paymentService.createPayment(curPayment);

        return true;
    }

    // Backend Service 10: Get all payments within specified dates
    public List <Payment> getPaymentsByDates(Long debitCardId, LocalDate start, LocalDate end) throws Exception {

        if (start.isBefore(end))
            throw new Exception("Dates are incorrect");
        if (end.isAfter(LocalDate.now()))
            throw new Exception("The date " + end.toString() + " is in the future");

        List <Payment> result = new ArrayList<Payment>();
        DebitCard sendingCard = debitCardRepository.findById(debitCardId).orElseThrow(() ->
                new EntityNotFoundException("Card with id " + debitCardId + " not found"));

        List<Payment> payments = paymentService.getPaymentsBySendingCard(sendingCard);
        for (Payment curPayment : payments){
            if (curPayment.getTimeOfPayment().isAfter(start.atStartOfDay()) &&
            curPayment.getTimeOfPayment().isBefore(end.atTime(23, 59)))
                result.add(curPayment);
        }
        return result;
    }

}

package com.ozyegin.cs393.services;

import com.ozyegin.cs393.dto.AccountDTO;
import com.ozyegin.cs393.dto.DebitCardDTO;
import com.ozyegin.cs393.dto.PaymentDTO;
import com.ozyegin.cs393.entities.DebitCard;
import com.ozyegin.cs393.entities.Payment;
import com.ozyegin.cs393.mappers.AccountMapper;
import com.ozyegin.cs393.mappers.DebitCardMapper;
import com.ozyegin.cs393.mappers.PaymentMapper;
import com.ozyegin.cs393.repositories.DebitCardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
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
    @Autowired
    private DebitCardMapper debitCardMapper;
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private AccountMapper accountMapper;

    // CRUD Operations

    public DebitCardDTO createDebitCard(DebitCardDTO debitCardDTO) {
        DebitCard debitCard = debitCardMapper.debitCardDtoToDebitCard(debitCardDTO);
        debitCard = debitCardRepository.save(debitCard);
        return debitCardMapper.debitCardtoDebitCardDto(debitCard);
    }

    public DebitCardDTO getDebitCardById(Long id){
        DebitCard debitCard = debitCardRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Card with id " + id + " not found"));
        return debitCardMapper.debitCardtoDebitCardDto(debitCard);
    }

    public List<DebitCardDTO> getAllDebitCards() {
        List<DebitCard> debitCards = debitCardRepository.findAll();
        return debitCardMapper.debitCardsToDebitCardDtos(debitCards);
    }

    public DebitCardDTO updateDebitCard(DebitCardDTO updatedDebitCardDTO) {

        DebitCard debitCard = debitCardMapper.debitCardDtoToDebitCard(updatedDebitCardDTO);
        debitCard = debitCardRepository.save(debitCard);
        return debitCardMapper.debitCardtoDebitCardDto(debitCard);
    }

    public void deleteDebitCardById(Long id){
        debitCardRepository.deleteById(id);
    }

    public void deleteAllDebitCards(){
        debitCardRepository.deleteAll();
    }

    // Backend Service 7: Opening a New Card
    public DebitCardDTO openDebitCard(Long accountNumber, String cardName) {

        DebitCard debitCard = new DebitCard();
        debitCard.setNumber(generateUniqueCardNumber());
        debitCard.setExpirationDate(LocalDate.now().plusYears(5));
        debitCard.setName(cardName);

        AccountDTO accountDTO = accountService.getAccountByNumber(accountNumber);
        debitCard.setAccount(accountMapper.accountDtoToAccount(accountDTO));
        debitCard = debitCardRepository.save(debitCard);
        return debitCardMapper.debitCardtoDebitCardDto(debitCard);
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
    public boolean makePayment(DebitCardDTO debitCardDTO, AccountDTO receivingAccountDTO, double amount){

        DebitCard debitCard  = debitCardMapper.debitCardDtoToDebitCard(debitCardDTO);

        double ret = accountService.transferMoney(debitCard.getAccount().getNumber(),
                receivingAccountDTO.getNumber(), amount);

        if (ret == -1.0)
            return false;

        Payment curPayment = new Payment();
        curPayment.setAmount(amount);
        curPayment.setCurrency(debitCard.getAccount().getCurrency());
        curPayment.setTimeOfPayment(LocalDateTime.now());
        curPayment.setSendingCard(debitCard);

        AccountDTO accountDTO = accountService.getAccountByNumber(receivingAccountDTO.getNumber());
        curPayment.setReceivingAccount(accountMapper.accountDtoToAccount(accountDTO));

        paymentService.createPayment(paymentMapper.paymentToPaymentDto(curPayment));

        return true;
    }

    // Backend Service 10: Get all payments within specified dates
    public List <PaymentDTO> getPaymentsByDates(DebitCardDTO debitCardDTO, LocalDate start, LocalDate end)
            throws DateTimeException{

        DebitCard debitCard = debitCardMapper.debitCardDtoToDebitCard(debitCardDTO);
        if (start.isAfter(end))
            throw new DateTimeException("Dates are incorrect");
        if (end.isAfter(LocalDate.now()))
            throw new DateTimeException("The date " + end + " is in the future");

        List <PaymentDTO> result = new ArrayList<>();
        DebitCard sendingCard = debitCardRepository.findById(debitCard.getId()).orElseThrow(() ->
                new EntityNotFoundException("Card with id " + debitCard.getId() + " not found"));

        List<PaymentDTO> payments = paymentService.getPaymentsBySendingCard(
                debitCardMapper.debitCardtoDebitCardDto(sendingCard));
        for (PaymentDTO curPayment : payments){
            if (curPayment.getTimeOfPayment().isAfter(start.atStartOfDay()) &&
            curPayment.getTimeOfPayment().isBefore(end.atTime(23, 59)))
                result.add(curPayment);
        }
        return result;
    }

}

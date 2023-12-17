package com.ozyegin.cs393.services;

import com.ozyegin.cs393.entities.DebitCard;
import com.ozyegin.cs393.repositories.DebitCardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class DebitCardService {
    @Autowired
    private DebitCardRepository debitCardRepository;
    @Autowired
    private AccountService accountService;

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
        debitCard.setNumber(generateCardNumber());
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

    // Generate Unique Card Number()
    public String generateUniqueCardNumber() {
        String cardNumber;
        do {
            cardNumber = generateCardNumber();
        } while (debitCardRepository.existsByNumber(cardNumber));
        return cardNumber;
    }
}

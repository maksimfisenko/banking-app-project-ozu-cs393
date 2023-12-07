package com.ozyegin.cs393.services;

import com.ozyegin.cs393.entities.DebitCard;
import com.ozyegin.cs393.repositories.DebitCardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebitCardService {
    @Autowired
    private DebitCardRepository debitCardRepository;

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
}

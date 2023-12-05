package com.ozyegin.cs393.Services;

import com.ozyegin.cs393.Entities.DebitCard;
import com.ozyegin.cs393.Repositories.DebitCardRepository;
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

    public List<DebitCard> findAllDebitCards() {
        return debitCardRepository.findAll();
    }

    public DebitCard updateDebitCard(DebitCard updatedDebitCard) {
        return debitCardRepository.save(updatedDebitCard);
    }

    public void deleteDebitCardById(Long id) {
        debitCardRepository.deleteById(id);
    }
}

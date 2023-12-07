package com.ozyegin.cs393.services;

import com.ozyegin.cs393.entities.Currency;
import com.ozyegin.cs393.repositories.CurrencyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;

    public Currency createCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency updateCurrency(Currency updatedCurrency) {

        Long id = updatedCurrency.getId();

        Currency currency = currencyRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Currency with id " + id + " not found"));

        currency.setName(updatedCurrency.getName());
        currency.setSymbol(updatedCurrency.getSymbol());
        currency.setExchangeRateToUsd(updatedCurrency.getExchangeRateToUsd());

        return currencyRepository.save(updatedCurrency);
    }

    public void deleteCurrencyById(Long id) {
        currencyRepository.deleteById(id);
    }
}

package com.ozyegin.cs393.Services;

import com.ozyegin.cs393.Entities.Currency;
import com.ozyegin.cs393.Repositories.CurrencyRepository;
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
        return currencyRepository.save(updatedCurrency);
    }

    public void deleteCurrencyById(Long id) {
        currencyRepository.deleteById(id);
    }
}

package com.ozyegin.cs393.services;

import com.ozyegin.cs393.dto.CurrencyDTO;
import com.ozyegin.cs393.entities.Currency;
import com.ozyegin.cs393.mappers.CurrencyMapper;
import com.ozyegin.cs393.repositories.CurrencyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private CurrencyMapper currencyMapper;

    // CRUD Operations

    public CurrencyDTO createCurrency(CurrencyDTO currencyDTO) {
        Currency currency = currencyMapper.currencyDtoToCurrency(currencyDTO);
        currency = currencyRepository.save(currency);
        return currencyMapper.currencyToCurrencyDto(currency);
    }

    public List<CurrencyDTO> getAllCurrencies() {
        List<Currency> currencies = currencyRepository.findAll();
        return currencyMapper.currenciesToCurrencyDtos(currencies);
    }

    public CurrencyDTO getCurrencyById(Long id) {
        Currency currency = currencyRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Currency with id " + id + " not found"));
        return currencyMapper.currencyToCurrencyDto(currency);
    }

    public CurrencyDTO updateCurrency(CurrencyDTO updatedCurrencyDto) {
        Currency currency = currencyMapper.currencyDtoToCurrency(updatedCurrencyDto);
        currency = currencyRepository.save(currency);
        return currencyMapper.currencyToCurrencyDto(currency);
    }

    public void deleteCurrencyById(Long id) {
        currencyRepository.deleteById(id);
    }

    public void deleteAllCurrencies() {
        currencyRepository.deleteAll();
    }
}

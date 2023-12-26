package com.ozyegin.cs393.controllers;

import com.ozyegin.cs393.dto.CurrencyDTO;
import com.ozyegin.cs393.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    private ResponseEntity<List<CurrencyDTO>> getAllCurrencies() {

        List<CurrencyDTO> currencies = currencyService.getAllCurrencies();

        if (currencies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(currencies, HttpStatus.OK);
        }
    }

    @PostMapping
    private ResponseEntity<CurrencyDTO> createCurrency(@RequestBody CurrencyDTO currencyDTO) {

        CurrencyDTO currency = currencyService.createCurrency(currencyDTO);

        if (currency == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(currency, HttpStatus.CREATED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyDTO> getCurrencyById(@PathVariable Long id) {

        CurrencyDTO currencyDTO = currencyService.getCurrencyById(id);

        if (currencyDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(currencyDTO, HttpStatus.OK);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<CurrencyDTO> handlePostOnExistingCurrency() {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurrencyDTO> updateCurrency(@RequestBody CurrencyDTO currencyDTO) {

        CurrencyDTO updatedCurrency = currencyService.updateCurrency(currencyDTO);

        if (updatedCurrency == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedCurrency, HttpStatus.OK);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllCurrencies() {
        currencyService.deleteAllCurrencies();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrencyById(@PathVariable Long id) {
        currencyService.deleteCurrencyById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

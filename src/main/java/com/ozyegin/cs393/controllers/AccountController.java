package com.ozyegin.cs393.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozyegin.cs393.dto.AccountDTO;
import com.ozyegin.cs393.dto.CurrencyDTO;
import com.ozyegin.cs393.dto.DebitCardDTO;
import com.ozyegin.cs393.services.AccountService;
import com.ozyegin.cs393.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {

        List<AccountDTO> accounts = accountService.getAllAccounts();

        if (accounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {

        AccountDTO createdAccount = accountService.createAccount(accountDTO);

        if (createdAccount == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
        }
    }

    // Service 1: Get Account By Number
    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> getAccountByNumber(@PathVariable Long accountNumber) {

        AccountDTO accountDTO = accountService.getAccountByNumber(accountNumber);

        if (accountDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(accountDTO, HttpStatus.OK);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AccountDTO>> getAccountsByUserId(@PathVariable Long userId) {

        List<AccountDTO> accounts = accountService.getAccountsByUserId(userId);

        if (accounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        }
    }

    @PostMapping("/{accountNumber}")
    public ResponseEntity<Void> handlePostOnExistingAccount(@PathVariable String accountNumber) {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PutMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> updateAccount(@RequestBody AccountDTO accountDTO, @PathVariable String accountNumber) {

        AccountDTO updatedAccount = accountService.updateAccount(accountDTO);

        if (updatedAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllAccounts() {
        accountService.deleteAllAccounts();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<Void> deleteAccountByNumber(@PathVariable Long accountNumber) {
        accountService.deleteAccountByNumber(accountNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Service 2: Change Currency
    @PutMapping("/changeCurrency")
    public ResponseEntity<AccountDTO> changeCurrency(@RequestBody Map<String, Object> requestBody){

        Long accountNumber = objectMapper.convertValue(requestBody.get("accountNumber"), Long.class);
        Long currencyId = objectMapper.convertValue(requestBody.get("currencyId"), Long.class);

        AccountDTO accountDTO = accountService.getAccountByNumber(accountNumber);
        CurrencyDTO currencyDTO = currencyService.getCurrencyById(currencyId);

        AccountDTO updatedAccount = accountService.changeCurrency(accountDTO, currencyDTO);

        if (updatedAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        }
    }

    // Service 3: Close Account
    @DeleteMapping("/close")
    public ResponseEntity<Void> closeAccount(@RequestBody Long accountNumber){

        AccountDTO accountDTO = accountService.getAccountByNumber(accountNumber);

        boolean res = accountService.closeAccount(accountDTO);

        if (!res) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    // Service 5: Transfer Money
    @PutMapping("/transfer")
    public ResponseEntity<Double> transferMoney(@RequestBody Map<String, Object> requestBody){

        Long sendingAccountNumber = objectMapper.convertValue(requestBody.get("sendingAccountNumber"), Long.class);
        Long receivingAccountNumber = objectMapper.convertValue(requestBody.get("receivingAccountNumber"), Long.class);
        Double amount = objectMapper.convertValue(requestBody.get("amount"), Double.class);

        if (sendingAccountNumber == null || receivingAccountNumber == null || amount == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        double res = accountService.transferMoney(sendingAccountNumber, receivingAccountNumber, amount);

        if (res == -1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    // Service 6: Get Money By Date
    @GetMapping("/getByDate/{accountNumber}/{dateString}")
    public ResponseEntity<Double> getAmountOnSelectedDate(@PathVariable Long accountNumber, @PathVariable String dateString){

        AccountDTO accountDTO = accountService.getAccountByNumber(accountNumber);
        LocalDate date = LocalDate.parse(dateString);

        double res = accountService.getAmountOnSelectedDate(accountDTO, date);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //New Service 2: Get all account's debit cards
    @GetMapping("/debitCards/{number}")
    public ResponseEntity<List<DebitCardDTO>> getAllAccountsDebitCards(@PathVariable Long number){
        List<DebitCardDTO> debitCards = accountService.getAllAccountsDebitCards(number);
        if (debitCards.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(debitCards, HttpStatus.OK);
    }

}
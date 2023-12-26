package com.ozyegin.cs393.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozyegin.cs393.dto.AccountDTO;
import com.ozyegin.cs393.dto.CurrencyDTO;
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

        AccountDTO createdAccountDTO = accountService.createAccount(accountDTO);

        if (createdAccountDTO == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(createdAccountDTO, HttpStatus.CREATED);
        }
    }

    //Service 1
    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> getAccountByNumber(@PathVariable Long accountNumber) {

        AccountDTO accountDTO = accountService.getAccountByNumber(accountNumber);

        if (accountDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(accountDTO, HttpStatus.OK);
        }
    }

    @PostMapping("/{accountNumber}")
    public ResponseEntity<Void> handlePostOnExistingAccount() {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PutMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> updateAccount(@RequestBody AccountDTO accountDTO) {

        AccountDTO updatedAccountDTO = accountService.updateAccount(accountDTO);

        if (updatedAccountDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedAccountDTO, HttpStatus.OK);
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
    @PutMapping("/changeCurrency/{accountNumber}/{currencyId}")
    public ResponseEntity<AccountDTO> changeCurrency(@PathVariable Long accountNumber, @PathVariable Long currencyId){

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
    @DeleteMapping("/close/{accountNumber}")
    public ResponseEntity<Void> closeAccount(@PathVariable Long accountNumber){

        AccountDTO accountDTO = accountService.getAccountByNumber(accountNumber);
        boolean res = accountService.closeAccount(accountDTO);

        if (!res)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(HttpStatus.OK);
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

        AccountDTO sendingAccountDTO = accountService.getAccountByNumber(sendingAccountNumber);
        AccountDTO receivingAccountDTO = accountService.getAccountByNumber(receivingAccountNumber);

        double res = accountService.transferMoney(sendingAccountDTO, receivingAccountDTO, amount);

        if (res == -1)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // Service 6: Get Amount of Money on Certain Date
    @GetMapping("/getByDate/{accountNumber}/{dateString}")
    public ResponseEntity<Double> getAmountOnSelectedDate(@PathVariable Long accountNumber,
                                                          @PathVariable String dateString){

        AccountDTO accountDTO = accountService.getAccountByNumber(accountNumber);
        LocalDate date = LocalDate.parse(dateString);

        double res = accountService.getAmountOnSelectedDate(accountDTO, date);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
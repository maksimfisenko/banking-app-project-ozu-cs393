package com.ozyegin.cs393.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozyegin.cs393.dto.AccountDTO;
import com.ozyegin.cs393.dto.CurrencyDTO;
import com.ozyegin.cs393.entities.Account;
import com.ozyegin.cs393.services.AccountService;
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

    //Service 2
    @PutMapping("/{accountNumber}/changeCurrency")
    public ResponseEntity<AccountDTO> changeCurrency(@PathVariable Long accountNumber, @RequestBody CurrencyDTO currencyDTO){
        AccountDTO accountDTO = accountService.getAccountByNumber(accountNumber);
        AccountDTO updatedAccount = accountService.changeCurrency(accountDTO, currencyDTO);

        if (updatedAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        }
    }

    //Service 3
    @DeleteMapping("/{accountNumber}/close")
    public ResponseEntity<Void> closeAccount(@RequestBody AccountDTO accountDTO){
        boolean res = accountService.closeAccount(accountDTO);
        if (!res)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(HttpStatus.OK);
    }

    //Service 5
    @PutMapping("/{amount}/transfer")
    public ResponseEntity<Double> transferMoney(@RequestBody Map<String, Object> requestBody,
                                                @PathVariable Double amount){

        AccountDTO sendingAccountDTO = objectMapper.convertValue(requestBody.get("sendingAccountDTO"), AccountDTO.class);
        AccountDTO receivingAccountDTO = objectMapper.convertValue(requestBody.get("receivingAccountDTO"), AccountDTO.class);

        if (sendingAccountDTO == null || receivingAccountDTO == null || amount == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        double res = accountService.transferMoney(sendingAccountDTO, receivingAccountDTO, amount);
        if (res == -1)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<Double>(res, HttpStatus.OK);
    }

    //Service 6
    @GetMapping("/{accountNumber}/getByDate")
    public ResponseEntity<Double> getAmountOnSelectedDate(@PathVariable Long accountNumber,
                                                          @RequestBody Map<String, Object> payload){
        AccountDTO accountDTO = accountService.getAccountByNumber(accountNumber);
        String dateString = (String) payload.get("date");
        LocalDate date = LocalDate.parse(dateString);
        double res = accountService.getAmountOnSelectedDate(accountDTO, date);
        return new ResponseEntity<Double>(res, HttpStatus.OK);
    }

}
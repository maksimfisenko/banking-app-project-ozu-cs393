package com.ozyegin.cs393.services;

import com.ozyegin.cs393.entities.Account;
import com.ozyegin.cs393.entities.AccountType;
import com.ozyegin.cs393.entities.Currency;
import com.ozyegin.cs393.repositories.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CurrencyService currencyService;

    // CRUD operations
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountByNumber(Long accountNumber) {
        return accountRepository.findById(accountNumber).orElseThrow(() ->
                new EntityNotFoundException("Account with number " + accountNumber + " not found"));
    }

    public Account updateAccount(Account updatedAccount) {

        Long accountNumber = updatedAccount.getNumber();
        Account account = accountRepository.findById(accountNumber).orElseThrow(() ->
                new EntityNotFoundException("Account with number " + accountNumber + " not found"));

        account.setDebitCards(updatedAccount.getDebitCards());
        account.setAmount(updatedAccount.getAmount());
        account.setName(updatedAccount.getName());
        account.setOwner(updatedAccount.getOwner());
        account.setCurrency(updatedAccount.getCurrency());
        account.setType(updatedAccount.getType());
        account.setOpeningDate(updatedAccount.getOpeningDate());
        account.setReceivedTransactions(updatedAccount.getReceivedTransactions());
        account.setSentTransactions(updatedAccount.getSentTransactions());

        return accountRepository.save(account);
    }

    public void deleteAccountById(Long accountNumber) {
        accountRepository.deleteById(accountNumber);
    }

    public void deleteAllAccounts() {
        accountRepository.deleteAll();
    }

    // Backend Service 1: Opening a New Account
    public Account openAccount(Long userId, Currency currency, AccountType accountType, String name) {

        Account account = new Account();
        account.setName(name);
        account.setCurrency(currency);
        account.setType(accountType);
        account.setAmount(0);
        account.setOpeningDate(LocalDate.now());
        account.setOwner(userService.getUserById(userId));

        return accountRepository.save(account);
    }

    // Backend Service 2: Changing Account Currency
    public Account changeCurrency(Long accountNumber, Long currencyId) {

        Account account = accountRepository.findById(accountNumber).orElseThrow(() ->
                new EntityNotFoundException("Account with number " + accountNumber + " not found"));
        Currency new_currency = currencyService.getCurrencyById(currencyId);
        Currency old_currency = account.getCurrency();
        double new_ammount = account.getAmount() * old_currency.getExchangeRateToUsd();
        new_ammount = new_ammount / new_currency.getExchangeRateToUsd();
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.DOWN);
        new_ammount = Double.parseDouble(df.format(new_ammount));

        account.setCurrency(new_currency);
        account.setAmount(new_ammount);
        return account;
    }
}

package com.ozyegin.cs393.services;

import com.ozyegin.cs393.entities.*;
import com.ozyegin.cs393.repositories.AccountRepository;
import com.ozyegin.cs393.repositories.DebitCardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private DebitCardService debitCardService;
    @Autowired
    private TransactionService transactionService;

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
        account.setAmount(0.0);
        account.setOpeningDate(LocalDate.now());
        account.setOwner(userService.getUserById(userId));

        return accountRepository.save(account);
    }

    // Backend Service 2: Changing Account Currency
    public Account changeCurrency(Long accountNumber, Long currencyId) {

        Account account = accountRepository.findById(accountNumber).orElseThrow(() ->
                new EntityNotFoundException("Account with number " + accountNumber + " not found"));
        Currency newCurrency = currencyService.getCurrencyById(currencyId);
        Currency oldCurrency = account.getCurrency();
        double newAmmount = account.getAmount() * oldCurrency.getExchangeRateToUsd();
        newAmmount = newAmmount / newCurrency.getExchangeRateToUsd();
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.DOWN);
        newAmmount = Double.parseDouble(df.format(newAmmount));

        account.setCurrency(newCurrency);
        account.setAmount(newAmmount);
        return account;
    }

    // Backend Service 3: Close an existing Account
    public boolean closeAccount(Long accountNumber){

        Account account = accountRepository.findById(accountNumber).orElseThrow(() ->
                new EntityNotFoundException("Account with number " + accountNumber + " not found"));
        if (account.getAmount() != 0)
            return false;
        List<DebitCard> debitCards= account.getDebitCards();
        for (DebitCard curDebitCard : debitCards){
            debitCardService.deleteDebitCardById(curDebitCard.getId());
        }
        accountRepository.deleteById(accountNumber);
        return true;
    }

    // Backend Service 5: Transfer Money Between 2 Accounts
    public double transferMoney (Long sendingAccountNumber, Long receivingAccountNumber, double ammount){

        Account sendingAccount = accountRepository.findById(sendingAccountNumber).orElseThrow(() ->
                new EntityNotFoundException("Account with number " + sendingAccountNumber + " not found"));
        Account receivingAccount = accountRepository.findById(receivingAccountNumber).orElseThrow(() ->
                new EntityNotFoundException("Account with number " + receivingAccountNumber + " not found"));
        if (sendingAccount.getAmount() < ammount)
            return -1.0;
        sendingAccount.setAmount(sendingAccount.getAmount() - ammount);
        double amountUSED = ammount * sendingAccount.getCurrency().getExchangeRateToUsd();
        double amountReceiving = amountUSED / receivingAccount.getCurrency().getExchangeRateToUsd();
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.DOWN);
        amountReceiving = Double.parseDouble(df.format(amountReceiving));
        receivingAccount.setAmount(receivingAccount.getAmount() + amountReceiving);

        Transaction curTransaction = new Transaction();
        curTransaction.setAmount(ammount);
        curTransaction.setCurrency(sendingAccount.getCurrency());
        curTransaction.setSendingAccount(sendingAccount);
        curTransaction.setReceivingAccount(sendingAccount);
        curTransaction.setTimeOfTransaction(LocalDateTime.now());
        transactionService.createTransaction(curTransaction);

        return sendingAccount.getAmount();
    }

    // Backend Service 6: Get the amount on the account on selected date
    public double amountOnSelectedDate (Long accountNumber, LocalDate date) throws Exception{

        Account account = accountRepository.findById(accountNumber).orElseThrow(() ->
                new EntityNotFoundException("Account with number " + accountNumber + " not found"));

        if (date.isBefore(account.getOpeningDate())){
            throw new Exception("Account did not exist on " + date.toString());
        }
        if (date.isAfter(LocalDate.now())){
            throw new Exception("The date " + date.toString() + " is in the future");
        }

        double curAmount = account.getAmount();
        List <Transaction> sending = transactionService.getSendingTransactionsOfAccount(account);
        for (Transaction curTransaction : sending){
            if (curTransaction.getTimeOfTransaction().isAfter(date.atStartOfDay()))
                curAmount -= curTransaction.getAmount();
        }
        List <Transaction> receiving = transactionService.getReceivingTransactionsOfAccount(account);
        for (Transaction curTransaction : receiving){
            if (curTransaction.getTimeOfTransaction().isAfter(date.atStartOfDay())) {
                curAmount = curAmount * curTransaction.getCurrency().getExchangeRateToUsd();
                curAmount = curAmount / account.getCurrency().getExchangeRateToUsd();
                curAmount += curTransaction.getAmount();
            }
        }
        return curAmount;
    }

}

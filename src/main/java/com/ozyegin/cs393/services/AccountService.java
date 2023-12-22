package com.ozyegin.cs393.services;

import com.ozyegin.cs393.dto.AccountDTO;
import com.ozyegin.cs393.entities.*;
import com.ozyegin.cs393.mappers.AccountMapper;
import com.ozyegin.cs393.mappers.TransactionMapper;
import com.ozyegin.cs393.repositories.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.*;
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
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private TransactionMapper transactionMapper;

    // CRUD Operations

    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = accountMapper.accountDtoToAccount(accountDTO);
        account = accountRepository.save(account);
        return accountMapper.accountToAccountDto(account);
    }

    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accountMapper.accountsToAccountDtos(accounts);
    }

    public AccountDTO getAccountByNumber(Long accountNumber) {
        Account account = accountRepository.findById(accountNumber).orElseThrow(() ->
                new EntityNotFoundException("Account with number " + accountNumber + " not found"));
        return accountMapper.accountToAccountDto(account);
    }

    public AccountDTO updateAccount(AccountDTO updatedAccountDTO) {
        Account account = accountMapper.accountDtoToAccount(updatedAccountDTO);
        account = accountRepository.save(account);
        return accountMapper.accountToAccountDto(account);
    }

    public void deleteAccountById(AccountDTO accountDTO) {
        Long accountNumber = accountDTO.getNumber();
        accountRepository.deleteById(accountNumber);
    }

    public void deleteAllAccounts() {
        accountRepository.deleteAll();
    }

    // Backend Service 1: Opening a New Account
    public AccountDTO openAccount(AccountDTO accountDTO) {
        Account account = accountMapper.accountDtoToAccount(accountDTO);
        account = accountRepository.save(account);
        return accountMapper.accountToAccountDto(account);
    }

    // Backend Service 2: Changing Account Currency
    public AccountDTO changeCurrency(Long accountNumber, Long currencyId) {

        Account account = accountRepository.findById(accountNumber).orElseThrow(() ->
                new EntityNotFoundException("Account with number " + accountNumber + " not found"));

        Currency newCurrency = currencyService.getCurrencyById(currencyId);
        Currency oldCurrency = account.getCurrency();

        double newAmount = account.getAmount() * oldCurrency.getExchangeRateToUsd();
        newAmount = newAmount / newCurrency.getExchangeRateToUsd();

        DecimalFormat df = new DecimalFormat("#.##");
        // fix rounding
        df.setRoundingMode(RoundingMode.DOWN);
        newAmount = Double.parseDouble(df.format(newAmount));

        account.setCurrency(newCurrency);
        account.setAmount(newAmount);

        return accountMapper.accountToAccountDto(account);
    }

    // Backend Service 3: Close an existing Account
    public boolean closeAccount(Long accountNumber){

        Account account = accountRepository.findById(accountNumber).orElseThrow(() ->
                new EntityNotFoundException("Account with number " + accountNumber + " not found"));

        if (account.getAmount() != 0)
            return false;

        List<DebitCard> debitCards = account.getDebitCards();

        for (DebitCard curDebitCard : debitCards){
            debitCardService.deleteDebitCardById(curDebitCard.getId());
        }

        accountRepository.deleteById(accountNumber);

        return true;
    }

    // Backend Service 5: Transfer Money Between 2 Accounts
    public double transferMoney (Long sendingAccountNumber, Long receivingAccountNumber, double amount){

        Account sendingAccount = accountRepository.findById(sendingAccountNumber).orElseThrow(() ->
                new EntityNotFoundException("Account with number " + sendingAccountNumber + " not found"));
        Account receivingAccount = accountRepository.findById(receivingAccountNumber).orElseThrow(() ->
                new EntityNotFoundException("Account with number " + receivingAccountNumber + " not found"));

        if (sendingAccount.getAmount() < amount)
            return -1.0;

        sendingAccount.setAmount(sendingAccount.getAmount() - amount);

        double amountUSED = amount * sendingAccount.getCurrency().getExchangeRateToUsd();
        double amountReceiving = amountUSED / receivingAccount.getCurrency().getExchangeRateToUsd();

        DecimalFormat df = new DecimalFormat("#.##");
        // fix rounding
        df.setRoundingMode(RoundingMode.DOWN);

        amountReceiving = Double.parseDouble(df.format(amountReceiving));
        receivingAccount.setAmount(receivingAccount.getAmount() + amountReceiving);

        Transaction currentTransaction = new Transaction();
        currentTransaction.setAmount(amount);
        currentTransaction.setCurrency(sendingAccount.getCurrency());
        currentTransaction.setSendingAccount(sendingAccount);
        currentTransaction.setReceivingAccount(sendingAccount);
        currentTransaction.setTimeOfTransaction(LocalDateTime.now());

        transactionService.createTransaction(transactionMapper.transactionToTransactionDto(currentTransaction));

        return sendingAccount.getAmount();
    }

    // Backend Service 6: Get the amount on the account on selected date
    public double getAmountOnSelectedDate(Long accountNumber, LocalDate date) throws Exception{

        Account account = accountRepository.findById(accountNumber).orElseThrow(() ->
                new EntityNotFoundException("Account with number " + accountNumber + " not found"));

        if (date.isBefore(account.getOpeningDate())){
            throw new Exception("Account did not exist on " + date);
        }
        if (date.isAfter(LocalDate.now())){
            throw new Exception("The date " + date + " is in the future");
        }

        double currentAmount = account.getAmount();

        List <Transaction> sentTransactions =
                transactionService.getSendingTransactionsOfAccount(account);
        for (Transaction curTransaction : sentTransactions){
            if (curTransaction.getTimeOfTransaction().isAfter(date.atStartOfDay()))
                currentAmount -= curTransaction.getAmount();
        }

        List <Transaction> receivedTransactions = transactionService.getReceivingTransactionsOfAccount(account);
        for (Transaction curTransaction : receivedTransactions){
            if (curTransaction.getTimeOfTransaction().isAfter(date.atStartOfDay())) {
                currentAmount = currentAmount * curTransaction.getCurrency().getExchangeRateToUsd();
                currentAmount = currentAmount / account.getCurrency().getExchangeRateToUsd();
                currentAmount += curTransaction.getAmount();
            }
        }

        return currentAmount;
    }

}

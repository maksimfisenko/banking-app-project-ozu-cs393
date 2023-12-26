package com.ozyegin.cs393.services;

import com.ozyegin.cs393.dto.AccountDTO;
import com.ozyegin.cs393.dto.CurrencyDTO;
import com.ozyegin.cs393.dto.TransactionDTO;
import com.ozyegin.cs393.entities.*;
import com.ozyegin.cs393.mappers.*;
import com.ozyegin.cs393.repositories.AccountRepository;
import com.ozyegin.cs393.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private TransactionService transactionService;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private CurrencyMapper currencyMapper;
    @Autowired
    private DebitCardMapper debitCardMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    // CRUD OPERATIONS

    // CRUD & Backend Service 1: Opening a New Account
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

    public void deleteAccountByNumber(Long accountNumber) {
        accountRepository.deleteById(accountNumber);
    }

    public void deleteAllAccounts() {
        accountRepository.deleteAll();
    }

    // BACKEND SERVICES

    // Backend Service 2: Changing Account Currency
    public AccountDTO changeCurrency(AccountDTO accountDTO, CurrencyDTO currencyDTO) {

        Account account = accountMapper.accountDtoToAccount(accountDTO);

        Currency newCurrency = currencyMapper.currencyDtoToCurrency(currencyDTO);
        Currency oldCurrency = account.getCurrency();

        double newAmount = account.getAmount() * oldCurrency.getExchangeRateToUsd();
        newAmount = newAmount / newCurrency.getExchangeRateToUsd();
        newAmount = Math.round(newAmount * 100.0) / 100.0;

        account.setCurrency(newCurrency);
        account.setAmount(newAmount);

        return accountMapper.accountToAccountDto(account);
    }

    // Backend Service 3: Close an Existing Account
    public boolean closeAccount(AccountDTO accountDTO){

        Account account = accountMapper.accountDtoToAccount(accountDTO);

        if (account.getAmount() != 0)
            return false;

//        List<DebitCard> debitCards = account.getDebitCards();
//
//        for (DebitCard curDebitCard : debitCards){
//            debitCardService.deleteDebitCardById(debitCardMapper.debitCardToDebitCardDto(curDebitCard));
//        }

        accountRepository.deleteById(account.getNumber());

        return true;
    }

    // Backend Service 5: Transfer Money Between 2 Accounts
    public double transferMoney (Long sendingAccountId, Long receivingAccountId, double amount){

        Account sendingAccount = accountMapper.accountDtoToAccount(
                this.getAccountByNumber(sendingAccountId));
        Account receivingAccount = accountMapper.accountDtoToAccount(
                this.getAccountByNumber(receivingAccountId));

        if (sendingAccount.getAmount() < amount)
            return -1.0;

        sendingAccount.setAmount(sendingAccount.getAmount() - amount);

        double amountUsed = amount * sendingAccount.getCurrency().getExchangeRateToUsd();
        double amountReceiving = amountUsed / receivingAccount.getCurrency().getExchangeRateToUsd();

        amountReceiving = Math.round(amountReceiving * 100.0) / 100.0;
        receivingAccount.setAmount(receivingAccount.getAmount() + amountReceiving);

        Transaction currentTransaction = new Transaction();
        currentTransaction.setAmount(amount);
        currentTransaction.setCurrency(sendingAccount.getCurrency());
        currentTransaction.setSendingAccount(sendingAccount);
        currentTransaction.setReceivingAccount(receivingAccount);
        currentTransaction.setTimeOfTransaction(LocalDateTime.now());

        this.updateAccount(accountMapper.accountToAccountDto(receivingAccount));
        this.updateAccount(accountMapper.accountToAccountDto(sendingAccount));

        transactionService.createTransaction(transactionMapper.transactionToTransactionDto(currentTransaction));

        return amount;
    }

    // Backend Service 6: Get the amount on the account on selected date
    public double getAmountOnSelectedDate(AccountDTO accountDTO, LocalDate date) throws DateTimeException{

        Account account = accountMapper.accountDtoToAccount(accountDTO);

        if (date.isBefore(account.getOpeningDate())){
            throw new DateTimeException("Account did not exist on " + date);
        }
        if (date.isAfter(LocalDate.now())){
            throw new DateTimeException("The date " + date + " is in the future");
        }

        double currentAmount = account.getAmount();

        List <TransactionDTO> sentTransactions =
                transactionService.getSendingTransactionsOfAccount(accountMapper.accountToAccountDto(account));
        for (TransactionDTO curTransaction : sentTransactions){
            if (curTransaction.getTimeOfTransaction().isAfter(date.atStartOfDay()))
                currentAmount -= curTransaction.getAmount();
        }

        List <TransactionDTO> receivedTransactions = transactionService.getReceivingTransactionsOfAccount(
                accountMapper.accountToAccountDto(account));
        for (TransactionDTO curTransaction : receivedTransactions){
            CurrencyDTO currency = currencyService.getCurrencyById(curTransaction.getCurrency().getId());
            if (curTransaction.getTimeOfTransaction().isAfter(date.atStartOfDay())) {
                currentAmount = currentAmount * currency.getExchangeRateToUsd();
                currentAmount = currentAmount / account.getCurrency().getExchangeRateToUsd();
                currentAmount += curTransaction.getAmount();
            }
        }

        return currentAmount;
    }

}

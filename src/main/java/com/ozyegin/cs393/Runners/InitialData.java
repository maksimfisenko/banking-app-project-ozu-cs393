package com.ozyegin.cs393.Runners;

import com.ozyegin.cs393.Entities.*;
import com.ozyegin.cs393.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.time.LocalDate;
import java.util.ArrayList;

public class InitialData implements ApplicationRunner {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final DebitCardRepository debitCardRepository;
    @Autowired
    public InitialData(AccountRepository accountRepository,
                       UserRepository userRepository,
                       CurrencyRepository currencyRepository,
                       AccountTypeRepository accountTypeRepository,
                       DebitCardRepository debitCardRepository){
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.accountTypeRepository = accountTypeRepository;
        this.currencyRepository = currencyRepository;
        this.debitCardRepository = debitCardRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception{
        Currency dollar = new Currency();
        dollar.setName("Dollar");
        dollar.setSymbol('$');
        dollar.setExchangeRateToUsd(1);
        AccountType simpleType = new AccountType();
        simpleType.setName("Simple");
        simpleType.setDescription("Just very simple");
        simpleType.setDepositRate(0);

        User user = new User();
        user.setEmail("abc@abc.ru");
        user.setFirstName("ABC");
        user.setLastName("Ivanov");
        user.setPhoneNumber("+79372403405");

        Account account = new Account();
        account.setNumber(1L);
        account.setName("My Account");
        account.setAmount(1000);
        account.setOwner(user);
        account.setCurrency(dollar);
        account.setType(simpleType);
        account.setOpeningDate(LocalDate.now());

        DebitCard card = new DebitCard();
        card.setCardName("First card");
        card.setCardNumber("1234567812345678");
        card.setExpirationDate(LocalDate.ofYearDay(2024, 1));
        card.setAccount(account);

        ArrayList<DebitCard> cards = new ArrayList<>();
        account.setDebitCards(cards);

        ArrayList<Account> accounts = new ArrayList<>();
        user.setAccounts(accounts);

        userRepository.save(user);
        accountRepository.save(account);
        currencyRepository.save(dollar);
        accountTypeRepository.save(simpleType);
        debitCardRepository.save(card);
    }
}

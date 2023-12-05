package com.ozyegin.cs393.Runners;

import com.ozyegin.cs393.Entities.*;
import com.ozyegin.cs393.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class DataInitializer implements ApplicationRunner {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private AccountTypeRepository accountTypeRepository;
    @Autowired
    private DebitCardRepository debitCardRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception{
        Currency currencyTest = new Currency(null, "Dollar", '$', 1);
        AccountType accountTypeTest = new AccountType(null, "Simple",
                "Just very simple", 0);
        User userTest = new User(null, "John", "Gray",
                "+900000", "abc@abc.com", null);

        Account accountTest = new Account(null, "TestAccount",
                currencyTest, accountTypeTest, 1000, LocalDate.now(),
                userTest, null, null, null);

        DebitCard debitCardTest = new DebitCard(null, "1234567812345678",
                LocalDate.ofYearDay(2024, 1), "TestCard", accountTest);


        ArrayList<DebitCard> cards = new ArrayList<>();
        cards.add(debitCardTest);
        accountTest.setDebitCards(cards);

        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(accountTest);
        userTest.setAccounts(accounts);

        currencyRepository.save(currencyTest);
        accountTypeRepository.save(accountTypeTest);
        userRepository.save(userTest);
        accountRepository.save(accountTest);
        debitCardRepository.save(debitCardTest);
    }
}

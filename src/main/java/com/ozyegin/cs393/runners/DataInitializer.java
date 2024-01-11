package com.ozyegin.cs393.runners;

import com.ozyegin.cs393.dto.*;
import com.ozyegin.cs393.entities.*;
import com.ozyegin.cs393.mappers.*;
import com.ozyegin.cs393.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
@Profile("!test")
public class DataInitializer implements ApplicationRunner {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private AccountTypeService accountTypeService;
    @Autowired
    private DebitCardService debitCardService;

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountTypeMapper accountTypeMapper;
    @Autowired
    private CurrencyMapper currencyMapper;
    @Autowired
    private DebitCardMapper debitCardMapper;
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void run(ApplicationArguments args){

        Currency dollar = new Currency(null, "Dollar", '$', 1);
        CurrencyDTO currencyDTO1 = currencyService.createCurrency(
                currencyMapper.currencyToCurrencyDto(dollar));

        Currency turkishLira = new Currency(null, "Turkish Lira", '₺', 0.033);
        CurrencyDTO currencyDTO2 = currencyService.createCurrency(
                currencyMapper.currencyToCurrencyDto(turkishLira));

        AccountType accountTypeTest = new AccountType(null, "Simple",
                "Just very simple", 0);
        AccountTypeDTO accountTypeDTO = accountTypeService.createAccountType(
                accountTypeMapper.accountTypeToAccountTypeDto(accountTypeTest));


        User userTest1 = new User(null, "John", "Smith",
                "+900000", "abc@abc.com", null);
        UserDTO userDTO1 = userService.createUser(
                userMapper.userToUserDto(userTest1));

        User userTest2 = new User(null, "Test_2", "Test_2",
                "+900000", "abc@abc.com", null);
        UserDTO userDTO2 = userService.createUser(
                userMapper.userToUserDto(userTest2));

        Account accountTest1 = new Account(null, "TestAccount_1",
                currencyMapper.currencyDtoToCurrency(currencyDTO1),
                accountTypeMapper.accountTypeDtoToAccountType(accountTypeDTO), 1000, LocalDate.now(),
                userMapper.userDtoToUser(userDTO1), null, null, null);
        AccountDTO accountDTO = accountService.createAccount(
                accountMapper.accountToAccountDto(accountTest1));

        Account accountTest2 = new Account(null, "TestAccount_2",
                currencyMapper.currencyDtoToCurrency(currencyDTO1),
                accountTypeMapper.accountTypeDtoToAccountType(accountTypeDTO), 2000, LocalDate.now(),
                userMapper.userDtoToUser(userDTO1), null, null, null);
        AccountDTO accountDTO2 = accountService.createAccount(
                accountMapper.accountToAccountDto(accountTest2));

        Account accountTest3 = new Account(null, "TestAccount_3",
                currencyMapper.currencyDtoToCurrency(currencyDTO1),
                accountTypeMapper.accountTypeDtoToAccountType(accountTypeDTO), 2000, LocalDate.now(),
                userMapper.userDtoToUser(userDTO2), null, null, null);
        AccountDTO accountDTO3 = accountService.createAccount(
                accountMapper.accountToAccountDto(accountTest3));

        DebitCard debitCardTest = new DebitCard(null, "1234567812345678",
                LocalDate.ofYearDay(2029, 1), "TestCard",
                accountMapper.accountDtoToAccount(accountDTO));
        DebitCardDTO debitCardDTO = debitCardService.createDebitCard(
                debitCardMapper.debitCardtoDebitCardDto(debitCardTest));

        ArrayList<DebitCard> cards = new ArrayList<>();
        cards.add(debitCardTest);
        accountTest1.setDebitCards(cards);

        ArrayList<Account> accounts1 = new ArrayList<>();
        accounts1.add(accountTest1);
        userTest1.setAccounts(accounts1);

        ArrayList<Account> accounts2 = new ArrayList<>();
        accounts2.add(accountTest3);
        userTest2.setAccounts(accounts2);

    }
}

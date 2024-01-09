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

        Currency currencyTest = new Currency(null, "Dollar", '$', 1);
        CurrencyDTO currencyDTO = currencyService.createCurrency(
                currencyMapper.currencyToCurrencyDto(currencyTest));

        AccountType accountTypeTest = new AccountType(null, "Simple",
                "Just very simple", 0);
        AccountTypeDTO accountTypeDTO = accountTypeService.createAccountType(
                accountTypeMapper.accountTypeToAccountTypeDto(accountTypeTest));


        User userTest = new User(null, "John", "Gray",
                "+900000", "abc@abc.com", null);
        UserDTO userDTO = userService.createUser(
                userMapper.userToUserDto(userTest));

        Account accountTest = new Account(null, "TestAccount11111",
                currencyMapper.currencyDtoToCurrency(currencyDTO),
                accountTypeMapper.accountTypeDtoToAccountType(accountTypeDTO), 1000, LocalDate.now(),
                userMapper.userDtoToUser(userDTO), null, null, null);
        AccountDTO accountDTO = accountService.createAccount(
                accountMapper.accountToAccountDto(accountTest));

        DebitCard debitCardTest = new DebitCard(null, "1234567812345678",
                LocalDate.ofYearDay(2024, 1), "TestCard",
                accountMapper.accountDtoToAccount(accountDTO));
        DebitCardDTO debitCardDTO = debitCardService.createDebitCard(
                debitCardMapper.debitCardtoDebitCardDto(debitCardTest));

        ArrayList<DebitCard> cards = new ArrayList<>();
        cards.add(debitCardTest);
        accountTest.setDebitCards(cards);

        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(accountTest);
        userTest.setAccounts(accounts);

    }
}

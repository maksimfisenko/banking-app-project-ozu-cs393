package com.ozyegin.cs393.services;

import com.ozyegin.cs393.dto.AccountDTO;
import com.ozyegin.cs393.dto.CurrencyDTO;
import com.ozyegin.cs393.entities.Account;
import com.ozyegin.cs393.entities.Currency;
import com.ozyegin.cs393.mappers.AccountMapper;
import com.ozyegin.cs393.mappers.CurrencyMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private CurrencyMapper currencyMapper;


    @Test
    @Transactional
    public void testCreateAccount() {
        Account account = new Account(
                null, "testName", null, null, 100,
                LocalDate.now(), null, null, null, null);

        AccountDTO createdAccount = accountService.createAccount(accountMapper.accountToAccountDto(account));

        assertNotNull(createdAccount);
        assertNotNull(createdAccount.getNumber());
        assertEquals("testName", createdAccount.getName());
    }

    @Test
    @Transactional
    public void testGetAllAccounts() {

        Account account1 = new Account(
                null, "testName1", null, null, 100,
                LocalDate.now(), null, null, null, null);
        Account account2 = new Account(
                null, "testName2", null, null, 200,
                LocalDate.now(), null, null, null, null);

        AccountDTO createdAccount1 = accountService.createAccount(accountMapper.accountToAccountDto(account1));
        AccountDTO createdAccount2 = accountService.createAccount(accountMapper.accountToAccountDto(account2));

        List<AccountDTO> createdAccounts = accountService.getAllAccounts();

        assertEquals(2, createdAccounts.size());
        assertEquals("testName1", createdAccounts.get(0).getName());
        assertEquals(200, createdAccounts.get(1).getAmount());
    }

    @Test
    @Transactional
    public void testUpdateAccount() {

        Account account = new Account(
                null, "testName", null, null, 100,
                LocalDate.now(), null, null, null, null);

        AccountDTO createdAccount = accountService.createAccount(accountMapper.accountToAccountDto(account));
        createdAccount.setName("updatedTestAccount");
        AccountDTO updatedAccount = accountService.updateAccount(createdAccount);

        assertNotNull(updatedAccount);
        assertEquals(createdAccount.getNumber(), updatedAccount.getNumber());
        assertEquals("updatedTestAccount", updatedAccount.getName());
    }

    @Test
    @Transactional
    public void testDeleteAccountByNumber() {

        Account account1 = new Account(
                null, "testName1", null, null, 100,
                LocalDate.now(), null, null, null, null);
        Account account2 = new Account(
                null, "testName2", null, null, 200,
                LocalDate.now(), null, null, null, null);

        AccountDTO createdAccount1 = accountService.createAccount(accountMapper.accountToAccountDto(account1));
        AccountDTO createdAccount2 = accountService.createAccount(accountMapper.accountToAccountDto(account2));

        accountService.deleteAccountByNumber(createdAccount1.getNumber());

        assertEquals(1, accountService.getAllAccounts().size());
    }

    @Test
    @Transactional
    public void testDeleteAllAccounts() {
        Account account1 = new Account(
                null, "testName1", null, null, 100,
                LocalDate.now(), null, null, null, null);
        Account account2 = new Account(
                null, "testName2", null, null, 200,
                LocalDate.now(), null, null, null, null);

        AccountDTO createdAccount1 = accountService.createAccount(accountMapper.accountToAccountDto(account1));
        AccountDTO createdAccount2 = accountService.createAccount(accountMapper.accountToAccountDto(account2));

        accountService.deleteAllAccounts();

        assertEquals(0, accountService.getAllAccounts().size());
    }

    @Test
    @Transactional
    public void testChangeCurrency() {

        Currency oldCurrency = new Currency(null, "old", '$', 1);

        Account account = new Account(
                null, "testName", oldCurrency, null, 100,
                LocalDate.now(), null, null, null, null);

        AccountDTO createdAccount = accountService.createAccount(accountMapper.accountToAccountDto(account));

        CurrencyDTO newCurrency = currencyMapper.currencyToCurrencyDto(
                new Currency(null, "new", '$', 2));
        AccountDTO updatedAccount = accountService.changeCurrency(createdAccount, newCurrency);

        assertEquals(50, updatedAccount.getAmount());
        assertEquals("new", updatedAccount.getCurrency().getName());
    }

    @Test
    @Transactional
    public void testCloseAccount() {

        Account account1 = new Account(
                null, "testName", null, null, 100,
                LocalDate.now(), null, null, null, null);

        AccountDTO createdAccount1 =
                accountService.createAccount(accountMapper.accountToAccountDto(account1));

        Account account2 = new Account(
                null, "testName", null, null, 0,
                LocalDate.now(), null, null, null, null);

        AccountDTO createdAccount2 =
                accountService.createAccount(accountMapper.accountToAccountDto(account2));

        assertFalse(accountService.closeAccount(createdAccount1));
        assertTrue(accountService.closeAccount(createdAccount2));
    }

    @Test
    @Transactional
    public void testTransferMoney() {

        Currency currency = new Currency(null, "test", '$', 1);

        Account account1 = new Account(
                null, "testName1", currency, null, 100,
                LocalDate.now(), null, null, null, null);
        Account account2 = new Account(
                null, "testName2", currency, null, 200,
                LocalDate.now(), null, null, null, null);

        AccountDTO createdAccount1 = accountService.createAccount(accountMapper.accountToAccountDto(account1));
        AccountDTO createdAccount2 = accountService.createAccount(accountMapper.accountToAccountDto(account2));

        assertEquals(-1.0, accountService.transferMoney(createdAccount1, createdAccount2, 101));
        assertEquals(100, accountService.transferMoney(createdAccount1, createdAccount2, 100));
    }

    @Test
    @Transactional
    public void getAmountOnSelectedDay() throws DateTimeException {
        Account account = new Account(
                null, "testName", null, null, 100,
                LocalDate.now(), null, null, null, null);

        AccountDTO createdAccount = accountService.createAccount(accountMapper.accountToAccountDto(account));

        assertThrows(DateTimeException.class, () -> accountService.getAmountOnSelectedDate(
                createdAccount, LocalDate.of(2023, 1, 1)));
        assertEquals(100, accountService.getAmountOnSelectedDate(createdAccount, LocalDate.now()));
    }
}

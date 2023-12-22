package com.ozyegin.cs393.services;

import com.ozyegin.cs393.dto.AccountDTO;
import com.ozyegin.cs393.entities.Account;
import com.ozyegin.cs393.mappers.AccountMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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

//    @Test
//    @Transactional
//    public void testGetAllAccounts() {
//        Account createdAccount1 = accountService.createAccount(new Account(
//                null, "testName1", null, null, 100,
//                LocalDate.now(), null, null, null, null));
//        Account createdAccount2 = accountService.createAccount(new Account(
//                null, "testName2", null, null, 200,
//                LocalDate.now(), null, null, null, null));
//
//        List<Account> createdAccounts = accountService.getAllAccounts();
//
//        assertEquals(2, createdAccounts.size());
//        assertEquals("testName1", createdAccounts.get(0).getName());
//        assertEquals(200, createdAccounts.get(1).getAmount());
//    }
//
//    @Test
//    @Transactional
//    public void testUpdateAccount() {
//        Account createdAccount = accountService.createAccount(new Account(
//                null, "testName", null, null, 100,
//                LocalDate.now(), null, null, null, null));
//
//        createdAccount.setName("updatedTestAccount");
//        Account updatedAccount = accountService.updateAccount(createdAccount);
//
//        assertNotNull(updatedAccount);
//        assertEquals(createdAccount.getNumber(), updatedAccount.getNumber());
//        assertEquals("updatedTestAccount", updatedAccount.getName());
//    }
//
//    @Test
//    @Transactional
//    public void testDeleteAccountById() {
//        Account createdAccount1 = accountService.createAccount(new Account(
//                null, "testName1", null, null, 100,
//                LocalDate.now(), null, null, null, null));
//        Account createdAccount2 = accountService.createAccount(new Account(
//                null, "testName2", null, null, 200,
//                LocalDate.now(), null, null, null, null));
//
//        accountService.deleteAccountById(createdAccount1.getNumber());
//
//        assertEquals(1, accountService.getAllAccounts().size());
//    }
}

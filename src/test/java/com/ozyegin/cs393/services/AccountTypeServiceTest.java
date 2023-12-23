package com.ozyegin.cs393.services;

import com.ozyegin.cs393.dto.AccountTypeDTO;
import com.ozyegin.cs393.entities.AccountType;
import com.ozyegin.cs393.mappers.AccountTypeMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class AccountTypeServiceTest {
    @Autowired
    private AccountTypeService accountTypeService;
    @Autowired
    private AccountTypeMapper accountTypeMapper;

    @Test
    @Transactional
    public void testCreateAccountType() {

        AccountType accountType = new AccountType(null, "test", "test", 1);

        AccountTypeDTO createdAccountType =
                accountTypeService.createAccountType(
                        accountTypeMapper.accountTypeToAccountTypeDto(accountType));

        assertNotNull(createdAccountType);
        assertNotNull(createdAccountType.getId());
        assertEquals("test", createdAccountType.getName());
    }

    @Test
    @Transactional
    public void testGetAllAccountTypes() {

        AccountType accountType1 = new AccountType(null, "test1", "test", 1);
        AccountType accountType2 = new AccountType(null, "test2", "test", 2);

        AccountTypeDTO createdAccountType1 =
                accountTypeService.createAccountType(
                        accountTypeMapper.accountTypeToAccountTypeDto(accountType1));
        AccountTypeDTO createdAccountType2 =
                accountTypeService.createAccountType(
                        accountTypeMapper.accountTypeToAccountTypeDto(accountType2));

        List<AccountTypeDTO> createdAccountTypes = accountTypeService.getAllAccountTypes();

        assertEquals(2, createdAccountTypes.size());
        assertEquals("test1", createdAccountTypes.get(0).getName());
        assertEquals(2, createdAccountTypes.get(1).getDepositRate());
    }

    @Test
    @Transactional
    public void testGetAccountTypeById() {

        AccountType accountType = new AccountType(null, "test", "test", 1);

        AccountTypeDTO createdAccountType =
                accountTypeService.createAccountType(
                        accountTypeMapper.accountTypeToAccountTypeDto(accountType));
        AccountTypeDTO foundAccountType =
                accountTypeService.getAccountTypeById(createdAccountType.getId());

        assertEquals(createdAccountType.getId(), foundAccountType.getId());
        assertEquals(createdAccountType.getName(), foundAccountType.getName());
    }

    @Test
    @Transactional
    public void updateAccountType() {

        AccountType accountType = new AccountType(null, "test", "test", 1);

        AccountTypeDTO createdAccountType =
                accountTypeService.createAccountType(
                        accountTypeMapper.accountTypeToAccountTypeDto(accountType));

        createdAccountType.setName("updatedAccountType");
        AccountTypeDTO updatedAccountType = accountTypeService.updateAccountType(createdAccountType);

        assertNotNull(updatedAccountType);
        assertEquals(createdAccountType.getId(), updatedAccountType.getId());
        assertEquals("updatedAccountType", updatedAccountType.getName());

    }

    @Test
    @Transactional
    public void testDeleteAccountTypeById() {

        AccountType accountType1 = new AccountType(null, "test1", "test", 1);
        AccountType accountType2 = new AccountType(null, "test2", "test", 2);

        AccountTypeDTO createdAccountType1 =
                accountTypeService.createAccountType(
                        accountTypeMapper.accountTypeToAccountTypeDto(accountType1));
        AccountTypeDTO createdAccountType2 =
                accountTypeService.createAccountType(
                        accountTypeMapper.accountTypeToAccountTypeDto(accountType2));

        accountTypeService.deleteAccountTypeById(createdAccountType1.getId());

        assertEquals(1, accountTypeService.getAllAccountTypes().size());
    }

    @Test
    @Transactional
    public void testDeleteAllAccountTypes() {

        AccountType accountType1 = new AccountType(null, "test1", "test", 1);
        AccountType accountType2 = new AccountType(null, "test2", "test", 2);

        AccountTypeDTO createdAccountType1 =
                accountTypeService.createAccountType(
                        accountTypeMapper.accountTypeToAccountTypeDto(accountType1));
        AccountTypeDTO createdAccountType2 =
                accountTypeService.createAccountType(
                        accountTypeMapper.accountTypeToAccountTypeDto(accountType2));

        accountTypeService.deleteAllAccountTypes();

        assertEquals(0, accountTypeService.getAllAccountTypes().size());
    }
}

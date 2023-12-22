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

        AccountType accountType = new AccountType(
                null, "testAccountType", "testDescription", 1);

        AccountTypeDTO createdAccountType =
                accountTypeService.createAccountType(accountTypeMapper.accountTypeToAccountTypeDto(accountType));

        assertNotNull(createdAccountType);
        assertNotNull(createdAccountType.getId());
        assertEquals("testAccountType", createdAccountType.getName());
    }

//    @Test
//    @Transactional
//    public void testGetAllAccountTypes() {
//        AccountType createdAccountType1 = accountTypeService.createAccountType(
//                new AccountType(null, "testAccountType1",
//                        "testDescription1", 1));
//        AccountType createdAccountType2 = accountTypeService.createAccountType(
//                new AccountType(null, "testAccountType2",
//                        "testDescription2", 2));
//
//        List<AccountType> createdAccountTypes = accountTypeService.getAllAccountTypes();
//
//        assertEquals(2, createdAccountTypes.size());
//        assertEquals("testAccountType1", createdAccountTypes.get(0).getName());
//        assertEquals("testDescription2", createdAccountTypes.get(1).getDescription());
//    }
//
//    @Test
//    @Transactional
//    public void updateAccountType() {
//        AccountType createdAccountType = accountTypeService.createAccountType(
//                new AccountType(null, "testAccountType",
//                        "testDescription", 1));
//
//        createdAccountType.setName("updatedAccountType");
//        AccountType updatedAccountType = accountTypeService.updateAccountType(createdAccountType);
//
//        assertNotNull(updatedAccountType);
//        assertEquals(createdAccountType.getId(), updatedAccountType.getId());
//        assertEquals("updatedAccountType", updatedAccountType.getName());
//    }
//
//    @Test
//    @Transactional
//    public void testDeleteAccountTypeById() {
//        AccountType createdAccountType1 = accountTypeService.createAccountType(
//                new AccountType(null, "testAccountType1",
//                        "testDescription1", 1));
//        AccountType createdAccountType2 = accountTypeService.createAccountType(
//                new AccountType(null, "testAccountType2",
//                        "testDescription2", 2));
//
//        accountTypeService.deleteAccountTypeById(createdAccountType1.getId());
//
//        assertEquals(1, accountTypeService.getAllAccountTypes().size());
//    }
}

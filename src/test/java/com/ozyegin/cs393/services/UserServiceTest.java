package com.ozyegin.cs393.services;

import com.ozyegin.cs393.dto.AccountDTO;
import com.ozyegin.cs393.dto.UserDTO;
import com.ozyegin.cs393.entities.Account;
import com.ozyegin.cs393.entities.User;
import com.ozyegin.cs393.mappers.AccountMapper;
import com.ozyegin.cs393.mappers.UserMapper;
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
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AccountService accountService;
    @Autowired
    AccountMapper accountMapper;

    @Test
    @Transactional
    public void testCreateUser() {
        User user =
                new User(null, "testFirstName", "testLastName",
                        "testPhoneNumber", "test@test.com", null);
        UserDTO createdUser = userService.createUser(userMapper.userToUserDto(user));

        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        assertEquals("test@test.com", createdUser.getEmail());
    }

    @Test
    @Transactional
    public void testGetAllTransactions() {
        User user1 =
                new User(null, "testFirstName1", "testLastName1",
                        "testPhoneNumber1", "test1@test.com", null);
        User user2 =
                new User(null, "testFirstName2", "testLastName2",
                        "testPhoneNumber2", "test2@test.com", null);

        UserDTO createdUser1 = userService.createUser(userMapper.userToUserDto(user1));
        UserDTO createdUser2 = userService.createUser(userMapper.userToUserDto(user2));

        List<UserDTO> createdUsers = userService.findAllUsers();

        assertEquals(2, createdUsers.size());
        assertEquals("testPhoneNumber1", createdUsers.get(0).getPhoneNumber());
        assertEquals("testPhoneNumber2", createdUsers.get(1).getPhoneNumber());
    }

    @Test
    @Transactional
    public void testUpdateUser() {
        User user =
                new User(null, "testFirstName", "testLastName",
                        "testPhoneNumber", "test@test.com", null);
        UserDTO createdUser = userService.createUser(userMapper.userToUserDto(user));

        createdUser.setPhoneNumber("updatedPhoneNumber");
        UserDTO updatedUser = userService.updateUser(createdUser);

        assertNotNull(updatedUser);
        assertEquals(createdUser.getId(), updatedUser.getId());
        assertEquals("updatedPhoneNumber", updatedUser.getPhoneNumber());
    }

    @Test
    @Transactional
    public void testDeleteUser() {
        User user1 =
                new User(null, "testFirstName1", "testLastName1",
                        "testPhoneNumber1", "test1@test.com", null);
        User user2 =
                new User(null, "testFirstName2", "testLastName2",
                        "testPhoneNumber2", "test2@test.com", null);

        UserDTO createdUser1 = userService.createUser(userMapper.userToUserDto(user1));
        UserDTO createdUser2 = userService.createUser(userMapper.userToUserDto(user2));

        userService.deleteUserById(createdUser1.getId());

        assertEquals(1, userService.findAllUsers().size());
    }

    @Test
    @Transactional
    public void testDeleteUsersWithNoAccounts(){
        User user1 =
                new User(null, "testFirstName1", "testLastName1",
                        "testPhoneNumber1", "test1@test.com", null);
        User user2 =
                new User(null, "testFirstName2", "testLastName2",
                        "testPhoneNumber2", "test2@test.com", null);

        UserDTO createdUser1 = userService.createUser(userMapper.userToUserDto(user1));
        UserDTO createdUser2 = userService.createUser(userMapper.userToUserDto(user2));

        Account account = new Account(
                null, "testName", null, null, 100,
                LocalDate.now(), user1, null, null, null);

        AccountDTO createdAccount = accountService.createAccount(accountMapper.accountToAccountDto(account));

        userService.deleteUsersWithNoAccounts();
        assertEquals(userService.findAllUsers().size(), 1);

    }
}

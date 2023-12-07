package com.ozyegin.cs393.services;

import com.ozyegin.cs393.entities.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void testCreateUser() {
        User createdUser = userService.createUser(
                new User(null, "testFirstName", "testLastName",
                        "testPhoneNumber", "test@test.com", null));

        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        assertEquals("test@test.com", createdUser.getEmail());
    }

    @Test
    @Transactional
    public void testGetAllTransactions() {
        User createdUser1 = userService.createUser(
                new User(null, "testFirstName1", "testLastName1",
                        "testPhoneNumber1", "test1@test.com", null));
        User createdUser2 = userService.createUser(
                new User(null, "testFirstName2", "testLastName2",
                        "testPhoneNumber2", "test2@test.com", null));

        List<User> createdUsers = userService.findAllUsers();

        assertEquals(2, createdUsers.size());
        assertEquals("testPhoneNumber1", createdUsers.get(0).getPhoneNumber());
        assertEquals("testPhoneNumber2", createdUsers.get(1).getPhoneNumber());
    }

    @Test
    @Transactional
    public void testUpdateUser() {
        User createdUser = userService.createUser(
                new User(null, "testFirstName", "testLastName",
                        "testPhoneNumber", "test@test.com", null));

        createdUser.setPhoneNumber("updatedPhoneNumber");
        User updatedUser = userService.updateUser(createdUser);

        assertNotNull(updatedUser);
        assertEquals(createdUser.getId(), updatedUser.getId());
        assertEquals("updatedPhoneNumber", updatedUser.getPhoneNumber());
    }

    @Test
    @Transactional
    public void testDeleteUser() {
        User createdUser1 = userService.createUser(
                new User(null, "testFirstName1", "testLastName1",
                        "testPhoneNumber1", "test1@test.com", null));
        User createdUser2 = userService.createUser(
                new User(null, "testFirstName2", "testLastName2",
                        "testPhoneNumber2", "test2@test.com", null));

        userService.deleteUserById(createdUser1.getId());

        assertEquals(1, userService.findAllUsers().size());
    }
}

package com.ozyegin.cs393.services;

import com.ozyegin.cs393.entities.User;
import com.ozyegin.cs393.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // CRUD operations

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User with id " + id + " not found"));
    }

    public User updateUser(User updatedUser) {

        Long id = updatedUser.getId();

        User user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User with id " + id + " not found"));

        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setEmail(updatedUser.getEmail());
        user.setAccounts(updatedUser.getAccounts());

        return userRepository.save(updatedUser);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    // Backend Service 4: Delete Users With No Accounts
    public void deleteUsersWithNoAccounts() {
        userRepository.deleteUsersWithNoAccounts();
    }
}

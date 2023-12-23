package com.ozyegin.cs393.services;

import com.ozyegin.cs393.dto.DebitCardDTO;
import com.ozyegin.cs393.dto.UserDTO;
import com.ozyegin.cs393.entities.Account;
import com.ozyegin.cs393.entities.DebitCard;
import com.ozyegin.cs393.entities.User;
import com.ozyegin.cs393.mappers.DebitCardMapper;
import com.ozyegin.cs393.mappers.UserMapper;
import com.ozyegin.cs393.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DebitCardMapper debitCardMapper;

    // CRUD operations

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.userDtoToUser(userDTO);
        user = userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    public List<UserDTO> findAllUsers() {
        List <User> users = userRepository.findAll();
        return userMapper.UsersToUserDtos(users);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User with id " + id + " not found"));
    }

    public UserDTO updateUser(UserDTO updatedUserDto) {
        User user = userMapper.userDtoToUser(updatedUserDto);
        user = userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    // Backend Service 4: Delete Users With No Accounts
    public void deleteUsersWithNoAccounts() {
        userRepository.deleteUsersWithNoAccounts();
    }

    // Backend Service 9: Get all cards belonging to the User
    public List<DebitCardDTO> getAllUserDebitCards(Long id){
        User user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User with id " + id + " not found"));
        List <Account> accounts = user.getAccounts();
        List <DebitCard> debitCards = new ArrayList<DebitCard>();
        for (Account curAccount : accounts)
            debitCards.addAll(curAccount.getDebitCards());
        return debitCardMapper.debutCardsToDebitCardDtos(debitCards);
    }
}

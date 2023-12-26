package com.ozyegin.cs393.controllers;


import com.ozyegin.cs393.dto.DebitCardDTO;
import com.ozyegin.cs393.dto.UserDTO;
import com.ozyegin.cs393.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){

        List<UserDTO> userDTOs = userService.findAllUsers();

        if (userDTOs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(userDTOs, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {

        UserDTO createdUserDTO = userService.createUser(userDTO);

        if (createdUserDTO == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){

        UserDTO userDTO = userService.getUserById(id);

        if (userDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> handlePostOnExistingAccount() {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updatedUser(@RequestBody UserDTO userDTO){

        UserDTO updatedUser = userService.updateUser(userDTO);

        if (updatedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllUsers(){
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Service 4: Delete Users With No Account
    @DeleteMapping("/deleteWithNoAccount")
    public ResponseEntity<Void> deleteUsersWithNoAccounts(){
        userService.deleteUsersWithNoAccounts();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Service 9: Get All User's Debit Cards
    @GetMapping("/cards/{id}")
    public ResponseEntity<List<DebitCardDTO>> getAllUserDebitCards(@PathVariable Long id){

        List<DebitCardDTO> debitCardDTOS = userService.getAllUserDebitCards(id);

        if (debitCardDTOS.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(debitCardDTOS, HttpStatus.OK);
        }
    }
}

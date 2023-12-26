package com.ozyegin.cs393.controllers;

import com.ozyegin.cs393.dto.AccountTypeDTO;
import com.ozyegin.cs393.services.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountTypes")
public class AccountTypeController {

    @Autowired
    private AccountTypeService accountTypeService;

    @GetMapping
    private ResponseEntity<List<AccountTypeDTO>> getAllAccountTypes() {

        List<AccountTypeDTO> accountTypes = accountTypeService.getAllAccountTypes();

        if (accountTypes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(accountTypes, HttpStatus.OK);
        }
    }

    @PostMapping
    private ResponseEntity<AccountTypeDTO> createAccountType(@RequestBody AccountTypeDTO accountTypeDTO) {

        AccountTypeDTO accountType = accountTypeService.createAccountType(accountTypeDTO);

        if (accountType == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(accountType, HttpStatus.CREATED);
        }
    }

    @GetMapping("/{accountTypeId}")
    public ResponseEntity<AccountTypeDTO> getAccountTypeById(@PathVariable Long accountTypeId) {

        AccountTypeDTO accountTypeDTO = accountTypeService.getAccountTypeById(accountTypeId);

        if (accountTypeDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(accountTypeDTO, HttpStatus.OK);
        }
    }

    @PostMapping("/{accountTypeId}")
    public ResponseEntity<AccountTypeDTO> handlePostOnExistingAccountType() {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PutMapping("/{accountTypeId}")
    public ResponseEntity<AccountTypeDTO> updateAccountType(@RequestBody AccountTypeDTO accountTypeDTO) {

        AccountTypeDTO updatedAccountType = accountTypeService.updateAccountType(accountTypeDTO);

        if (updatedAccountType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedAccountType, HttpStatus.OK);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllAccountTypes() {
        accountTypeService.deleteAllAccountTypes();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountTypeById(@PathVariable Long id) {
        accountTypeService.deleteAccountTypeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

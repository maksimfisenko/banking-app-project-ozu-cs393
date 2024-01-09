package com.ozyegin.cs393.services;

import com.ozyegin.cs393.dto.AccountTypeDTO;
import com.ozyegin.cs393.entities.AccountType;
import com.ozyegin.cs393.mappers.AccountTypeMapper;
import com.ozyegin.cs393.repositories.AccountTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTypeService {
    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private AccountTypeMapper accountTypeMapper;

    // CRUD Operations

    public AccountTypeDTO createAccountType(AccountTypeDTO accountTypeDTO) {
        AccountType accountType = accountTypeMapper.accountTypeDtoToAccountType(accountTypeDTO);
        accountType = accountTypeRepository.save(accountType);
        return accountTypeMapper.accountTypeToAccountTypeDto(accountType);
    }

    public List<AccountTypeDTO> getAllAccountTypes() {
        List<AccountType> accountTypes = accountTypeRepository.findAll();
        return accountTypeMapper.accountTypesToAccountTypeDtos(accountTypes);
    }

    public AccountTypeDTO getAccountTypeById(Long id) {
        AccountType accountType = accountTypeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Account Type with id " + id + " not found"));
        return accountTypeMapper.accountTypeToAccountTypeDto(accountType);
    }

    public AccountTypeDTO updateAccountType(AccountTypeDTO updatedAccountTypeDTO) {
        AccountType accountType = accountTypeMapper.accountTypeDtoToAccountType(updatedAccountTypeDTO);
        accountType = accountTypeRepository.save(accountType);
        return accountTypeMapper.accountTypeToAccountTypeDto(accountType);
    }

    public void deleteAccountTypeById(Long accountTypeId) {
        accountTypeRepository.deleteById(accountTypeId);
    }

    public void deleteAllAccountTypes() {
        accountTypeRepository.deleteAll();
    }
}

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

    public List<AccountType> getAllAccountTypes() {
        return accountTypeRepository.findAll();
    }

    public AccountType updateAccountType(AccountType updatedAccountType) {

        Long accountTypeId = updatedAccountType.getId();

        AccountType accountType = accountTypeRepository.findById(accountTypeId).orElseThrow(() ->
                new EntityNotFoundException("Account Type with id " + accountTypeId + " not found"));

        accountType.setName(updatedAccountType.getName());
        accountType.setDescription(updatedAccountType.getDescription());
        accountType.setDepositRate(updatedAccountType.getDepositRate());

        return accountTypeRepository.save(updatedAccountType);
    }

    public void deleteAccountTypeById(Long id) {
        accountTypeRepository.deleteById(id);
    }
}

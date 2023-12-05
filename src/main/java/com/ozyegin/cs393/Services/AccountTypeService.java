package com.ozyegin.cs393.Services;

import com.ozyegin.cs393.Entities.AccountType;
import com.ozyegin.cs393.Repositories.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTypeService {
    @Autowired
    private AccountTypeRepository accountTypeRepository;

    public AccountType createAccountType(AccountType accountType) {
        return accountTypeRepository.save(accountType);
    }

    public List<AccountType> getAllAccountTypes() {
        return accountTypeRepository.findAll();
    }

    public AccountType updateAccountType(AccountType updatedAccountType) {
        return accountTypeRepository.save(updatedAccountType);
    }

    public void deleteAccountTypeById(Long id) {
        accountTypeRepository.deleteById(id);
    }
}

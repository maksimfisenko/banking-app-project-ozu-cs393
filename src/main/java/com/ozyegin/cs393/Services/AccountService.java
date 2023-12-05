package com.ozyegin.cs393.Services;

import com.ozyegin.cs393.Entities.Account;
import com.ozyegin.cs393.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account updateAccount(Account updatedAccount) {
        return accountRepository.save(updatedAccount);
    }

    public void deleteAccountById(Long accountNumber) {
        accountRepository.deleteById(accountNumber);
    }
}

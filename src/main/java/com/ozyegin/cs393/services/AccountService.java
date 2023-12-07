package com.ozyegin.cs393.services;

import com.ozyegin.cs393.entities.Account;
import com.ozyegin.cs393.repositories.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
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

        Long accountNumber = updatedAccount.getNumber();
        Account account = accountRepository.findById(accountNumber).orElseThrow(() ->
                new EntityNotFoundException("Account with number " + accountNumber + " not found"));

        account.setDebitCards(updatedAccount.getDebitCards());
        account.setAmount(updatedAccount.getAmount());
        account.setName(updatedAccount.getName());
        account.setOwner(updatedAccount.getOwner());
        account.setCurrency(updatedAccount.getCurrency());
        account.setType(updatedAccount.getType());
        account.setOpeningDate(updatedAccount.getOpeningDate());
        account.setReceivedTransactions(updatedAccount.getReceivedTransactions());
        account.setSentTransactions(updatedAccount.getSentTransactions());

        return accountRepository.save(account);
    }

    public void deleteAccountById(Long accountNumber) {
        accountRepository.deleteById(accountNumber);
    }

    public void deleteAllAccounts() {
        accountRepository.deleteAll();
    }
}

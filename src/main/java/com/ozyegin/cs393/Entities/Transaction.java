package com.ozyegin.cs393.Entities;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
public class Transaction {

    public Transaction() {
    }

    @Id
    private long id;
    private Account SendingAccount;
    private Account ReceivingAccount;

    public void setId(long id) {
        this.id = id;
    }

    public void setSendingAccount(Account sendingAccount) {
        SendingAccount = sendingAccount;
    }

    public void setReceivingAccount(Account receivingAccount) {
        ReceivingAccount = receivingAccount;
    }

    public void setTimeOfTransaction(LocalDateTime timeOfTransaction) {
        TimeOfTransaction = timeOfTransaction;
    }

    public void setCurrency(com.ozyegin.cs393.Entities.Currency currency) {
        Currency = currency;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    private LocalDateTime TimeOfTransaction;
    private Currency Currency;
    private int Amount;

    public long getId() {
        return id;
    }

    public Account getSendingAccount() {
        return SendingAccount;
    }

    public Account getReceivingAccount() {
        return ReceivingAccount;
    }

    public LocalDateTime getTimeOfTransaction() {
        return TimeOfTransaction;
    }

    public com.ozyegin.cs393.Entities.Currency getCurrency() {
        return Currency;
    }

    public int getAmount() {
        return Amount;
    }
}

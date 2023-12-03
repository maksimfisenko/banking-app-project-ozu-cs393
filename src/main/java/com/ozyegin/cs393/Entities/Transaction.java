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
    private Account sendingAccount;
    private Account receivingAccount;
    private LocalDateTime timeOfTransaction;
    private Currency currency;
    private int amount;

    public void setId(long id) {
        this.id = id;
    }

    public void setSendingAccount(Account sendingAccount) {
        this.sendingAccount = sendingAccount;
    }

    public void setReceivingAccount(Account receivingAccount) {
        this.receivingAccount = receivingAccount;
    }

    public void setTimeOfTransaction(LocalDateTime timeOfTransaction) {
        this.timeOfTransaction = timeOfTransaction;
    }

    public void setCurrency(com.ozyegin.cs393.Entities.Currency currency) {
        this.currency = currency;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getId() {
        return this.id;
    }

    public Account getSendingAccount() {
        return this.sendingAccount;
    }

    public Account getReceivingAccount() {
        return this.receivingAccount;
    }

    public LocalDateTime getTimeOfTransaction() {
        return this.timeOfTransaction;
    }

    public com.ozyegin.cs393.Entities.Currency getCurrency() {
        return this.currency;
    }

    public int getAmount() {
        return this.amount;
    }
}

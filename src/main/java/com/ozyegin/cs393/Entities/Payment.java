package com.ozyegin.cs393.Entities;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    private long id;
    private DebitCard sendingCard;
    private Account receivingAccount;
    private LocalDateTime timeOfTransaction;
    private Currency currency;
    private int amount;
    public Payment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSendingCard(DebitCard sendingCard) {
        this.sendingCard = sendingCard;
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

    public DebitCard getSendingCard() {
        return this.sendingCard;
    }

    public Account getReceivingAccount() {
        return this.receivingAccount;
    }

    public LocalDateTime getTimeOfTransaction() {
        return this.timeOfTransaction;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public int getAmount() {
        return this.amount;
    }

}

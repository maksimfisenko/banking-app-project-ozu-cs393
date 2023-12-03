package com.ozyegin.cs393.Entities;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Payment {
    public Payment() {
    }
    @Id
    private long id;
    private DebitCard SendingCard;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSendingCard(DebitCard sendingCard) {
        SendingCard = sendingCard;
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

    public DebitCard getSendingCard() {
        return SendingCard;
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

    private Account ReceivingAccount;
    private LocalDateTime TimeOfTransaction;
    private Currency Currency;
    private int Amount;
}

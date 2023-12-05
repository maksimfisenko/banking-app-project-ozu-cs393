package com.ozyegin.cs393.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sendingAccountId")
    private Account sendingAccount;
    @ManyToOne
    @JoinColumn(name = "receivingAccountId")
    private Account receivingAccount;
    @Column(nullable = false)
    private LocalDateTime timeOfTransaction;
    @ManyToOne
    @JoinColumn(name = "currencyId")
    private Currency currency;
    @Column(nullable = false)
    private double amount;

    public Transaction() {
    }

    public void setId(Long id) {
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

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getId() {
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

    public Currency getCurrency() {
        return this.currency;
    }

    public double getAmount() {
        return this.amount;
    }
}

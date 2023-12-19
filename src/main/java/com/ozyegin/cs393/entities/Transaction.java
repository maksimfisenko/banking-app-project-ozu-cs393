package com.ozyegin.cs393.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

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

    public Transaction(Long id, Account sendingAccount, Account receivingAccount,
                       LocalDateTime timeOfTransaction, Currency currency, double amount) {
        this.id = id;
        this.sendingAccount = sendingAccount;
        this.receivingAccount = receivingAccount;
        this.timeOfTransaction = timeOfTransaction;
        this.currency = currency;
        this.amount = amount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(id, that.id) && Objects.equals(sendingAccount, that.sendingAccount) && Objects.equals(receivingAccount, that.receivingAccount) && Objects.equals(timeOfTransaction, that.timeOfTransaction) && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sendingAccount, receivingAccount, timeOfTransaction, currency, amount);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", sendingAccount=" + sendingAccount +
                ", receivingAccount=" + receivingAccount +
                ", timeOfTransaction=" + timeOfTransaction +
                ", currency=" + currency +
                ", amount=" + amount +
                '}';
    }
}

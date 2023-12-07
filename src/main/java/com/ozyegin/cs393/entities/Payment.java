package com.ozyegin.cs393.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sendingCardNumber")
    private DebitCard sendingCard;
    @ManyToOne
    @JoinColumn(name = "receivingAccountId")
    private Account receivingAccount;
    @Column(nullable = false)
    private LocalDateTime timeOfPayment;
    @ManyToOne
    @JoinColumn(name = "currencyId")
    private Currency currency;
    @Column(nullable = false)
    private double amount;

    public Payment() {
    }

    public Payment(Long id, DebitCard sendingCard, Account receivingAccount,
                   LocalDateTime timeOfPayment, Currency currency, double amount) {
        this.id = id;
        this.sendingCard = sendingCard;
        this.receivingAccount = receivingAccount;
        this.timeOfPayment = timeOfPayment;
        this.currency = currency;
        this.amount = amount;
    }

    public Long getId() {
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

    public void setTimeOfPayment(LocalDateTime timeOfTransaction) {
        this.timeOfPayment = timeOfTransaction;
    }

    public void setCurrency(com.ozyegin.cs393.entities.Currency currency) {
        this.currency = currency;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public DebitCard getSendingCard() {
        return this.sendingCard;
    }

    public Account getReceivingAccount() {
        return this.receivingAccount;
    }

    public LocalDateTime getTimeOfPayment() {
        return this.timeOfPayment;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public double getAmount() {
        return this.amount;
    }

}

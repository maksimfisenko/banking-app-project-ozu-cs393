package com.ozyegin.cs393.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Account {
    @Id
    private Long number;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "currencyId")
    private Currency currency;
    @ManyToOne
    @JoinColumn(name = "accountType")
    private AccountType type;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private LocalDate openingDate;
    @ManyToOne
    @JoinColumn(name = "ownerId")
    private User owner;
    @OneToMany(mappedBy = "sendingAccount")
    private List<Transaction> sentTransactions;
    @OneToMany(mappedBy = "receivingAccount")
    private List<Transaction> receivedTransactions;
    @OneToMany(mappedBy = "account")
    private List<DebitCard> debitCards;


    public Account() {
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public List<Transaction> getSentTransactions() {
        return sentTransactions;
    }

    public void setSentTransactions(List<Transaction> sentTransactions) {
        this.sentTransactions = sentTransactions;
    }

    public List<Transaction> getReceivedTransactions() {
        return receivedTransactions;
    }

    public void setReceivedTransactions(List<Transaction> receivedTransactions) {
        this.receivedTransactions = receivedTransactions;
    }

    public List<DebitCard> getDebitCards() {
        return debitCards;
    }

    public void setDebitCards(List<DebitCard> debitCards) {
        this.debitCards = debitCards;
    }
}

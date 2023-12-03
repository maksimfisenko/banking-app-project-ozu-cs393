package com.ozyegin.cs393.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Account {
    @Id
    private Long number;
    private String name;
    @ManyToOne
    private Currency currency;
    @ManyToOne
    private AccountType type;
    private int amount;
    @ManyToOne
    private User owner;
    private LocalDate openingDate;

    public Account(Long number, String name, Currency currency, AccountType type, int amount, User owner,
                   LocalDate openingDate) {
        this.number = number;
        this.name = name;
        this.currency = currency;
        this.type = type;
        this.amount = amount;
        this.owner = owner;
        this.openingDate = openingDate;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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
}

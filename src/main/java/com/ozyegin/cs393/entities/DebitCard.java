package com.ozyegin.cs393.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class DebitCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private LocalDate expirationDate;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    public DebitCard() {
    }

    public DebitCard(Long id, String cardNumber, LocalDate expirationDate, String cardName, Account account) {
        this.id = id;
        this.number = cardNumber;
        this.expirationDate = expirationDate;
        this.name = cardName;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getNumber() {
        return this.number;
    }

    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    public String getName() {
        return this.name;
    }

    public Account getAccount() {
        return this.account;
    }
}

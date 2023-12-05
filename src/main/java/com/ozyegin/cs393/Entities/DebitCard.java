package com.ozyegin.cs393.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class DebitCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String cardNumber;
    @Column(nullable = false)
    private LocalDate expirationDate;
    @Column(nullable = false)
    private String cardName;
    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    public DebitCard(Long id, String cardNumber, LocalDate expirationDate, String cardName, Account account) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardName = cardName;
        this.account = account;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    public String getCardName() {
        return this.cardName;
    }

    public Account getAccount() {
        return this.account;
    }
}

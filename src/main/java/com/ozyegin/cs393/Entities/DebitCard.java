package com.ozyegin.cs393.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
public class DebitCard {
    @Id
    private Long cardNumber;
    @Column(nullable = false)
    private LocalDate expirationDate;
    @Column(nullable = false)
    private String cardName;
    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    public DebitCard() {
    }

    public void setCardNumber(long cardNumber) {
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

    public Long getCardNumber() {
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

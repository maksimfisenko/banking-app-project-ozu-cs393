package com.ozyegin.cs393.Entities;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
public class DebitCard {
    public DebitCard() {
    }
    @Id
    private long cardNumber;
    private LocalDate expirationDate;
    private String cardName;
    private Account account;

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

    public long getCardNumber() {
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

package com.ozyegin.cs393.Entities;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
public class DebitCard {
    public DebitCard() {
    }
    @Id
    private long CardNumber;
    private LocalDate ExpirationDate;
    private String CardName;
    private Account Account;

    public void setCardNumber(long cardNumber) {
        CardNumber = cardNumber;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        ExpirationDate = expirationDate;
    }

    public void setCardName(String cardName) {
        CardName = cardName;
    }

    public void setAccount(com.ozyegin.cs393.Entities.Account account) {
        Account = account;
    }

    public long getCardNumber() {
        return CardNumber;
    }

    public LocalDate getExpirationDate() {
        return ExpirationDate;
    }

    public String getCardName() {
        return CardName;
    }

    public com.ozyegin.cs393.Entities.Account getAccount() {
        return Account;
    }
}

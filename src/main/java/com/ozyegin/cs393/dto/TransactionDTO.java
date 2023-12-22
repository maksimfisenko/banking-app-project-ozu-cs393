package com.ozyegin.cs393.dto;

import com.ozyegin.cs393.entities.Account;
import com.ozyegin.cs393.entities.Currency;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

public class TransactionDTO {
    private Long id;
    private Long sendingAccountNumber;
    private Long receivingAccountNumber;
    private LocalDateTime timeOfTransaction;
    private Long currencyId;
    private double amount;

    public TransactionDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSendingAccountNumber() {
        return sendingAccountNumber;
    }

    public void setSendingAccountNumber(Long sendingAccountNumber) {
        this.sendingAccountNumber = sendingAccountNumber;
    }

    public Long getReceivingAccountNumber() {
        return receivingAccountNumber;
    }

    public void setReceivingAccountNumber(Long receivingAccountNumber) {
        this.receivingAccountNumber = receivingAccountNumber;
    }

    public LocalDateTime getTimeOfTransaction() {
        return timeOfTransaction;
    }

    public void setTimeOfTransaction(LocalDateTime timeOfTransaction) {
        this.timeOfTransaction = timeOfTransaction;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDTO that = (TransactionDTO) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(id, that.id) && Objects.equals(sendingAccountNumber, that.sendingAccountNumber) && Objects.equals(receivingAccountNumber, that.receivingAccountNumber) && Objects.equals(timeOfTransaction, that.timeOfTransaction) && Objects.equals(currencyId, that.currencyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sendingAccountNumber, receivingAccountNumber, timeOfTransaction, currencyId, amount);
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + id +
                ", sendingAccountNumber=" + sendingAccountNumber +
                ", receivingAccountNumber=" + receivingAccountNumber +
                ", timeOfTransaction=" + timeOfTransaction +
                ", currencyId=" + currencyId +
                ", amount=" + amount +
                '}';
    }
}

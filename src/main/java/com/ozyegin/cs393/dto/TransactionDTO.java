package com.ozyegin.cs393.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class TransactionDTO {
    private Long id;
    private AccountDTO sendingAccount;
    private AccountDTO receivingAccount;
    private LocalDateTime timeOfTransaction;
    private CurrencyDTO currency;
    private double amount;

    public TransactionDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountDTO getSendingAccount() {
        return sendingAccount;
    }

    public void setSendingAccount(AccountDTO sendingAccount) {
        this.sendingAccount = sendingAccount;
    }

    public AccountDTO getReceivingAccount() {
        return receivingAccount;
    }

    public void setReceivingAccount(AccountDTO receivingAccount) {
        this.receivingAccount = receivingAccount;
    }

    public LocalDateTime getTimeOfTransaction() {
        return timeOfTransaction;
    }

    public void setTimeOfTransaction(LocalDateTime timeOfTransaction) {
        this.timeOfTransaction = timeOfTransaction;
    }

    public CurrencyDTO getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyDTO currency) {
        this.currency = currency;
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
        return Double.compare(that.amount, amount) == 0 && Objects.equals(id, that.id) && Objects.equals(sendingAccount, that.sendingAccount) && Objects.equals(receivingAccount, that.receivingAccount) && Objects.equals(timeOfTransaction, that.timeOfTransaction) && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sendingAccount, receivingAccount, timeOfTransaction, currency, amount);
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + id +
                ", sendingAccountNumber=" + sendingAccount +
                ", receivingAccountNumber=" + receivingAccount +
                ", timeOfTransaction=" + timeOfTransaction +
                ", currencyId=" + currency +
                ", amount=" + amount +
                '}';
    }
}

package com.ozyegin.cs393.dto;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class PaymentDTO {
    private Long id;
    private DebitCardDTO sendingCard;
    private AccountDTO receivingAccount;
    private LocalDateTime timeOfPayment;
    private CurrencyDTO currency;
    private double amount;

    public PaymentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DebitCardDTO getSendingCard() {
        return sendingCard;
    }

    public void setSendingCard(DebitCardDTO sendingCard) {
        this.sendingCard = sendingCard;
    }

    public AccountDTO getReceivingAccountNumb() {
        return receivingAccount;
    }

    public void setReceivingAccountNumber(AccountDTO receivingAccount) {
        this.receivingAccount = receivingAccount;
    }

    public LocalDateTime getTimeOfPayment() {
        return timeOfPayment;
    }

    public void setTimeOfPayment(LocalDateTime timeOfPayment) {
        this.timeOfPayment = timeOfPayment;
    }

    public CurrencyDTO getCurrency() {
        return currency;
    }

    public void setCurrencyId(CurrencyDTO currency) {
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
        PaymentDTO that = (PaymentDTO) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(id, that.id) && Objects.equals(sendingCard, that.sendingCard) && Objects.equals(receivingAccount, that.receivingAccount) && Objects.equals(timeOfPayment, that.timeOfPayment) && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sendingCard, receivingAccount, timeOfPayment, currency, amount);
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id=" + id +
                ", sendingCardId=" + sendingCard +
                ", receivingAccountNumber=" + receivingAccount +
                ", timeOfPayment=" + timeOfPayment +
                ", currencyId=" + currency +
                ", amount=" + amount +
                '}';
    }
}

package com.ozyegin.cs393.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class PaymentDTO {
    private Long id;
    private Long sendingCardId;
    private Long receivingAccountNumber;
    private LocalDateTime timeOfPayment;
    private Long currencyId;
    private double amount;

    public PaymentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSendingCardId() {
        return sendingCardId;
    }

    public void setSendingCardId(Long sendingCardId) {
        this.sendingCardId = sendingCardId;
    }

    public Long getReceivingAccountNumber() {
        return receivingAccountNumber;
    }

    public void setReceivingAccountNumber(Long receivingAccountNumber) {
        this.receivingAccountNumber = receivingAccountNumber;
    }

    public LocalDateTime getTimeOfPayment() {
        return timeOfPayment;
    }

    public void setTimeOfPayment(LocalDateTime timeOfPayment) {
        this.timeOfPayment = timeOfPayment;
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
        PaymentDTO that = (PaymentDTO) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(id, that.id) && Objects.equals(sendingCardId, that.sendingCardId) && Objects.equals(receivingAccountNumber, that.receivingAccountNumber) && Objects.equals(timeOfPayment, that.timeOfPayment) && Objects.equals(currencyId, that.currencyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sendingCardId, receivingAccountNumber, timeOfPayment, currencyId, amount);
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id=" + id +
                ", sendingCardId=" + sendingCardId +
                ", receivingAccountNumber=" + receivingAccountNumber +
                ", timeOfPayment=" + timeOfPayment +
                ", currencyId=" + currencyId +
                ", amount=" + amount +
                '}';
    }
}

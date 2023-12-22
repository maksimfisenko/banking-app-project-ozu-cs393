package com.ozyegin.cs393.dto;

import java.time.LocalDate;
import java.util.Objects;

public class AccountDTO {
    private Long number;
    private String name;
    private Long currencyId;
    private Long accountTypeId;
    private double amount;
    private LocalDate openingDate;
    private Long ownerId;

    public AccountDTO() {
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

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDTO that = (AccountDTO) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(number, that.number) && Objects.equals(name, that.name) && Objects.equals(currencyId, that.currencyId) && Objects.equals(accountTypeId, that.accountTypeId) && Objects.equals(openingDate, that.openingDate) && Objects.equals(ownerId, that.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, currencyId, accountTypeId, amount, openingDate, ownerId);
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", currencyId=" + currencyId +
                ", accountTypeId=" + accountTypeId +
                ", amount=" + amount +
                ", openingDate=" + openingDate +
                ", ownerId=" + ownerId +
                '}';
    }
}

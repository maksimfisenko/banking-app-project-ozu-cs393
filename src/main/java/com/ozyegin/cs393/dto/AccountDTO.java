package com.ozyegin.cs393.dto;


import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.Objects;

@Component
public class AccountDTO {
    private Long number;
    private String name;
    private CurrencyDTO currency;
    private AccountTypeDTO accountType;
    private double amount;
    private LocalDate openingDate;
    private UserDTO owner;
//    private List<DebitCardDTO> debitCards;

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

    public CurrencyDTO getCurrency() {
    return currency;
}

    public void setCurrency(CurrencyDTO currency) { this.currency = currency; }

    public AccountTypeDTO getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeDTO accountType) {
        this.accountType = accountType;
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

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDTO that = (AccountDTO) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(number, that.number) && Objects.equals(name, that.name) && Objects.equals(currency, that.currency) && Objects.equals(accountType, that.accountType) && Objects.equals(openingDate, that.openingDate) && Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, currency, accountType, amount, openingDate, owner);
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", currencyId=" + currency +
                ", accountTypeId=" + accountType +
                ", amount=" + amount +
                ", openingDate=" + openingDate +
                ", ownerId=" + owner +
                '}';
    }
}

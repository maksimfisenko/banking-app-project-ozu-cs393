package com.ozyegin.cs393.dto;

import com.ozyegin.cs393.entities.Account;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

public class DebitCardDTO {
    private Long id;
    private String number;
    private LocalDate expirationDate;
    private String name;
    private Long accountNumber;

    public DebitCardDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DebitCardDTO that = (DebitCardDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(number, that.number) && Objects.equals(expirationDate, that.expirationDate) && Objects.equals(name, that.name) && Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, expirationDate, name, accountNumber);
    }

    @Override
    public String toString() {
        return "DebitCardDTO{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", expirationDate=" + expirationDate +
                ", name='" + name + '\'' +
                ", accountId=" + accountNumber +
                '}';
    }
}
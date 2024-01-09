package com.ozyegin.cs393.dto;


import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.Objects;

@Component
public class DebitCardDTO {
    private Long id;
    private String number;
    private LocalDate expirationDate;
    private String name;
    private AccountDTO account;

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

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DebitCardDTO that = (DebitCardDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(number, that.number) && Objects.equals(expirationDate, that.expirationDate) && Objects.equals(name, that.name) && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, expirationDate, name, account);
    }

    @Override
    public String toString() {
        return "DebitCardDTO{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", expirationDate=" + expirationDate +
                ", name='" + name + '\'' +
                ", accountId=" + account +
                '}';
    }
}

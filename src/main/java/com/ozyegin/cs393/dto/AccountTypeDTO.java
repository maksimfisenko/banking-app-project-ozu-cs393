package com.ozyegin.cs393.dto;

import java.util.Objects;

public class AccountTypeDTO {
    private Long id;
    private String name;
    private String description;
    private double depositRate;

    public AccountTypeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDepositRate() {
        return depositRate;
    }

    public void setDepositRate(double depositRate) {
        this.depositRate = depositRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTypeDTO that = (AccountTypeDTO) o;
        return Double.compare(that.depositRate, depositRate) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, depositRate);
    }

    @Override
    public String toString() {
        return "AccountTypeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", depositRate=" + depositRate +
                '}';
    }
}

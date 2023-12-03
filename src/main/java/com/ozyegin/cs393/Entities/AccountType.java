package com.ozyegin.cs393.Entities;

import jakarta.persistence.*;

@Entity
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private double depositRate;

    public AccountType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

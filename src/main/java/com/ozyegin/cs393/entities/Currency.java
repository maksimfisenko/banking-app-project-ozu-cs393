package com.ozyegin.cs393.entities;

import jakarta.persistence.*;

@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private char symbol;
    @Column(nullable = false)
    private double exchangeRateToUsd;

    public Currency() {
    }

    public Currency(Long id, String name, char symbol, double exchangeRateToUsd) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.exchangeRateToUsd = exchangeRateToUsd;
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

    public void setName(String name) { this.name = name; }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public double getExchangeRateToUsd() {
        return exchangeRateToUsd;
    }

    public void setExchangeRateToUsd(double exchangeRatetoUsd) {
        this.exchangeRateToUsd = exchangeRatetoUsd;
    }
}
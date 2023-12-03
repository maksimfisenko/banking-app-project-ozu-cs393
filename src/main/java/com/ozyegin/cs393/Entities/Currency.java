package com.ozyegin.cs393.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private char symbol;
    private double exchangeRateToUsd;

    public Currency(Long id, String name, char symbol, double exchangeRatetoUsd) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.exchangeRateToUsd = exchangeRatetoUsd;
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

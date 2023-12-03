package com.ozyegin.cs393.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private char symbol;
    private double exchangeRatetoUsd;

    public Currency(Long id, String name, char symbol, double exchangeRatetoUsd) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.exchangeRatetoUsd = exchangeRatetoUsd;
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

    public double getExchangeRatetoUsd() {
        return exchangeRatetoUsd;
    }

    public void setExchangeRatetoUsd(double exchangeRatetoUsd) {
        this.exchangeRatetoUsd = exchangeRatetoUsd;
    }
}

package com.ozyegin.cs393.entities;

import jakarta.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return symbol == currency.symbol && Double.compare(currency.exchangeRateToUsd, exchangeRateToUsd) == 0 &&
                Objects.equals(id, currency.id) && Objects.equals(name, currency.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, symbol, exchangeRateToUsd);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol=" + symbol +
                ", exchangeRateToUsd=" + exchangeRateToUsd +
                '}';
    }
}

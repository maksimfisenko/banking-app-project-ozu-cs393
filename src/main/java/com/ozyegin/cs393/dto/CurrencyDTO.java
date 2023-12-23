package com.ozyegin.cs393.dto;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CurrencyDTO {
    private Long id;
    private String name;
    private char symbol;
    private double exchangeRateToUsd;

    public CurrencyDTO() {
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

    public void setExchangeRateToUsd(double exchangeRateToUsd) {
        this.exchangeRateToUsd = exchangeRateToUsd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyDTO that = (CurrencyDTO) o;
        return symbol == that.symbol && Double.compare(that.exchangeRateToUsd, exchangeRateToUsd) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, symbol, exchangeRateToUsd);
    }

    @Override
    public String toString() {
        return "CurrencyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol=" + symbol +
                ", exchangeRateToUsd=" + exchangeRateToUsd +
                '}';
    }
}

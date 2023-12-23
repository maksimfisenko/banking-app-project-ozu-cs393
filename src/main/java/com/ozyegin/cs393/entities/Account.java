package com.ozyegin.cs393.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Entity
public class Account {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @SequenceGenerator(
            name = "sequence-generator",
            sequenceName = "sequence-generator",
            initialValue = 100000,
            allocationSize = 1
    )
    private Long number;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "currencyId")
    private Currency currency;
    @ManyToOne
    @JoinColumn(name = "accountType")
    private AccountType type;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private LocalDate openingDate;
    @ManyToOne
    @JoinColumn(name = "ownerId")
    private User owner;
    @OneToMany(mappedBy = "sendingAccount")
    private List<Transaction> sentTransactions;
    @OneToMany(mappedBy = "receivingAccount")
    private List<Transaction> receivedTransactions;
    @OneToMany(mappedBy = "account")
    private List<DebitCard> debitCards;

    public Account() {
    }

    public Account(Long number, String name, Currency currency, AccountType type,
                   double amount, LocalDate openingDate, User owner,
                   List<Transaction> sentTransactions, List<Transaction> receivedTransactions,
                   List<DebitCard> debitCards) {
        this.number = number;
        this.name = name;
        this.currency = currency;
        this.type = type;
        this.amount = amount;
        this.openingDate = openingDate;
        this.owner = owner;
        this.sentTransactions = sentTransactions;
        this.receivedTransactions = receivedTransactions;
        this.debitCards = debitCards;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public List<Transaction> getSentTransactions() {
        return sentTransactions;
    }

    public void setSentTransactions(List<Transaction> sentTransactions) {
        this.sentTransactions = sentTransactions;
    }

    public List<Transaction> getReceivedTransactions() {
        return receivedTransactions;
    }

    public void setReceivedTransactions(List<Transaction> receivedTransactions) {
        this.receivedTransactions = receivedTransactions;
    }

    public List<DebitCard> getDebitCards() {
        return debitCards;
    }

    public void setDebitCards(List<DebitCard> debitCards) {
        this.debitCards = debitCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(account.amount, amount) == 0 && Objects.equals(number, account.number) &&
                Objects.equals(name, account.name) && Objects.equals(currency, account.currency) &&
                Objects.equals(type, account.type) && Objects.equals(openingDate, account.openingDate) &&
                Objects.equals(owner, account.owner) && Objects.equals(sentTransactions, account.sentTransactions) &&
                Objects.equals(receivedTransactions, account.receivedTransactions) &&
                Objects.equals(debitCards, account.debitCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, currency, type, amount, openingDate, owner, sentTransactions,
                receivedTransactions, debitCards);
    }

    @Override
    public String toString() {
        return "Account{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", currency=" + currency +
                ", type=" + type +
                ", amount=" + amount +
                ", openingDate=" + openingDate +
                ", owner=" + owner +
                ", sentTransactions=" + sentTransactions +
                ", receivedTransactions=" + receivedTransactions +
                ", debitCards=" + debitCards +
                '}';
    }
}

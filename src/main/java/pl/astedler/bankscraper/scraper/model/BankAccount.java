package pl.astedler.bankscraper.scraper.model;

import java.math.BigDecimal;
import java.util.Currency;

public class BankAccount {

    private String name;
    private String number;
    private BigDecimal balance;
    private Currency currency;

    public BankAccount(String name, String number, BigDecimal balance, Currency currency) {
        this.name = name;
        this.number = number;
        this.balance = balance;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return String.format("%s number = '%s', balance = %s %s", name, number, balance, currency);
    }
}

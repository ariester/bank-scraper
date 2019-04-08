package pl.astedler.bankscraper.scraper.model;

import java.math.BigDecimal;
import java.util.Currency;

public class BankAccount {

    private final String name;
    private final String number;
    private final BigDecimal balance;
    private final Currency currency;

    public BankAccount(String name, String number, BigDecimal balance, Currency currency) {
        this.name = name;
        this.number = number;
        this.balance = balance;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return String.format("%s number = '%s', balance = %s %s", name, number, balance, currency);
    }

}

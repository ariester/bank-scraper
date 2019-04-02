package pl.astedler.bankscraper.scraper.model;

import com.fasterxml.jackson.databind.JsonNode;

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

    public BankAccount(JsonNode node) {
        this.name = node.get("ProductName").asText();
        this.number = node.get("AccountNumber").asText();
        this.balance = new BigDecimal(node.get("Balance").asText().replace(',', '.'));
        this.currency = Currency.getInstance(node.get("Currency").asText());
    }

    @Override
    public String toString() {
        return String.format("%s number = '%s', balance = %s %s", name, number, balance, currency);
    }

}

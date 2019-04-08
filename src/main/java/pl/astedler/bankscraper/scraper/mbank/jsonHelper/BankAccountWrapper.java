package pl.astedler.bankscraper.scraper.mbank.jsonHelper;

import com.fasterxml.jackson.databind.JsonNode;
import pl.astedler.bankscraper.scraper.model.BankAccount;

import java.math.BigDecimal;
import java.util.Currency;

public class BankAccountWrapper {

    public static BankAccount createBankAccount(JsonNode node) {
        String name = node.get("ProductName").asText();
        String number = node.get("AccountNumber").asText();
        BigDecimal balance = new BigDecimal(node.get("Balance").asText().replace(',', '.'));
        Currency currency = Currency.getInstance(node.get("Currency").asText());
        return new BankAccount(name, number, balance, currency);
    }

}

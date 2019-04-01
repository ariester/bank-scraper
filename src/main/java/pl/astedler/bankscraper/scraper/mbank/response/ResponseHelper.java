package pl.astedler.bankscraper.scraper.mbank.response;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.client.fluent.Response;
import pl.astedler.bankscraper.scraper.model.BankAccount;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;

public class ResponseHelper {

    private ResponseHelper() {
    }

    public static String getText(Response response) throws IOException {
        return response.returnContent()
                .asString();
    }

    public static BankAccount createBankAccountFromJson(JsonNode node) {
        String name = node.get("ProductName").asText();
        String number = node.get("AccountNumber").asText();
        BigDecimal balance = new BigDecimal(node.get("Balance").asText().replace(',', '.'));
        Currency currency = Currency.getInstance(node.get("Currency").asText());
        return new BankAccount(name, number, balance, currency);
    }

}

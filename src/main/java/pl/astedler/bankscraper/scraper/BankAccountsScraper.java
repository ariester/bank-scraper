package pl.astedler.bankscraper.scraper;

import org.apache.http.auth.InvalidCredentialsException;
import pl.astedler.bankscraper.scraper.model.BankAccount;
import pl.astedler.bankscraper.scraper.model.UserCredentials;

import java.io.IOException;
import java.util.List;

public interface BankAccountsScraper {
    List<BankAccount> getBankAccounts(UserCredentials userCredentials) throws IOException, InvalidCredentialsException;
}

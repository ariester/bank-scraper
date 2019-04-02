package pl.astedler.bankscraper.scraper;

import pl.astedler.bankscraper.exception.InvalidCredentialsException;
import pl.astedler.bankscraper.scraper.model.BankAccount;
import pl.astedler.bankscraper.scraper.model.UserCredentials;

import java.io.IOException;
import java.util.Collection;

public interface BankScraper {

    Collection<BankAccount> fetchBankAccounts(UserCredentials userCredentials) throws IOException, InvalidCredentialsException;

}

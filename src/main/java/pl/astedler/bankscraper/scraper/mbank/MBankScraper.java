package pl.astedler.bankscraper.scraper.mbank;

import org.apache.http.auth.InvalidCredentialsException;
import pl.astedler.bankscraper.scraper.BankAccountsScraper;
import pl.astedler.bankscraper.scraper.mbank.page.LoginPage;
import pl.astedler.bankscraper.scraper.mbank.page.MainPage;
import pl.astedler.bankscraper.scraper.mbank.response.LoginResponse;
import pl.astedler.bankscraper.scraper.model.BankAccount;
import pl.astedler.bankscraper.scraper.model.UserCredentials;

import java.io.IOException;
import java.util.List;

public class MBankScraper implements BankAccountsScraper {

    public MBankScraper() {
    }

    @Override
    public List<BankAccount> getBankAccounts(UserCredentials userCredentials) throws IOException, InvalidCredentialsException {
        LoginResponse loginResponse = LoginPage.login(userCredentials);
        return MainPage.getAccounts(loginResponse);
    }
}

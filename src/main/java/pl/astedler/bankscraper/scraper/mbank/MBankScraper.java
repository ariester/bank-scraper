package pl.astedler.bankscraper.scraper.mbank;

import pl.astedler.bankscraper.exception.InvalidCredentialsException;
import pl.astedler.bankscraper.scraper.BankScraper;
import pl.astedler.bankscraper.scraper.mbank.page.LoginPage;
import pl.astedler.bankscraper.scraper.mbank.page.MainPage;
import pl.astedler.bankscraper.scraper.mbank.response.LoginResponse;
import pl.astedler.bankscraper.scraper.model.BankAccount;
import pl.astedler.bankscraper.scraper.model.UserCredentials;

import java.io.IOException;
import java.util.List;

public class MBankScraper implements BankScraper {

    public MBankScraper() {
    }

    @Override
    public List<BankAccount> fetchBankAccounts(UserCredentials userCredentials) throws IOException, InvalidCredentialsException {
        LoginResponse loginResponse = LoginPage.login(userCredentials);
        return MainPage.getAccounts(loginResponse);
    }

}

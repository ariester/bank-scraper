package pl.astedler.bankscraper.scraper.mbank;

import org.junit.jupiter.api.Test;
import pl.astedler.bankscraper.exception.InvalidCredentialsException;
import pl.astedler.bankscraper.scraper.TestCredentials;
import pl.astedler.bankscraper.scraper.model.BankAccount;
import pl.astedler.bankscraper.scraper.model.UserCredentials;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MBankScraperTest {

    @Test
    void fetchBankAccountsGivenValidCredentialsShouldReturnAccounts() throws IOException {
        MBankScraper mBankScraper = new MBankScraper();
        UserCredentials userCredentials = new UserCredentials(TestCredentials.MBANK_USERNAME, TestCredentials.MBANK_PASSWORD);
        List<BankAccount> accounts = mBankScraper.fetchBankAccounts(userCredentials);
        assertTrue(accounts.size() >= 1);
    }

    @Test
    void fetchBankAccountGivenInvalidUsernameShouldThrowInvalidCredentialsException() {
        MBankScraper mBankScraper = new MBankScraper();
        UserCredentials userCredentials = new UserCredentials("wrong_name", TestCredentials.MBANK_PASSWORD);
        assertThrows(InvalidCredentialsException.class, () -> mBankScraper.fetchBankAccounts(userCredentials));
    }

    @Test
    void fetchBankAccountGivenInvalidPasswordShouldThrowInvalidCredentialsException() {
        MBankScraper mBankScraper = new MBankScraper();
        UserCredentials userCredentials = new UserCredentials(TestCredentials.MBANK_USERNAME, "wrong_password");
        assertThrows(InvalidCredentialsException.class, () -> mBankScraper.fetchBankAccounts(userCredentials));
    }

    @Test
    void fetchBankAccountGivenInvalidCredentialsShouldThrowInvalidCredentialsException() {
        MBankScraper mBankScraper = new MBankScraper();
        UserCredentials userCredentials = new UserCredentials("wrong_name", "wrong_password");
        assertThrows(InvalidCredentialsException.class, () -> mBankScraper.fetchBankAccounts(userCredentials));
    }

}
package pl.astedler.bankscraper;

import org.apache.http.auth.InvalidCredentialsException;
import pl.astedler.bankscraper.scraper.BankAccountsScraper;
import pl.astedler.bankscraper.scraper.model.BankAccount;
import pl.astedler.bankscraper.scraper.model.UserCredentials;
import pl.astedler.bankscraper.userinterface.UserInterface;

import java.io.IOException;
import java.util.List;

public class Application {

    private UserInterface userInterface;
    private BankAccountsScraper bankAccountsScraper;

    public Application(UserInterface userInterface, BankAccountsScraper bankAccountsScraper) {
        this.userInterface = userInterface;
        this.bankAccountsScraper = bankAccountsScraper;
    }

    public void run() {
        try {
            fetchBankAccounts();
        } catch (Exception e) {
            printError(e);
        }
    }

    private void fetchBankAccounts() throws IOException, InvalidCredentialsException {
        UserCredentials userCredentials = userInterface.getUserCredentials();
        List<BankAccount> bankAccounts = bankAccountsScraper.getBankAccounts(userCredentials);
        userInterface.displayBankAccounts(bankAccounts);
    }

    private void printError(Exception e) {
        System.out.println(e.getMessage());
    }
}

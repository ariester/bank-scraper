package pl.astedler.bankscraper;

import pl.astedler.bankscraper.exception.InvalidCredentialsException;
import pl.astedler.bankscraper.scraper.BankScraper;
import pl.astedler.bankscraper.scraper.model.BankAccount;
import pl.astedler.bankscraper.scraper.model.UserCredentials;
import pl.astedler.bankscraper.userinterface.UserInterface;

import java.io.IOException;
import java.util.Collection;

public class Application {

    private UserInterface userInterface;
    private BankScraper bankScraper;

    public Application(UserInterface userInterface, BankScraper bankScraper) {
        this.userInterface = userInterface;
        this.bankScraper = bankScraper;
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
        Collection<BankAccount> bankAccounts = bankScraper.fetchBankAccounts(userCredentials);
        userInterface.displayBankAccounts(bankAccounts);
    }

    private void printError(Exception e) {
        System.out.println(e.getMessage());
    }

}

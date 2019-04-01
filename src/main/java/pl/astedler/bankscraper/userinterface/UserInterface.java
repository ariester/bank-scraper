package pl.astedler.bankscraper.userinterface;

import pl.astedler.bankscraper.scraper.model.BankAccount;
import pl.astedler.bankscraper.scraper.model.UserCredentials;

import java.util.List;

public interface UserInterface {

    UserCredentials getUserCredentials();

    void displayBankAccounts(List<BankAccount> accounts);
}

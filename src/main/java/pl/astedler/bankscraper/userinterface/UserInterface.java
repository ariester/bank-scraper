package pl.astedler.bankscraper.userinterface;

import pl.astedler.bankscraper.scraper.model.BankAccount;
import pl.astedler.bankscraper.scraper.model.UserCredentials;

import java.util.Collection;

public interface UserInterface {

    UserCredentials getUserCredentials();

    void displayBankAccounts(Collection<BankAccount> accounts);

}

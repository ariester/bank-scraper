package pl.astedler.bankscraper.userinterface;

import pl.astedler.bankscraper.scraper.model.BankAccount;
import pl.astedler.bankscraper.scraper.model.UserCredentials;

import java.util.Collection;
import java.util.Scanner;

public class ConsoleUI implements UserInterface {

    private static final String USERNAME_PROMPT = "Username: ";
    private static final String PASSWORD_PROMPT = "Password: ";

    private final Scanner scanner = new Scanner(System.in);

    public UserCredentials getUserCredentials() {
        System.out.println("To show information about your accounts, please enter your bank credentials below.");
        String username = getUsername();
        String password = getPassword();
        return new UserCredentials(username, password);
    }

    private String getUsername() {
        System.out.print(USERNAME_PROMPT);
        return scanner.nextLine().trim();
    }

    private String getPassword() {
        System.out.print(PASSWORD_PROMPT);
        return scanner.nextLine();
    }

    public void displayBankAccounts(Collection<BankAccount> accounts) {
        System.out.println("List of your accounts:");
        accounts.forEach(System.out::println);
    }

}

package pl.astedler.bankscraper;

import pl.astedler.bankscraper.scraper.BankScraper;
import pl.astedler.bankscraper.scraper.mbank.MBankScraper;
import pl.astedler.bankscraper.userinterface.ConsoleUI;
import pl.astedler.bankscraper.userinterface.UserInterface;

public class Main {

    public static void main(String[] args) {
        startApplication();
    }

    private static void startApplication() {
        UserInterface userInterface = new ConsoleUI();
        BankScraper scraper = new MBankScraper();
        Application application = new Application(userInterface, scraper);
        application.run();
    }

}

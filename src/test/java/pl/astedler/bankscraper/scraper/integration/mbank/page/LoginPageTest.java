package pl.astedler.bankscraper.scraper.integration.mbank.page;

import org.junit.jupiter.api.Test;
import pl.astedler.bankscraper.exception.InvalidCredentialsException;
import pl.astedler.bankscraper.scraper.integration.TestCredentials;
import pl.astedler.bankscraper.scraper.mbank.page.LoginPage;
import pl.astedler.bankscraper.scraper.mbank.response.LoginResponse;
import pl.astedler.bankscraper.scraper.model.UserCredentials;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginPageTest {

    @Test
    void loginGivenValidUserCredentialsShouldReturnSuccessfulResponse() throws IOException {
        UserCredentials userCredentials = new UserCredentials(TestCredentials.MBANK_USERNAME, TestCredentials.MBANK_PASSWORD);
        LoginResponse response = LoginPage.login(userCredentials);
        assertTrue(response.isSuccessful());
    }

    @Test
    void loginGivenInvalidUsernameShouldThrowInvalidCredentialsException() {
        UserCredentials userCredentials = new UserCredentials("wrong_username", TestCredentials.MBANK_PASSWORD);
        assertThrows(InvalidCredentialsException.class, () -> LoginPage.login(userCredentials));
    }

    @Test
    void loginGivenInvalidPasswordShouldThrowInvalidCredentialsException() {
        UserCredentials userCredentials = new UserCredentials(TestCredentials.MBANK_USERNAME, "wrong_password");
        assertThrows(InvalidCredentialsException.class, () -> LoginPage.login(userCredentials));
    }

    @Test
    void loginGivenInvalidUserCredentialsShouldThrowInvalidCredentialsException() {
        UserCredentials userCredentials = new UserCredentials("wrong_username", "wrong_password");
        assertThrows(InvalidCredentialsException.class, () -> LoginPage.login(userCredentials));
    }

}
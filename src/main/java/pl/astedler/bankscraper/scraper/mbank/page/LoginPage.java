package pl.astedler.bankscraper.scraper.mbank.page;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.auth.InvalidCredentialsException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;
import pl.astedler.bankscraper.scraper.mbank.request.RequestHeaders;
import pl.astedler.bankscraper.scraper.mbank.request.RequestHelper;
import pl.astedler.bankscraper.scraper.mbank.response.LoginResponse;
import pl.astedler.bankscraper.scraper.model.UserCredentials;
import pl.astedler.bankscraper.scraper.utils.RegexUtils;

import java.io.IOException;

public class LoginPage {

    private static final String LOGIN_PAGE_URL = "https://online.mbank.pl/pl/Login";
    private static final String LOGIN_REQUEST_URL = "https://online.mbank.pl/pl/LoginMain/Account/JsonLogin";
    private static final String SEED_REGEX = "app\\.initialize\\('(.*?)'"; //seed from app.initialize('seed'

    private static final String INVALID_CREDENTIALS_MESSAGE = "Nieprawidłowy identyfikator lub hasło.";

    private LoginPage() {
    }

    public static LoginResponse login(UserCredentials userCredentials) throws IOException, InvalidCredentialsException {
        Request postRequest = preparePostRequest(userCredentials);
        String responseContent = RequestHelper.postRequestAndGetResponseText(postRequest);
        JsonNode node = extractJson(responseContent);
        LoginResponse loginResponse = new LoginResponse(node);
        if (!loginResponse.isSuccessful()) {
            handleError(loginResponse);
        }
        return loginResponse;
    }

    private static Request preparePostRequest(UserCredentials userCredentials) throws IOException {
        String json = prepareJson(userCredentials);
        return Request.Post(LOGIN_REQUEST_URL)
                .setHeaders(RequestHeaders.COMMON_HEADERS)
                .body(new StringEntity(json));
    }

    private static String prepareJson(UserCredentials userCredentials) throws IOException {
        return String.format("{\"UserName\":\"%s\",\"Password\":\"%s\",\"Seed\":\"%s\",\"Scenario\":\"Default\"," +
                        "\"UWAdditionalParams\":{\"InOut\":null,\"ReturnAddress\":null,\"Source\":null},\"Lang\":\"\",\"HrefHasHash\":false}",
                userCredentials.getUsername(), userCredentials.getPassword(), getSeed());
    }

    private static String getSeed() throws IOException {
        String htmlPage = RequestHelper.getPage(LOGIN_PAGE_URL);
        return RegexUtils.getFirstMatchOrCrash(SEED_REGEX, htmlPage);
    }

    private static JsonNode extractJson(String response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(response);
    }

    private static void handleError(LoginResponse loginResponse) throws InvalidCredentialsException {
        String error = loginResponse.getErrorMessageTitle();
        if (error.equals(INVALID_CREDENTIALS_MESSAGE)) {
            throw new InvalidCredentialsException(error);
        } else {
            throw new RuntimeException(error);
        }
    }

}

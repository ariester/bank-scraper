package pl.astedler.bankscraper.scraper.mbank.page;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;
import pl.astedler.bankscraper.exception.InvalidCredentialsException;
import pl.astedler.bankscraper.scraper.mbank.request.RequestHeaders;
import pl.astedler.bankscraper.scraper.mbank.request.RequestHelper;
import pl.astedler.bankscraper.scraper.mbank.response.LoginResponse;
import pl.astedler.bankscraper.scraper.model.UserCredentials;
import pl.astedler.bankscraper.scraper.utils.RegexUtils;

import java.io.IOException;

public class LoginPage {

    private static final String SEED_REGEX = "app\\.initialize\\('(.*?)'"; //seed from app.initialize('seed'

    private static final String INVALID_CREDENTIALS_MESSAGE = "Nieprawidłowy identyfikator lub hasło.";

    private static final ObjectMapper mapper = new ObjectMapper();

    private LoginPage() {
    }

    public static LoginResponse login(UserCredentials userCredentials) throws IOException, InvalidCredentialsException {
        Request postRequest = preparePostRequest(userCredentials);
        String responseContent = RequestHelper.postRequestAndGetResponseText(postRequest);
        JsonNode node = extractJson(responseContent);
        LoginResponse loginResponse = new LoginResponse(node);
        if (!loginResponse.isSuccessful())
            handleError(loginResponse);
        return loginResponse;
    }

    private static Request preparePostRequest(UserCredentials userCredentials) throws IOException {
        String json = prepareJson(userCredentials);
        return Request.Post(MBankUrl.LOGIN_REQUEST_URL)
            .setHeaders(RequestHeaders.COMMON_HEADERS)
            .body(new StringEntity(json));
    }

    private static String prepareJson(UserCredentials userCredentials) throws IOException {
        ObjectNode rootNode = mapper.createObjectNode();
        NullNode nullNode = NullNode.getInstance();

        ObjectNode childNode = mapper.createObjectNode();
        childNode.set("InOut", nullNode);
        childNode.set("ReturnAddress", nullNode);
        childNode.set("Source", nullNode);

        rootNode.put("UserName", userCredentials.getUsername())
            .put("Password", userCredentials.getPassword())
            .put("Seed", getSeed())
            .put("Scenario", "Default")
            .set("UWAdditionalParams", childNode);

        rootNode.put("Lang", "")
            .put("HrefHasHash", false);

        return mapper.writeValueAsString(rootNode);
    }

    private static String getSeed() throws IOException {
        String htmlPage = RequestHelper.getPage(MBankUrl.LOGIN_PAGE_URL);
        return RegexUtils.getFirstMatchOrCrash(SEED_REGEX, htmlPage);
    }

    private static JsonNode extractJson(String response) throws IOException {
        return mapper.readTree(response);
    }

    private static void handleError(LoginResponse loginResponse) throws InvalidCredentialsException {
        String error = loginResponse.getErrorMessageTitle();
        if (error.equals(INVALID_CREDENTIALS_MESSAGE))
            throw new InvalidCredentialsException(error);
        else
            throw new RuntimeException(error);
    }

}

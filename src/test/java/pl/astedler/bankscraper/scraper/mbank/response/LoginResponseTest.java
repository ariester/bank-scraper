package pl.astedler.bankscraper.scraper.mbank.response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoginResponseTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void createLoginResponseGivenValidDataShouldCreateProperlyLoginResponse() throws IOException {
        // given
        boolean successful = true;
        String errorMessageTitle = "null";
        String tabId = "9f044aff-177b-4f23-b70b-41fdf40e344f";
        LoginResponse loginResponse = createLoginResponse(successful, errorMessageTitle, tabId);

        // when

        // then
        assertNotNull(loginResponse);
        assertTrue(loginResponse.isSuccessful());
        assertEquals("null", loginResponse.getErrorMessageTitle());
        assertEquals("9f044aff-177b-4f23-b70b-41fdf40e344f", loginResponse.getTabId());
    }

    @Test
    void createLoginResponseGivenNotSuccessfulJsonResponseShouldCreateProperlyLoginResponse() throws IOException {
        // given
        boolean successful = false;
        String errorMessageTitle = "\"Nieprawidłowy identyfikator lub hasło.\"";
        String tabId = "null";
        LoginResponse loginResponse = createLoginResponse(successful, errorMessageTitle, tabId);

        // when

        // then
        assertNotNull(loginResponse);
        assertFalse(loginResponse.isSuccessful());
        assertEquals("Nieprawidłowy identyfikator lub hasło.", loginResponse.getErrorMessageTitle());
        assertEquals("null", loginResponse.getTabId());
    }

    synchronized LoginResponse createLoginResponse(boolean successful, String errorMessageTitle, String tabId) throws IOException {
        String json = "{\"button\":false,\"errorMessageBody\":\"\",\"errorMessageTitle\":" + errorMessageTitle +
            ",\"redirectUrl\":\"/pl\",\"successful\":" + successful + ",\"tabId\":\"" + tabId + "\"" +
            ",\"sessionKeyForUW\":null,\"regulationsApproval\":false,\"betaTestingApproval\":false,\"implicitTestingApproval\":false,\"allAprovalsSaved\":false}";
        JsonNode node = mapper.readTree(json);
        return new LoginResponse(node);
    }
}
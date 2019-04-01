package pl.astedler.bankscraper.scraper.mbank.response;

import com.fasterxml.jackson.databind.JsonNode;

public class LoginResponse {

    private final JsonNode node;

    public LoginResponse(JsonNode node) {
        this.node = node;
    }

    public boolean isSuccessful() {
        return node.get("successful").asBoolean();
    }

    public String getErrorMessageTitle() {
        return node.get("errorMessageTitle").asText();
    }

    public String getTabId() {
        return node.get("tabId").asText();
    }
}

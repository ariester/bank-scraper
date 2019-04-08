package pl.astedler.bankscraper.scraper.mbank.jsonHelper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import pl.astedler.bankscraper.scraper.model.UserCredentials;

public class LoginJsonNode {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final ObjectNode node;

    public LoginJsonNode() {
        node = mapper.createObjectNode();
    }

    private static ObjectNode createUWAdditionalParamsNode() {
        NullNode nullNode = NullNode.getInstance();
        ObjectNode node = mapper.createObjectNode();
        node.set("InOut", nullNode);
        node.set("ReturnAddress", nullNode);
        node.set("Source", nullNode);
        return node;
    }

    public LoginJsonNode putUserCredentials(UserCredentials userCredentials) {
        node.put("UserName", userCredentials.getUsername());
        node.put("Password", userCredentials.getPassword());
        return this;
    }

    public LoginJsonNode putSeed(String seed) {
        node.put("Seed", seed);
        return this;
    }

    public LoginJsonNode putScenario(String value) {
        node.put("Scenario", value);
        return this;
    }

    public LoginJsonNode addUWAdditionalParamsNode() {
        node.set("UWAdditionalParams", createUWAdditionalParamsNode());
        return this;
    }

    public LoginJsonNode addAdditionalFields() {
        node.put("Lang", "");
        node.put("HrefHasHash", false);
        return this;
    }

    public ObjectNode getNode() {
        return node;
    }

}
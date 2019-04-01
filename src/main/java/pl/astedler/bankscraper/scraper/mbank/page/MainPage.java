package pl.astedler.bankscraper.scraper.mbank.page;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.http.client.fluent.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.astedler.bankscraper.scraper.mbank.request.RequestHeaders;
import pl.astedler.bankscraper.scraper.mbank.request.RequestHelper;
import pl.astedler.bankscraper.scraper.mbank.response.LoginResponse;
import pl.astedler.bankscraper.scraper.mbank.response.ResponseHelper;
import pl.astedler.bankscraper.scraper.model.BankAccount;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPage {

    private static final String MAIN_PAGE_URL = "https://online.mbank.pl/pl";
    private static final String ACCOUNTS_REQUEST_URL = "https://online.mbank.pl/pl/MyDesktop/Desktop/GetAccountsList";
    private static final String VERIFICATION_TOKEN_META_TAG = "meta[name=__AjaxRequestVerificationToken]";

    private MainPage() {
    }

    public static List<BankAccount> getAccounts(LoginResponse loginResponse) throws IOException {
        String verificationToken = getVerificationToken();
        Request postRequest = preparePostRequest(loginResponse, verificationToken);
        String responseContent = RequestHelper.postRequestAndGetResponseText(postRequest);
        return parseAccountsResponse(responseContent);
    }

    private static String getVerificationToken() throws IOException {
        String htmlPage = getHtmlSourceCode();
        return extractVerificationToken(htmlPage);
    }

    private static String getHtmlSourceCode() throws IOException {
        return RequestHelper.getPage(MAIN_PAGE_URL);
    }

    private static String extractVerificationToken(String htmlPage) {
        Document document = Jsoup.parse(htmlPage);
        Elements metaTags = document.select(VERIFICATION_TOKEN_META_TAG);
        Element verificationTokenMetaTag = metaTags.first();
        return verificationTokenMetaTag.attr("content");
    }

    private static Request preparePostRequest(LoginResponse loginResponse, String verificationToken) {
        return Request.Post(ACCOUNTS_REQUEST_URL)
                .setHeaders(RequestHeaders.COMMON_HEADERS)
                .setHeader("X-Request-Verification-Token", verificationToken)
                .setHeader("X-Tab-Id", loginResponse.getTabId());
    }

    private static List<BankAccount> parseAccountsResponse(String responseContent) throws IOException {
        ArrayNode accountsNode = getArrayNodeAccounts(responseContent);
        return getBankAccounts(accountsNode);
    }

    private static ArrayNode getArrayNodeAccounts(String responseContent) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(responseContent, JsonNode.class);
        return (ArrayNode) rootNode.get("accountDetailsList");
    }

    private static List<BankAccount> getBankAccounts(ArrayNode accountsNode) {
        List<BankAccount> accounts = new ArrayList<>();
        for (JsonNode accountNode : accountsNode) {
            BankAccount bankAccount = ResponseHelper.createBankAccountFromJson(accountNode);
            accounts.add(bankAccount);
        }
        return accounts;
    }

}

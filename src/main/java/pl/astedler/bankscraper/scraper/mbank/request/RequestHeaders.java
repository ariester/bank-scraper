package pl.astedler.bankscraper.scraper.mbank.request;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;

public class RequestHeaders {

    public static final Header[] COMMON_HEADERS = {
        new BasicHeader(HttpHeaders.ACCEPT, "application/json, text/javascript, */*; q=0.01"),
        new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE, "pl,en-US:q=0.7,en;q=0.3"),
        new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8"),
    };

    private RequestHeaders() {
    }

}

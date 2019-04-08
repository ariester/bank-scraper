package pl.astedler.bankscraper.scraper.mbank.response;

import org.apache.http.client.fluent.Response;

import java.io.IOException;

public class ResponseHelper {

    private ResponseHelper() {
    }

    public static String getText(Response response) throws IOException {
        return response.returnContent()
            .asString();
    }

}

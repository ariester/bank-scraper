package pl.astedler.bankscraper.scraper.mbank.request;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import pl.astedler.bankscraper.scraper.mbank.response.ResponseHelper;

import java.io.IOException;

public class RequestHelper {

    private RequestHelper() {
    }

    public static String getPage(String uri) throws IOException {
        Request request = Request.Get(uri);
        Response response = executeRequest(request);
        return ResponseHelper.getText(response);
    }

    public static String postRequestAndGetResponseText(Request postRequest) throws IOException {
        Response response = executeRequest(postRequest);
        return ResponseHelper.getText(response);
    }

    public static Response executeRequest(Request request) throws IOException {
        return request.execute();
    }

}

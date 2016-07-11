package org.zoltor.communicator.client;

import org.zoltor.communicator.config.HttpMethod;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zoltor on 09/07/16.
 */
// TODO Is this thread-safe?
public abstract class BaseHttpClient implements HttpClient {

    private final Map<String, String> headers = new HashMap<>();
    private HttpURLConnection connection;
    private int responseCode = -1;
    private String rawResponse;

    protected void openConnectionWithHeaders(String toUrl) throws IOException {
        URL url = new URL(toUrl);
        connection = (HttpURLConnection) url.openConnection();
        for (String headerName : headers.keySet()) {
            connection.setRequestProperty(headerName, headers.get(headerName));
        }
    }

    @Override
    public void setHeaders(Map<String, String> headers) {
        this.headers.clear();
        this.headers.putAll(headers);
    }

    @Override
    public int getResponseCode() {
        return responseCode;
    }

    public HttpURLConnection getConnection() {
        return connection;
    }

    // TODO Logger debug here
    protected String getResponse(String toUrl, HttpMethod httpMethod, String body) throws IOException {
        openConnectionWithHeaders(toUrl);
        getConnection().setRequestMethod(httpMethod.toString());
        switch (httpMethod) {
            case POST:
            case PUT:
                getConnection().setDoOutput(true);
                DataOutputStream dataOutputStream = new DataOutputStream(getConnection().getOutputStream());
                dataOutputStream.writeBytes(body);
                dataOutputStream.flush();
                dataOutputStream.close();
                break;
            case GET:
            case DELETE:
                getConnection().setDoOutput(false);
        }
        responseCode = getConnection().getResponseCode();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(getConnection().getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            response.append(line);
        }
        bufferedReader.close();
        rawResponse = response.toString();
        return rawResponse;
    }

    @Override
    public String getRawResponse() {
        return rawResponse;
    }
}

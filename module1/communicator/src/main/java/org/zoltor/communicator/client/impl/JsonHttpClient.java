package org.zoltor.communicator.client.impl;

import org.zoltor.communicator.client.BaseHttpClient;
import org.zoltor.communicator.config.HttpMethod;
import org.zoltor.communicator.map.JsonProcessor;
import org.zoltor.communicator.map.impl.JsonProcessorImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zoltor on 09/07/16.
 */
class JsonHttpClient extends BaseHttpClient {

    private JsonProcessor jsonProcessor = new JsonProcessorImpl();

    JsonHttpClient() {
        Map<String, String> jsonHeaders = new HashMap<>();
        jsonHeaders.put("Accept", "application/json");
        jsonHeaders.put("Content-Type", "application/json");
        jsonHeaders.put("User-Agent", "Json HTTP Client by ZoLToR v1.0");
        setHeaders(jsonHeaders);
    }

    @SuppressWarnings("unchecked")
    public <T, R> T execute(String url, HttpMethod httpMethod, R requestObject, Class<T> responseClass) throws IOException {
        String requestBody = (requestObject == null) ? "" : jsonProcessor.getJson(requestObject);
        String responseBody = getResponse(url, httpMethod, requestBody);
        return (responseClass == null) ? (T) responseBody : jsonProcessor.getObjectFromJson(responseBody, responseClass);
    }

    @Override
    public <T, R> List<T> execute(String url, HttpMethod httpMethod, List<R> requestObjects, Class<T> responseClass) throws IOException {
        String requestBody = jsonProcessor.getJsonFromObjects(requestObjects);
        String responseBody = getResponse(url, httpMethod, requestBody);
        return jsonProcessor.getObjectsFromJson(responseBody, responseClass);
    }
}

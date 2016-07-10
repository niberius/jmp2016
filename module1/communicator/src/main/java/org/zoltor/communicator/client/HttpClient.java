package org.zoltor.communicator.client;

import org.zoltor.communicator.config.HttpMethod;

import java.io.IOException;
import java.util.Map;

/**
 * Created by zoltor on 09/07/16.
 */
public interface HttpClient {

    <T, R> T execute(String url, HttpMethod httpMethod, R requestObject, Class<T> responseClass) throws IOException;
    void setHeaders(Map<String, String> headers);
    int getResponseCode();
}

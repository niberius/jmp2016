package org.zoltor.communicator.client.impl;

import org.zoltor.communicator.client.HttpClient;
import org.zoltor.communicator.config.HttpClientType;

/**
 * Created by zoltor on 10/07/16.
 */
public final class HttpClientFactory {

    private HttpClientFactory() {
        // Do not need to create instance of this class
    }

    public static HttpClient getClient(HttpClientType httpClientType) {
        switch (httpClientType) {
            case JSON:
                return new JsonHttpClient();
            default:
                throw new IllegalArgumentException("There is no http client implementation for type " + httpClientType);
        }
    }

}

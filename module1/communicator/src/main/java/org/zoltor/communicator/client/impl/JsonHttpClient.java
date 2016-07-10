package org.zoltor.communicator.client.impl;

import org.zoltor.communicator.client.BaseHttpClient;
import org.zoltor.communicator.config.HttpMethod;

/**
 * Created by zoltor on 09/07/16.
 */
public class JsonHttpClient extends BaseHttpClient {



    public <T, R> T execute(String url, HttpMethod httpMethod, R requestObject, T responseClass) {
        return null;
    }

    private <R> String getRequestAsString(R requestObject) {
        return null;
    }

    private <T> T getObjectsFromResponseString() {
        return null;
    }


}

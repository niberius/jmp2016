package org.zoltor.communicator.client;

import org.zoltor.communicator.config.HttpMethod;

/**
 * Created by zoltor on 09/07/16.
 */
public interface HttpClient {

    <T, R> T execute(String url, HttpMethod httpMethod, R requestObject, T responseClass);

}

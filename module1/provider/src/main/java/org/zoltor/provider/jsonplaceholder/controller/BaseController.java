package org.zoltor.provider.jsonplaceholder.controller;

import org.zoltor.communicator.client.HttpClient;
import org.zoltor.communicator.client.impl.HttpClientFactory;
import org.zoltor.communicator.config.HttpClientType;
import org.zoltor.communicator.config.HttpMethod;
import org.zoltor.provider.jsonplaceholder.entity.BaseEntity;
import org.zoltor.util.ReflectionUtil;

import java.io.IOException;

/**
 * Created by zoltor on 11/07/16.
 */
public abstract class BaseController {

    protected static final HttpClient JSON_HTTP_CLIENT = HttpClientFactory.getClient(HttpClientType.JSON);
    protected static final String BASE_URL = "http://jsonplaceholder.typicode.com";

    protected <T extends BaseEntity> T getEntity(String relativeUrl, HttpMethod httpMethod, Object requestObject, Class<T> responseClass) throws IOException {
        return JSON_HTTP_CLIENT.execute(BASE_URL + relativeUrl, httpMethod, requestObject, responseClass);
    }


}

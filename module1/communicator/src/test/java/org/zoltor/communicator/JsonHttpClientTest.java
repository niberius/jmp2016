package org.zoltor.communicator;

import org.junit.Test;
import org.zoltor.communicator.client.HttpClient;
import org.zoltor.communicator.client.impl.HttpClientFactory;
import org.zoltor.communicator.config.HttpClientType;
import org.zoltor.communicator.config.HttpMethod;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by zoltor on 10/07/16.
 */
// TODO Add some tests for the POST request
public class JsonHttpClientTest {

    private final HttpClient httpClient = HttpClientFactory.getClient(HttpClientType.JSON);

    @Test
    public void testGetGoogle() throws IOException {
        String response = httpClient.execute("https://google.com", HttpMethod.GET, null, null);
        assertEquals(200, httpClient.getResponseCode());
        assertFalse(response.isEmpty());
    }

}

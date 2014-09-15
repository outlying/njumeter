package com.antyzero.njumeter.network;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.IOException;
import java.net.URI;

/**
 * Customized request factory
 */
public class RequestFactory extends SimpleClientHttpRequestFactory {

    private static final String HEADER_COOKIE = "Cookie";

    private static String cookies = null;

    /**
     * Inject extra headers
     *
     * {@inheritDoc}
     */
    @Override
    public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {

        ClientHttpRequest request = super.createRequest(uri, httpMethod);

        if(cookies != null){
            request.getHeaders().set(HEADER_COOKIE, cookies);
        }

        return request;
    }

    /**
     * Updates cookie value for next requests
     *
     * @param newCookies
     */
    public static void setCookies(String newCookies){
        RequestFactory.cookies = newCookies;
    }
}

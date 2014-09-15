package com.antyzero.njumeter.network;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.IOException;
import java.net.URI;

/**
 * Customized request factory
 */
public class RequestFactory extends SimpleClientHttpRequestFactory {

    /**
     * Inject extra headers
     *
     * {@inheritDoc}
     */
    @Override
    public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {

        ClientHttpRequest request = super.createRequest(uri, httpMethod);

        final HttpHeaders headers = request.getHeaders();

        headers.setUserAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/28.0.1500.52 Chrome/28.0.1500.52 Safari/537.36");

        return request;
    }
}

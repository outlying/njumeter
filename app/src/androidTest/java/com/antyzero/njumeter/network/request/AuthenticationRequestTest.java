package com.antyzero.njumeter.network.request;

public class AuthenticationRequestTest extends RequestTestCase {

    public void testLoadDataFromNetwork() throws Exception {

        AuthenticationRequest request = new AuthenticationRequest("123456789", "password");

        request.setRestTemplate(restTemplate);

        request.loadDataFromNetwork();
    }
}
package com.antyzero.njumeter.network.request;

public class LoginRequestTest extends RequestTestCase {

    public void testLoadDataFromNetwork() throws Exception {

        LoginRequest request = new LoginRequest("123456789", "password");

        request.setRestTemplate(restTemplate);

        request.loadDataFromNetwork();
    }
}
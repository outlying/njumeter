package com.antyzero.njumeter.network.request;

/**
 *
 */
public class AuthenticationRequestTest extends RequestTestCase {

    public void testLoginWithNonExistingUser() throws Exception {

        AuthenticationRequest request = new AuthenticationRequest("123456789", "password");

        request.setRestTemplate(getRestTemplate());

        request.loadDataFromNetwork();
    }

    public void testLoginWithValidUser() throws Exception {

        AuthenticationRequest request = new AuthenticationRequest("123456789", "password");

        request.setRestTemplate(getRestTemplate());

        request.loadDataFromNetwork();
    }
}
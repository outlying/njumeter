package com.antyzero.njumeter.network.request;

import android.test.FlakyTest;

/**
 * Testing behaviour of login
 */
public class AuthenticationRequestTest extends RequestTestCase {

    @FlakyTest( tolerance = 3 )
    public void testLoginWithNonExistingUser() throws Exception {

        AuthenticationRequest request = new AuthenticationRequest("123456789", "password");

        request.setRestTemplate(getRestTemplate());

        request.loadDataFromNetwork();
    }

    @FlakyTest( tolerance = 3 )
    public void testLoginWithValidUser() throws Exception {

        AuthenticationRequest request = new AuthenticationRequest("123456789", "password");

        request.setRestTemplate(getRestTemplate());

        request.loadDataFromNetwork();
    }
}
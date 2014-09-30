package com.antyzero.njumeter.network.request;

import android.test.FlakyTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing behaviour of login
 */
public class AuthenticationRequestTest extends RequestTestCase {

    @FlakyTest( tolerance = 3 )
    public void testLoginWithNonExistingUser() throws Exception {

        boolean exceptionOccurred = false;

        AuthenticationRequest request = new AuthenticationRequest("123456789", "password");

        request.setRestTemplate(getRestTemplate());

        try {
            request.loadDataFromNetwork();
        } catch (Exception e){
            exceptionOccurred = true;
        }

        assertThat(exceptionOccurred).isTrue();
    }

    @FlakyTest( tolerance = 3 )
    public void testLoginWithValidUser() throws Exception {

        AuthenticationRequest request = new AuthenticationRequest("123456789", "password");

        request.setRestTemplate(getRestTemplate());

        request.loadDataFromNetwork();
    }
}
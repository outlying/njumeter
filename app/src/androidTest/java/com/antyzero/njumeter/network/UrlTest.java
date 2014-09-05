package com.antyzero.njumeter.network;

import junit.framework.TestCase;

import static org.assertj.core.api.Assertions.assertThat;

public class UrlTest extends TestCase {

    public void testLogin() throws Exception {
        assertThat(String.valueOf(Url.login())).contains("https://www.njumobile.pl/logowanie");
    }
}
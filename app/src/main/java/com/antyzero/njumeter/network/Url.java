package com.antyzero.njumeter.network;

import java.net.URI;

/**
 *
 */
public class Url {

    private static final String URL_BASE = "https://www.njumobile.pl";

    private static final String SEGMENT_LOGIN = "/logowanie";

    private Url() {
        throw new IllegalAccessError();
    }

    /**
     * Generate login screen Url
     *
     * @return URI object
     */
    public static URI login() {
        return URI.create(URL_BASE + SEGMENT_LOGIN);
    }

}

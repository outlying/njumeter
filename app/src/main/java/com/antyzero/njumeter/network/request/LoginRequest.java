package com.antyzero.njumeter.network.request;

import com.antyzero.njumeter.network.Url;

/**
 *
 */
public class LoginRequest extends BaseRequest<Void> {

    private final CharSequence user;
    private final CharSequence password;

    public LoginRequest(CharSequence user, CharSequence password) {
        super(Void.class);

        this.user = user;
        this.password = password;
    }

    @Override
    public Void loadDataFromNetwork() throws Exception {

        String websiteLogin = getRestTemplate().getForObject(Url.login(), String.class);



        return null;
    }
}

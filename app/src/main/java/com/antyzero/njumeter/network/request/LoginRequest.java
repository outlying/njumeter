package com.antyzero.njumeter.network.request;

import com.antyzero.njumeter.network.Url;

/**
 * Created by tornax on 04.09.14.
 */
public class LoginRequest extends BaseRequest<Void> {

    public LoginRequest() {
        super(Void.class);
    }

    @Override
    public Void loadDataFromNetwork() throws Exception {

        String websiteLogin = getRestTemplate().getForObject(Url.login(), String.class);

        

        return null;
    }
}

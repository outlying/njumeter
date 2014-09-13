package com.antyzero.njumeter.network.request;

import com.antyzero.njumeter.network.Url;
import com.antyzero.njumeter.network.html.Form;

/**
 *
 */
public class AuthenticationRequest extends BaseRequest<Boolean> {

    private final CharSequence user;
    private final CharSequence password;

    public AuthenticationRequest( CharSequence user, CharSequence password ) {
        super(Boolean.class);

        this.user = user;
        this.password = password;
    }

    @Override
    public Boolean loadDataFromNetwork() throws Exception {

        String websiteLogin = getRestTemplate().getForObject(Url.login(), String.class);

        Form form = Form.from( websiteLogin ).withId( "portal-login-form" ).build();

        form.toString();

        return true;
    }
}

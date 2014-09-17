package com.antyzero.njumeter.network.request;

import com.antyzero.njumeter.network.Url;
import com.antyzero.njumeter.network.html.Form;

import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AuthenticationRequest extends BaseRequest<Boolean> {

    private final static String FIELD_USER = "login-form";
    private final static String FIELD_PASSWORD = "password-form";

    private final static List<String> FIELDS_ALL;

    private final String user;
    private final String password;


    /**
     *
     */
    static {

        List<String> fields = new ArrayList<>();

        fields.add(FIELD_USER);
        fields.add(FIELD_PASSWORD);

        FIELDS_ALL = fields;
    }

    /**
     * Base constructor
     *
     * @param user
     * @param password
     */
    public AuthenticationRequest(CharSequence user, CharSequence password) {
        super(Boolean.class);

        this.user = String.valueOf(user);
        this.password = String.valueOf(password);
    }

    @Override
    public Boolean loadDataFromNetwork() throws Exception {

        ResponseEntity<String> entityLogin = getRestTemplate().getForEntity(Url.login(), String.class);

        String websiteLogin = entityLogin.getBody();

        Form form = Form.from(websiteLogin).withId("portal-login-form").build();

        MultiValueMap<String, String> fields = new LinkedMultiValueMap<>();

        fields.setAll(form.getInputs().toMap());

        checkKeys(fields);

        fields.set(FIELD_PASSWORD, password);
        fields.set(FIELD_USER, user);

        HttpHeaders requestHeaders = new HttpHeaders();

        requestHeaders.set( "Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8" );
        requestHeaders.set( "Accept-Encoding", "gzip,deflate,sdch" );
        requestHeaders.set( "Referer", String.valueOf( Url.login() ) );

        requestHeaders.setCacheControl("max-age=0");

        requestHeaders.setAcceptLanguage( "pl,en-US;q=0.8,en;q=0.6" );
        requestHeaders.setContentType( MediaType.APPLICATION_FORM_URLENCODED );
        requestHeaders.setAcceptEncoding( ContentCodingType.ALL );

        HttpEntity<?> httpEntity = new HttpEntity<Object>( fields, requestHeaders );

        ResponseEntity<String> entity = getRestTemplate().postForEntity(form.getAction(), httpEntity, String.class);

        if( entity.getStatusCode().value() == 302){
            entity = getRestTemplate().getForEntity(entity.getHeaders().getLocation(), String.class);
        }

        // TODO check errors

        entity.toString();

        return true;
    }

    /**
     * Validate if proper form was found
     *
     * @param map form fields
     * @throws IllegalStateException
     */
    private void checkKeys(MultiValueMap<String, String> map) throws IllegalStateException {

        for (String field : FIELDS_ALL) {

            if (!map.containsKey(field)) {
                throw new IllegalStateException("Form does not contain valid fields");
            }
        }
    }
}

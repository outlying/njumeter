package com.antyzero.njumeter.network.request;

import android.test.ApplicationTestCase;

import com.antyzero.njumeter.NjuApplication;
import com.antyzero.njumeter.network.SpiceService;

import org.springframework.web.client.RestTemplate;

/**
 * Created by tornax on 04.09.14.
 */
abstract class RequestTestCase extends ApplicationTestCase<NjuApplication> {

    private RestTemplate restTemplate;

    public RequestTestCase() {
        super(NjuApplication.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        restTemplate = new SpiceService().createRestTemplate();
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}

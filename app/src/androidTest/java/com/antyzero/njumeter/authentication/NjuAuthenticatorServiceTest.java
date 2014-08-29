package com.antyzero.njumeter.authentication;

import android.content.Intent;
import android.test.ServiceTestCase;

import static org.assertj.core.api.Assertions.assertThat;

public class NjuAuthenticatorServiceTest extends ServiceTestCase<NjuAuthenticatorService> {

    /**
     * {@inheritDoc}
     */
    public NjuAuthenticatorServiceTest() {
        super(NjuAuthenticatorService.class);
    }

    /**
     * Test binding
     */
    public void testBind() {
        assertThat(bindService(new Intent())).isNotNull();
    }
}
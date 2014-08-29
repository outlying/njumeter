package com.antyzero.njumeter.authentication;

import android.os.Bundle;
import android.test.ApplicationTestCase;

import com.antyzero.njumeter.NjuApplication;

import static org.assertj.android.api.Assertions.assertThat;

/**
 *
 */
public class NjuAccountAuthenticatorTest extends ApplicationTestCase<NjuApplication> {

    private NjuAccountAuthenticator authenticator;

    public NjuAccountAuthenticatorTest() {
        super(NjuApplication.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        createApplication();
        authenticator = new NjuAccountAuthenticator(getApplication());
    }

    public void testAddAccount() throws Exception {
        Bundle bundle = authenticator.addAccount(null, null, null, null, null);
        assertThat(bundle).isNotNull();
    }
}

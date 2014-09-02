package com.antyzero.njumeter.ui;

import android.test.ActivityInstrumentationTestCase2;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthenticationActivityTest extends ActivityInstrumentationTestCase2<AuthenticationActivity> {

    public AuthenticationActivityTest() {
        super(AuthenticationActivity.class);
    }

    public void testCreation() throws Exception {
        assertThat(getActivity()).isNotNull();
    }
}
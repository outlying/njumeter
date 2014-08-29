package com.antyzero.njumeter.ui;

import android.test.ActivityInstrumentationTestCase2;

import static org.assertj.core.api.Assertions.assertThat;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void testCreation() throws Exception {
        assertThat(getActivity()).isNotNull();
    }
}
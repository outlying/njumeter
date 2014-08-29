package com.antyzero.njumeter;

import android.test.ApplicationTestCase;

/**
 * Test proper application setup and creation
 */
public class ApplicationTest extends ApplicationTestCase<NjuApplication> {

    public ApplicationTest() {
        super(NjuApplication.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        createApplication();
    }
}
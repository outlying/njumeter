package com.antyzero.njumeter;

import junit.framework.TestCase;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class DumbTest extends TestCase {

    public void testFail() {
        assertThat(false).isTrue();
    }
}

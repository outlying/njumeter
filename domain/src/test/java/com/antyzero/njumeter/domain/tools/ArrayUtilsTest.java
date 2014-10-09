package com.antyzero.njumeter.domain.tools;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ArrayUtilsTest {

    @Test
    public void testJoin() throws Exception {

        Integer[] integers1 = new Integer[]{1,2};
        Integer[] integers2 = new Integer[]{3,4};

        Integer[] result = ArrayUtils.join(integers1, integers2);

        final int expectedLength = integers1.length + integers2.length;

        assertThat(result.length).isEqualTo(expectedLength);

        for( int i = 1; i <= expectedLength; i++ ){
            assertThat(result[i-1]).isEqualTo(i);
        }
    }
}
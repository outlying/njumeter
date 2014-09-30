package com.antyzero.njumeter;

import java.util.Arrays;

/**
 * Base modules provider
 */
public class Modules {

    /**
     * List base modules, olso those that requires
     */
    public static Object[] list(NjuApplication application) {
        return Arrays.asList(
                new NjuModule(application)
        ).toArray();
    }
}

package com.antyzero.njumeter.ui;

import android.app.Activity;

import com.antyzero.njumeter.messenger.MessengerModule;
import com.antyzero.njumeter.network.NetworkModule;

import java.util.Arrays;

import dagger.ObjectGraph;

/**
 *
 */
final class ActivityModules {

    private static final Object[] MODULES = Arrays.<Object>asList(
            MessengerModule.class,
            NetworkModule.class)
            .toArray();

    /**
     *
     */
    private ActivityModules() {
        throw new IllegalArgumentException();
    }

    /**
     *
     *
     * @param activity
     */
    public static void inject(Activity activity) {
        ObjectGraph.create(MODULES).inject(activity);
    }
}

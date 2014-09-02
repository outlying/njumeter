package com.antyzero.njumeter;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

/**
 *
 */
public class NjuApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // For non debug builds use Crashlytics reports
        if(!BuildConfig.DEBUG) {
            Crashlytics.start(this);
        }
    }
}

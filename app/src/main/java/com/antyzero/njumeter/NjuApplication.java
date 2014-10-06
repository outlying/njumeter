package com.antyzero.njumeter;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;

import dagger.ObjectGraph;

/**
 *
 */
public final class NjuApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // For non debug builds use Crashlytics reports
        if (!BuildConfig.DEBUG) {
            Crashlytics.start(this);
        }

    }

    /**
     * Access NjuApplication instance
     *
     * @param context for accessing
     * @return NjuApplication object
     */
    public static NjuApplication get(Context context) {
        return (NjuApplication) context.getApplicationContext();
    }
}

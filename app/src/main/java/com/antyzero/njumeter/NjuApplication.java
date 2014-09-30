package com.antyzero.njumeter;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;

import dagger.ObjectGraph;

/**
 *
 */
public final class NjuApplication extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        // For non debug builds use Crashlytics reports
        if (!BuildConfig.DEBUG) {
            Crashlytics.start(this);
        }

        buildObjectGraphAndInject();
    }

    public void buildObjectGraphAndInject() {
        objectGraph = ObjectGraph.create(Modules.list(this));
        objectGraph.inject(this);
    }

    public void inject(Object o) {
        objectGraph.inject(o);
    }

    public static NjuApplication get(Context context) {
        return (NjuApplication) context.getApplicationContext();
    }
}

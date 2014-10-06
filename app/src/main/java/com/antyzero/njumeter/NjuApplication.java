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

        objectGraph = ObjectGraph.create();
        objectGraph.inject(this);
    }

    /**
     * TODO
     *
     * @param modules
     * @return
     */
    public ObjectGraph createScopedGraph(Object... modules) {
        return objectGraph.plus(modules);
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

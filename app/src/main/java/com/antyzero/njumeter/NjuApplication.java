package com.antyzero.njumeter;

import android.app.Application;

import com.antyzero.njumeter.network.NetworkModule;
import com.crashlytics.android.Crashlytics;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

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

        /*objectGraph = ObjectGraph.create(getModules().toArray());
        objectGraph.inject(this);*/
    }

    private List<Object> getModules() {
        return Arrays.<Object>asList(
                new NetworkModule());
    }
}

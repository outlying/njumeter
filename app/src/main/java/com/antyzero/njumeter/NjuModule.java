package com.antyzero.njumeter;

import android.app.Application;

import com.antyzero.njumeter.network.NetworkModule;
import com.antyzero.njumeter.ui.UiModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Application module
 */
@Module(
        includes = {
                NetworkModule.class,
                UiModule.class
        },
        injects = {
                NjuApplication.class
        },
        library = true
)
public class NjuModule {

    private final NjuApplication application;

    public NjuModule(NjuApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }
}

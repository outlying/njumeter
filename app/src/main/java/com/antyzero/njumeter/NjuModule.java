package com.antyzero.njumeter;

import android.app.Application;

import com.antyzero.njumeter.messenger.MessengerModule;
import com.antyzero.njumeter.network.NetworkModule;

import dagger.Module;

/**
 * Application module
 */
@Module(
        includes = {
                NetworkModule.class,
                MessengerModule.class
        },
        injects = {
                NjuApplication.class
        }
)
public class NjuModule {

    private final NjuApplication application;

    public NjuModule(NjuApplication application) {
        this.application = application;
    }

    Application provideApplication() {
        return application;
    }
}

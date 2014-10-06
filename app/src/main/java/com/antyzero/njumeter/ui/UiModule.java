package com.antyzero.njumeter.ui;

import com.antyzero.njumeter.messenger.Messenger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provides UI components
 */
@Module(
        injects = {
                MainActivity.class,
                AuthenticationActivity.class
        },
        library = true,
        complete = false
)
public class UiModule {

    private Messenger messenger = new Messenger();

    @Provides
    @Singleton
    public Messenger providesMessenger() {
        return messenger;
    }
}

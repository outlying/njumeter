package com.antyzero.njumeter.ui;

import com.antyzero.njumeter.messenger.CroutonMessenger;
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

    @Provides
    @Singleton
    public Messenger providesMessenger() {
        return new CroutonMessenger();
    }
}

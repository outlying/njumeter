package com.antyzero.njumeter.messenger;

import com.antyzero.njumeter.ui.AuthenticationActivity;
import com.antyzero.njumeter.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Injects messenger
 */
@Module(injects = {
        MainActivity.class,
        AuthenticationActivity.class})
public class MessengerModule {

    private Messenger messenger = new Messenger();

    @Provides
    @Singleton
    public Messenger providesMessenger() {
        return messenger;
    }
}

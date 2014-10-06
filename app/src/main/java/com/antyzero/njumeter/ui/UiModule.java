package com.antyzero.njumeter.ui;

import android.app.Activity;

import com.antyzero.njumeter.NjuModule;
import com.antyzero.njumeter.messenger.Messenger;
import com.antyzero.njumeter.messenger.ToastMessenger;
import com.antyzero.njumeter.ui.progress.ActionBarProgressIndicator;
import com.antyzero.njumeter.ui.progress.ProgressIndicator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provides UI components
 */
@Module(
        addsTo = NjuModule.class,
        injects = {
                MainActivity.class,
                AuthenticationActivity.class
        },
        library = true
)
public class UiModule {

    private final Activity activity;

    public UiModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    public Messenger providesMessenger() {
        return new ToastMessenger();
    }

    @Provides
    public ProgressIndicator providesProgressIndicator() {
        return new ActionBarProgressIndicator(activity);
    }
}

package com.antyzero.njumeter.ui;

import android.app.Activity;

import com.antyzero.njumeter.NjuModule;
import com.antyzero.njumeter.messenger.Messenger;
import com.antyzero.njumeter.messenger.ToastMessenger;
import com.antyzero.njumeter.network.SpiceService;
import com.octo.android.robospice.SpiceManager;

import dagger.Module;
import dagger.Provides;

@Module(
        addsTo = NjuModule.class,
        injects = {
                MainActivity.class,
                AuthenticationActivity.class
        }
)
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    public Messenger providesMessenger(){
        return new ToastMessenger();
    }

    @Provides
    public SpiceManager providesSpiceManager(){
        return new SpiceManager( SpiceService.class );
    }
}

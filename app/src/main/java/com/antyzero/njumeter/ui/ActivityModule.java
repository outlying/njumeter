package com.antyzero.njumeter.ui;

import android.app.Activity;

import com.antyzero.njumeter.NjuModule;
import com.antyzero.njumeter.messenger.Messenger;
import com.antyzero.njumeter.messenger.ToastMessenger;
import com.antyzero.njumeter.network.SpiceService;
import com.antyzero.njumeter.ui.progress.ActionBarProgressIndicator;
import com.antyzero.njumeter.ui.progress.ProgressIndicator;
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

    // TODO move it to another module
    @Provides
    public SpiceManager providesSpiceManager(){
        return new SpiceManager( SpiceService.class );
    }

    @Provides
    public ProgressIndicator providesProgressIndicator() {
        return new ActionBarProgressIndicator(activity);
    }
}

package com.antyzero.njumeter.network;

import com.antyzero.njumeter.ui.AuthenticationActivity;
import com.antyzero.njumeter.ui.MainActivity;
import com.octo.android.robospice.SpiceManager;

import dagger.Module;
import dagger.Provides;

/**
 * Provides network components
 */
@Module(injects = {
        MainActivity.class,
        AuthenticationActivity.class},
        complete = false,
        library = true)
public class NetworkModule {

    @Provides
    public SpiceManager provideSpiceManager(){
        return new SpiceManager(SpiceService.class);
    }
}

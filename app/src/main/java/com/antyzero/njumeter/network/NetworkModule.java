package com.antyzero.njumeter.network;

import com.octo.android.robospice.SpiceManager;

import dagger.Module;
import dagger.Provides;

/**
 * Provides network components
 */
@Module(injects = {SpiceManager.class})
public class NetworkModule {

    @Provides
    public SpiceManager provideSpiceManager(){
        return new SpiceManager(SpiceService.class);
    }
}

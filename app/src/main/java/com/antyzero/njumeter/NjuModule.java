package com.antyzero.njumeter;

import dagger.Module;

@Module(
        injects = NjuApplication.class
)
public class NjuModule {

    private final NjuApplication njuApplication;

    public NjuModule(NjuApplication njuApplication) {
        this.njuApplication = njuApplication;
    }
}

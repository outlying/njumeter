package com.antyzero.njumeter.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.antyzero.njumeter.NjuApplication;
import com.antyzero.njumeter.messenger.Message;
import com.antyzero.njumeter.messenger.Messenger;
import com.antyzero.njumeter.network.NetworkModule;
import com.antyzero.njumeter.network.SpiceService;
import com.google.common.collect.Lists;
import com.octo.android.robospice.SpiceManager;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.ObjectGraph;
import de.keyboardsurfer.android.widget.crouton.Crouton;

/**
 * BaseActivity created to provide some common behaviour for all activities in this application
 */
public abstract class BaseActivity extends Activity {

    @Inject
    SpiceManager spiceManager;

    @Inject
    Messenger messenger;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityModules.inject(this);

        messenger.register( this );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        messenger.register(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        messenger.unregister(this);
        super.onStop();
    }

    /**
     * Catch message event and display proper dialog / toast / crouton
     *
     * @param message event
     */
    @SuppressWarnings( "UnusedDeclaration" )
    public void onEventMainThread( Message message ) {
        messenger.process( this, message );
    }

    /**
     * Alternative way to obtain object reference, this one does not require casting in activities
     *
     * @param viewId view resource ID
     * @param <T>    generic type for method
     * @return View class object
     */
    @SuppressWarnings({"unchecked", "UnusedDeclaration"})
    protected <T extends View> T findView( int viewId ) {
        return (T) findViewById( viewId );
    }

    /**
     * Access to SpiceManager for extending classes
     *
     * @return SpiceManager object
     */
    protected SpiceManager getSpiceManager() {
        return spiceManager;
    }

    /**
     * Access to Messenger for extending classes
     *
     * @return SpiceManager object
     */
    protected Messenger getMessenger() {
        return messenger;
    }
}

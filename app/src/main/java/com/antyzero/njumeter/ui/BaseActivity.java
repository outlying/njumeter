package com.antyzero.njumeter.ui;

import android.app.Activity;
import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityModules.inject(this);

        messenger.register(this);
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
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onDestroy() {
        messenger.unregister( this );
        super.onDestroy();
    }

    /**
     * This event listener is only created because EventBus will throw Exception on object without any event listeners
     *
     * @param dumbEvent blank event
     */
    @SuppressWarnings("UnusedDeclaration")
    public void onEvent( Object dumbEvent ) {
        // do nothing
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
    @SuppressWarnings( "unchecked" )
    protected <T> T findView( int viewId ) {
        return (T) findViewById( viewId );
    }

    protected SpiceManager getSpiceManager() {
        return spiceManager;
    }

    protected Messenger getMessenger() {
        return messenger;
    }
}

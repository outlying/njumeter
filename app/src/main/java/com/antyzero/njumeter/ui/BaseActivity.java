package com.antyzero.njumeter.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

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

    private ObjectGraph activityGraph;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityGraph = null;
    }

    /**
     * Catch message event and display proper dialog / toast / crouton
     *
     * @param message event
     */
    @SuppressWarnings( "UnusedDeclaration" )
    public void onEventMainThread( Message message ) {
        //messenger.process( this, message );
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
}

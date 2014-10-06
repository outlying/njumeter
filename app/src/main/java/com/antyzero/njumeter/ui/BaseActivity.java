package com.antyzero.njumeter.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.antyzero.njumeter.NjuApplication;
import com.antyzero.njumeter.messenger.Message;
import com.antyzero.njumeter.messenger.Messenger;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * BaseActivity created to provide some common behaviour for all activities in this application
 */
public abstract class BaseActivity extends Activity {

    private ObjectGraph activityGraph;

    @Inject
    Messenger messenger;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        activityGraph = NjuApplication.get( this ).createScopedGraph( new ActivityModule( this ) );
        activityGraph.inject( this );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStart() {
        super.onStart();
        messenger.register( this );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStop() {
        messenger.unregister( this );
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
     * Messenger accessor
     *
     * @return Messenger object
     */
    public Messenger getMessenger() {
        return messenger;
    }
}

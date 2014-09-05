package com.antyzero.njumeter.ui;

import android.app.Activity;
import android.os.Bundle;

import com.antyzero.njumeter.messenger.Message;
import com.antyzero.njumeter.messenger.Messenger;
import com.antyzero.njumeter.network.SpiceService;
import com.octo.android.robospice.SpiceManager;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * BaseActivity created to provide some common behaviour for all activities in this application
 */
class BaseActivity extends Activity {

    private SpiceManager spiceManager = new SpiceManager( SpiceService.class );

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        Messenger.INSTANCE.getEventBus().register( this );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onDestroy() {
        Crouton.cancelAllCroutons();
        Messenger.INSTANCE.getEventBus().unregister( this );
        super.onDestroy();
    }

    /**
     * This event listener is only created because EventBus will throw Exception on object without any event listeners
     *
     * @param dumbEvent blank event
     */
    public void onEvent(Object dumbEvent) {
        // do nothing
    }

    /**
     * Catch message event and display proper dialog / toast / crouton
     *
     * @param message event
     */
    @SuppressWarnings( "UnusedDeclaration" )
    public void onEvent(Message message) {
        Crouton.makeText( this, message.getText(), Style.INFO ).show();
    }

    /**
     * Access to SpiceManager
     *
     * @return SpiceManager object
     */
    protected SpiceManager getSpiceManager() {
        return spiceManager;
    }

    /**
     * Alternative way to obtain object reference, this one does not require casting in activities
     *
     * @param viewId view resource ID
     * @param <T> generic type for method
     * @return View class object
     */
    @SuppressWarnings( "unchecked" )
    protected <T> T findView( int viewId ) {
        return (T) findViewById( viewId );
    }
}

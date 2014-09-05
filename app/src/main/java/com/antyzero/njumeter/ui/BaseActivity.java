package com.antyzero.njumeter.ui;

import android.app.Activity;
import android.os.Bundle;

import com.antyzero.njumeter.messenger.Messenger;
import com.antyzero.njumeter.network.SpiceService;
import com.octo.android.robospice.SpiceManager;

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
        Messenger.INSTANCE.getEventBus().unregister( this );
        super.onDestroy();
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

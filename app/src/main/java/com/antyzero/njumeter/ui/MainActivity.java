package com.antyzero.njumeter.ui;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.antyzero.njumeter.BuildConfig;
import com.antyzero.njumeter.R;
import com.antyzero.njumeter.messenger.Messenger;
import com.antyzero.njumeter.network.NetworkModule;
import com.octo.android.robospice.SpiceManager;

import javax.inject.Inject;

import dagger.ObjectGraph;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * ...
 */
public class MainActivity extends BaseActivity {

    private static final int REQUEST_CODE_ACCOUNT_CREATE = 30471;

    private Button button;

    @Inject
    SpiceManager spiceManager;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

        // First Activity creation
        if( savedInstanceState == null ) {

            // Check if we have at least one account, if not redirect user to login screen
            if (AccountManager.get(this).getAccountsByType(BuildConfig.ACCOUNT_TYPE).length == 0) {
                AuthenticationActivity.startForNewAccountResult(this, REQUEST_CODE_ACCOUNT_CREATE);
            }
        }

        setContentView( R.layout.activity_main );

        button = findView(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Messenger.INSTANCE.message("test" + System.currentTimeMillis());
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {

        if( requestCode == REQUEST_CODE_ACCOUNT_CREATE ) {

            // TODO resources
            if( resultCode == Activity.RESULT_OK ) {

                //Messenger.INSTANCE.message( "Konto utworzone" );

                Crouton.makeText(this, "QQQ", Style.INFO).show();

            } else {
                //Messenger.INSTANCE.message("Konto nie utworzone");
            }
        }
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
}

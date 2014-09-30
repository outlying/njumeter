package com.antyzero.njumeter.ui;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.antyzero.njumeter.BuildConfig;
import com.antyzero.njumeter.R;
import com.antyzero.njumeter.messenger.Message;
import com.antyzero.njumeter.network.request.AuthenticationRequest;
import com.antyzero.njumeter.network.request.RequestListener;
import com.octo.android.robospice.persistence.exception.SpiceException;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * ...
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_ACCOUNT_CREATE = 30471;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        // First Activity creation
        if( savedInstanceState == null ) {

            // Check if we have at least one account, if not redirect user to login screen
            if( AccountManager.get( this ).getAccountsByType( BuildConfig.ACCOUNT_TYPE ).length == 0 ) {
                AuthenticationActivity.startForNewAccountResult( this, REQUEST_CODE_ACCOUNT_CREATE );
            }
        }

        setContentView( R.layout.activity_main );

        findView( R.id.button ).setOnClickListener(this);
    }



    /**
     * {@inheritDoc}
     */
    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {

        if( requestCode == REQUEST_CODE_ACCOUNT_CREATE ) {

            Message.Builder builder = Message.prepare();

            if( resultCode == Activity.RESULT_OK ) {
                builder.setMessage( getString( R.string.message_confirm_account_created ) );
                builder.setStyle( Message.Style.CONFIRM );
            } else {
                builder.setMessage( getString( R.string.message_confirm_account_not_created ) );
                builder.setStyle(Message.Style.ERROR);
            }

            messenger.message( builder.build() );
        }
    }

    @Override
    public void onClick(View v) {
        messenger.message("Q");
    }
}

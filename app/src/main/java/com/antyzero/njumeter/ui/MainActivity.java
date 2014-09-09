package com.antyzero.njumeter.ui;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.antyzero.njumeter.BuildConfig;
import com.antyzero.njumeter.R;
import com.antyzero.njumeter.messenger.Messenger;

public class MainActivity extends BaseActivity {

    private static final int REQUEST_CODE_ACCOUNT_CREATE = 30471;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        // Check if we have at least one account, if not redirect user to login screen
        if( AccountManager.get( this ).getAccountsByType( BuildConfig.ACCOUNT_TYPE ).length == 0 ) {
            AuthenticationActivity.startForResult( this, REQUEST_CODE_ACCOUNT_CREATE );
        }

        setContentView( R.layout.activity_main );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {

        if( requestCode == REQUEST_CODE_ACCOUNT_CREATE ) {

            // TODO resources
            if( resultCode == Activity.RESULT_OK ) {
                Messenger.INSTANCE.message( "Konto utworzone" );
            } else {
                Messenger.INSTANCE.message( "Konto nie utworzone" );
            }
        }
    }
}

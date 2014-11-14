package com.antyzero.njumeter.ui;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.antyzero.njumeter.BuildConfig;
import com.antyzero.njumeter.R;
import com.antyzero.njumeter.messenger.Message;
import com.antyzero.njumeter.messenger.Messenger;
import com.antyzero.njumeter.network.request.AuthenticationRequest;
import com.antyzero.njumeter.network.request.RequestListener;
import com.octo.android.robospice.persistence.exception.SpiceException;

import javax.inject.Inject;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * ...
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_ACCOUNT_CREATE = 30471;

    private View containerNoAccount;

    private Button buttonAddAccount;

    @Inject
    protected Messenger messenger;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        // First Activity creation
        if( savedInstanceState == null ) {

            // Check if we have at least one account, if not redirect user to login screen
            if(hasAnyAccounts()) {
                startAccountCreationActivity();
            }
        }

        setContentView( R.layout.activity_main );

        containerNoAccount = findView(R.id.containerNoAccount);
        buttonAddAccount = findView(R.id.buttonAddAccount);
        buttonAddAccount.setOnClickListener(this);
    }

    /**
     * Starts AuthenticationActivity for new account
     */
    private void startAccountCreationActivity() {
        AuthenticationActivity.startForNewAccountResult(this, REQUEST_CODE_ACCOUNT_CREATE);
    }

    /**
     * Checks if at least one account is avaiable
     *
     * @return {@code true} if account(s) is/are available
     */
    private boolean hasAnyAccounts() {
        return AccountManager.get(this)
                .getAccountsByType(BuildConfig.ACCOUNT_TYPE).length == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {

        if( requestCode == REQUEST_CODE_ACCOUNT_CREATE ) {

            Message.Builder builder = Message.prepare();

            if( resultCode != Activity.RESULT_OK ) {
                builder.setMessage( getString( R.string.message_confirm_account_not_created ) );
                builder.setStyle(Message.Style.ERROR);

                containerNoAccount.setVisibility(View.VISIBLE);
                // TODO hide others
            }

            getMessenger().message( builder.build() );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.buttonAddAccount:
                startAccountCreationActivity();
                break;

            default:
                throw new UnsupportedOperationException("Click is not yet implemented");
        }
    }
}

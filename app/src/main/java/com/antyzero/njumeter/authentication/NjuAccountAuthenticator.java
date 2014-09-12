package com.antyzero.njumeter.authentication;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.antyzero.njumeter.NjuApplication;
import com.antyzero.njumeter.ui.AuthenticationActivity;

import javax.inject.Inject;

/**
 *
 */
public class NjuAccountAuthenticator extends AbstractAccountAuthenticator {

    private final Context context;

    public NjuAccountAuthenticator( Context context ) {
        super( context );
        this.context = context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bundle editProperties( AccountAuthenticatorResponse response, String accountType ) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bundle addAccount( AccountAuthenticatorResponse response, String accountType, String authTokenType,
                              String[] requiredFeatures, Bundle options ) throws NetworkErrorException {

        final Intent intent = AuthenticationActivity.intent(context, AuthenticationActivity.Action.ADD_NEW_ACCOUNT);

        intent.putExtra( AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response );

        final Bundle bundle = new Bundle();

        bundle.putParcelable( AccountManager.KEY_INTENT, intent );

        return bundle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bundle confirmCredentials( AccountAuthenticatorResponse response, Account account,
                                      Bundle options ) throws NetworkErrorException {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bundle getAuthToken( AccountAuthenticatorResponse response, Account account, String authTokenType,
                                Bundle options ) throws NetworkErrorException {

        // Extract the username and password from the Account Manager, and ask
        // the server for an appropriate AuthToken.
        final AccountManager accountManager = AccountManager.get( context );

        String authToken = accountManager.peekAuthToken( account, authTokenType );

        // Lets give another try to authenticate the user
        if( TextUtils.isEmpty( authToken ) ) {
            final String password = accountManager.getPassword( account );
            if( password != null ) {
                // authToken = sServerAuthenticate.userSignIn(account.name, password, authTokenType);
            }
        }

        // If we get an authToken - we return it
        if( !TextUtils.isEmpty( authToken ) ) {
            final Bundle result = new Bundle();
            result.putString( AccountManager.KEY_ACCOUNT_NAME, account.name );
            result.putString( AccountManager.KEY_ACCOUNT_TYPE, account.type );
            result.putString( AccountManager.KEY_AUTHTOKEN, authToken );
            return result;
        }

        // If we get here, then we couldn't access the user's password - so we
        // need to re-prompt them for their credentials. We do that by creating
        // an intent to display our AuthenticatorActivity.

        final Intent intent = new Intent();

        /*final Intent intent = new Intent(context, AuthenticatorActivity.class);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
        intent.putExtra(AuthenticatorActivity.ARG_ACCOUNT_TYPE, account.type);
        intent.putExtra(AuthenticatorActivity.ARG_AUTH_TYPE, authTokenType);*/

        final Bundle bundle = new Bundle();

        bundle.putParcelable( AccountManager.KEY_INTENT, intent );

        return bundle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAuthTokenLabel( String authTokenType ) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bundle updateCredentials( AccountAuthenticatorResponse response, Account account, String authTokenType,
                                     Bundle options ) throws NetworkErrorException {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bundle hasFeatures( AccountAuthenticatorResponse response, Account account,
                               String[] features ) throws NetworkErrorException {
        return null;
    }
}

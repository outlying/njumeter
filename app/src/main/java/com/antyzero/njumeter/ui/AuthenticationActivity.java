package com.antyzero.njumeter.ui;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.antyzero.njumeter.NjuApplication;
import com.antyzero.njumeter.R;
import com.antyzero.njumeter.messenger.Message;
import com.antyzero.njumeter.messenger.Messenger;
import com.antyzero.njumeter.network.request.AuthenticationRequest;
import com.antyzero.njumeter.network.request.RequestListener;
import com.antyzero.njumeter.network.request.ServerSideException;
import com.antyzero.njumeter.tools.SimpleTextWatcher;
import com.antyzero.njumeter.ui.progress.ProgressIndicator;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;

import javax.inject.Inject;

import dagger.ObjectGraph;

import static com.antyzero.njumeter.BuildConfig.ACCOUNT_TYPE;
import static com.antyzero.njumeter.messenger.Message.Style;

/**
 * Provides authentication form
 */
public class AuthenticationActivity extends AccountAuthenticatorActivity implements View.OnClickListener {

    public static final String AUTH_TOKEN_DEFAULT = "000000000000000";
    public static final String AUTH_TOKEN_TYPE = "Default";

    private Action action;

    private ObjectGraph activityGraph;

    Button button;
    EditText editTextUser;
    EditText editTextPassword;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        if( TextUtils.isEmpty( getIntent().getAction() ) ) {
            throw new IllegalStateException( "Missing Intent action, required to start this Activity" );
        }

        try {
            action = Action.valueOf( getIntent().getAction() );
        } catch( Exception e ) {
            throw new IllegalArgumentException( "Given action is not supported" );
        }

        setContentView( R.layout.activity_authentication );

        button = (Button) findViewById( R.id.button );
        button.setOnClickListener( this );

        editTextUser = (EditText) findViewById( R.id.editTextUser );
        editTextUser.addTextChangedListener( new UserTextWatcher() );

        editTextPassword = (EditText) findViewById( R.id.editTextPassword );
        editTextPassword.addTextChangedListener( new PasswordTextWatcher() );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStart() {
        super.onStart();
        /*spiceManager.start( this );
        messenger.register( this );*/
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStop() {
        /*messenger.unregister( this );
        spiceManager.shouldStop();*/
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
    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread( Message message ) {
        /*messenger.process( this, message );*/
    }

    /**
     * Listen for login button click
     * <p/>
     * {@inheritDoc}
     */
    @Override
    public void onClick( View v ) {

        setFormEnable( false );

        // TODO validation ?

        CharSequence user = editTextUser.getText();
        CharSequence password = editTextPassword.getText();

        setProgressBarIndeterminateVisibility( true );

        /*spiceManager.execute(
                new AuthenticationRequest( user, password ),
                new AuthenticationRequestListener() );*/
    }

    /**
     * Control enable state of form
     *
     * @param enable new state value
     */
    private void setFormEnable( boolean enable ) {
        editTextUser.setEnabled( enable );
        editTextPassword.setEnabled( enable );
        button.setEnabled( enable );
    }

    /**
     * This method will add new account to system
     *
     * @param userName
     * @param password
     */
    private void registerNewAccount( String userName, String password ) {

        final Intent intent = new Intent();

        intent.putExtra( AccountManager.KEY_ACCOUNT_NAME, userName );
        intent.putExtra( AccountManager.KEY_ACCOUNT_TYPE, ACCOUNT_TYPE );
        intent.putExtra( AccountManager.KEY_AUTHTOKEN, AUTH_TOKEN_DEFAULT );

        final Account account = new Account( userName, ACCOUNT_TYPE );

        AccountManager accountManager = AccountManager.get( this );

        switch( action ) {

            case ADD_NEW_ACCOUNT:
                accountManager.addAccountExplicitly( account, password, null );
                accountManager.setAuthToken( account, AUTH_TOKEN_TYPE, AUTH_TOKEN_DEFAULT );
                break;

            case CHANGE_PASSWORD:
                accountManager.setPassword( account, password );
                break;

            default:
                throw new UnsupportedOperationException( "Unsupported enum Value" );
        }

        setAccountAuthenticatorResult( intent.getExtras() );
        setResult( RESULT_OK, intent );
        finish();
    }

    /**
     * Start activity to create new user account
     *
     * @param activity require for start
     */
    public static void startForNewAccountResult( Activity activity, int requestCode ) {

        Intent intent = intent( activity, Action.ADD_NEW_ACCOUNT );

        activity.startActivityForResult( intent, requestCode );
    }

    /**
     * Get valid starting Intent
     *
     * @param context required for Intent
     * @return Intent object
     */
    public static Intent intent( Context context, Action action ) {

        final Intent intent = new Intent( context, AuthenticationActivity.class );

        intent.setAction( action.name() );

        return intent;
    }

    /**
     * Watching user / login / phone number EditText
     */
    private class UserTextWatcher extends SimpleTextWatcher {

        @Override
        public void afterTextChanged( Editable editable ) {

            final boolean validLength = editable.length() >= 9;

            editTextPassword.setEnabled( validLength );
        }
    }

    /**
     * Watching password EditText
     */
    private class PasswordTextWatcher extends SimpleTextWatcher {

        @Override
        public void afterTextChanged( Editable editable ) {

            button.setEnabled( !TextUtils.isEmpty( editable ) );
        }
    }

    /**
     * Listen for login response
     */
    private class AuthenticationRequestListener extends RequestListener<Boolean> {

        @Override
        public void preRequest() {
            setProgressBarIndeterminateVisibility( false );
        }

        @Override
        public void onFailure( SpiceException spiceException ) {

            Message.Builder builder = Message.prepare().setStyle( Style.ERROR );

            builder.setMessage( getString( R.string.message_error_auth ) );

            Throwable cause = spiceException.getCause();

            if( cause instanceof ServerSideException ) {
                builder.setMessage( cause.getMessage() );
            }

            if( cause instanceof IllegalStateException ) {
                builder.setMessage( getString( R.string.message_error_website_formatting ) );
            }

            // Give user another chance
            setFormEnable( true );
        }

        @Override
        public void onSuccess( Boolean result ) {

            final String userName = String.valueOf( editTextUser.getText() );
            final String password = String.valueOf( editTextPassword.getText() );

            registerNewAccount( userName, password );
        }
    }

    /**
     * Actions for this Activity
     */
    public enum Action {
        ADD_NEW_ACCOUNT,
        CHANGE_PASSWORD
    }
}

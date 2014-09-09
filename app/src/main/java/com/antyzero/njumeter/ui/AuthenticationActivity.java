package com.antyzero.njumeter.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.antyzero.njumeter.R;
import com.antyzero.njumeter.messenger.Messenger;
import com.antyzero.njumeter.network.request.AuthenticationRequest;
import com.antyzero.njumeter.network.request.RequestListener;
import com.antyzero.njumeter.tools.SimpleTextWatcher;
import com.octo.android.robospice.persistence.exception.SpiceException;

/**
 * Provides authentication form
 */
public class AuthenticationActivity extends BaseActivity implements View.OnClickListener {

    Button button;
    EditText editTextUser;
    EditText editTextPassword;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_authentication );

        button = findView( R.id.button );
        button.setOnClickListener( this );

        editTextUser = findView( R.id.editTextUser );
        editTextUser.addTextChangedListener( new UserTextWatcher() );

        editTextPassword = findView( R.id.editTextPassword );
        editTextPassword.addTextChangedListener( new PasswordTextWatcher() );
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

        getSpiceManager().execute(
                new AuthenticationRequest( user, password ),
                new AuthenticationRequestListener() );
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
     * Start activity to create new user account
     *
     * @param activity require for start
     */
    public static void startForResult( Activity activity, int requestCode ) {

        Intent intent = new Intent( activity, AuthenticationActivity.class );

        activity.startActivityForResult( intent, requestCode );
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
        public void onFailure( SpiceException spiceException ) {

            // TODO support different error types

            Messenger.INSTANCE.message( "Błędny login i/lub hasło, spróbuj ponownie" );

            // Give user another chance
            setFormEnable( true );
        }

        @Override
        public void onSuccess( Boolean result ) {
            // TODO add user login data in Intent
            setResult( RESULT_OK );
            finish();
        }
    }
}

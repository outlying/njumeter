package com.antyzero.njumeter.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.antyzero.njumeter.R;
import com.antyzero.njumeter.messenger.Message;
import com.antyzero.njumeter.messenger.Messenger;
import com.antyzero.njumeter.network.request.LoginRequest;
import com.antyzero.njumeter.tools.SimpleTextWatcher;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

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

        Messenger.INSTANCE.message( "QWEasd" );
    }

    /**
     * Listen for login button click
     * <p/>
     * {@inheritDoc}
     */
    @Override
    public void onClick( View v ) {
        button.setEnabled( false );

        // TODO validation ?

        CharSequence user = editTextUser.getText();
        CharSequence password = editTextPassword.getText();

        getSpiceManager().execute(
                new LoginRequest( user, password ),
                new LoginRequestListener() );
    }

    /**
     * Start activity to create new user account
     *
     * @param activity
     */
    public static void start( Activity activity, int requestCode ) {

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
    private class LoginRequestListener implements RequestListener<Void> {

        @Override
        public void onRequestFailure( SpiceException spiceException ) {

        }

        @Override
        public void onRequestSuccess( Void aVoid ) {

        }
    }
}

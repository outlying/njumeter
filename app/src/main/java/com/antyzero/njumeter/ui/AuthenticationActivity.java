package com.antyzero.njumeter.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
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

    private static final String EXTRA_USER = "extraUser";
    private static final String EXTRA_PASSWORD = "extraPassword";

    Button button;
    EditText editTextUser;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        button = findView(R.id.button);
        button.setOnClickListener(this);

        editTextUser = findView(R.id.editTextUser);
        editTextUser.addTextChangedListener(new UserTextWatcher());

        editTextPassword = findView(R.id.editTextPassword);
        editTextPassword.addTextChangedListener(new PasswordTextWatcher());
    }

    /**
     * Listen for login button click
     * <p/>
     * {@inheritDoc}
     */
    @Override
    public void onClick(View v) {

        setFormEnable(false);

        // TODO validation ?

        CharSequence user = editTextUser.getText();
        CharSequence password = editTextPassword.getText();

        getSpiceManager().execute(
                new AuthenticationRequest(user, password),
                new AuthenticationRequestListener());
    }

    /**
     * Control enable state of form
     *
     * @param enable new state value
     */
    private void setFormEnable(boolean enable) {
        editTextUser.setEnabled(enable);
        editTextPassword.setEnabled(enable);
        button.setEnabled(enable);
    }

    /**
     * Start activity to create new user account
     *
     * @param activity require for start
     */
    public static void startForResult(Activity activity, int requestCode) {

        Intent intent = getIntent(activity);

        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * Get valid starting Intent
     *
     * @param context required for Intent
     * @return Intent object
     */
    public static Intent getIntent(Context context) {
        return new Intent(context, AuthenticationActivity.class);
    }

    /**
     * Get simple result from resulting intent
     *
     * @param intent given Intent to get
     * @return Container
     */
    public static Result getIntentResult(Intent intent) {
        return new Result(intent);
    }

    /**
     * Watching user / login / phone number EditText
     */
    private class UserTextWatcher extends SimpleTextWatcher {

        @Override
        public void afterTextChanged(Editable editable) {

            final boolean validLength = editable.length() >= 9;

            editTextPassword.setEnabled(validLength);
        }
    }

    /**
     * Watching password EditText
     */
    private class PasswordTextWatcher extends SimpleTextWatcher {

        @Override
        public void afterTextChanged(Editable editable) {

            button.setEnabled(!TextUtils.isEmpty(editable));
        }
    }

    /**
     * Listen for login response
     */
    private class AuthenticationRequestListener extends RequestListener<Boolean> {

        @Override
        public void onFailure(SpiceException spiceException) {

            // TODO support different error types

            Messenger.INSTANCE.message("Błędny login i/lub hasło, spróbuj ponownie");

            // Give user another chance
            setFormEnable(true);
        }

        @Override
        public void onSuccess(Boolean result) {

            Intent intent = new Intent();

            intent.putExtra(EXTRA_USER, String.valueOf(editTextUser.getText().toString()));
            intent.putExtra(EXTRA_PASSWORD, String.valueOf(editTextUser.getText()));

            setResult(RESULT_OK, intent);
            finish();
        }
    }

    /**
     * Resulting authentication data, confirmed by server
     */
    public static class Result {

        private final String user;
        private final String password;

        private Result(Intent intent) {
            user = intent.getStringExtra(EXTRA_USER);
            password = intent.getStringExtra(EXTRA_PASSWORD);
        }

        public String getUser() {
            return user;
        }

        public String getPassword() {
            return password;
        }

        @Override
        public String toString() {
            return "Result{user='" + user + '\'' + '}';
        }
    }
}

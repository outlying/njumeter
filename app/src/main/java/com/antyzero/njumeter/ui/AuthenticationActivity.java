package com.antyzero.njumeter.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.antyzero.njumeter.R;
import com.antyzero.njumeter.tools.SimpleTextWatcher;

/**
 * Provides authentication form
 */
public class AuthenticationActivity extends BaseActivity {

    Button button;
    EditText editTextUser;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        button = findView(R.id.button);

        editTextUser = findView(R.id.editTextUser);
        editTextUser.addTextChangedListener(new UserTextWatcher());

        editTextPassword = findView(R.id.editTextPassword);
        editTextPassword.addTextChangedListener(new PasswordTextWatcher());
    }

    /**
     * Start activity to create new user account
     *
     * @param activity
     */
    public static void start(Activity activity, int requestCode) {

        Intent intent = new Intent(activity, AuthenticationActivity.class);

        activity.startActivityForResult(intent, requestCode);
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
}

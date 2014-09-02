package com.antyzero.njumeter.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.antyzero.njumeter.R;

/**
 * Provides authentication form
 */
public class AuthenticationActivity extends BaseActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        button = findView(R.id.button);
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
}

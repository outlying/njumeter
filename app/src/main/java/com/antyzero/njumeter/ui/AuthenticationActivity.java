package com.antyzero.njumeter.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Provides authentication form
 */
public class AuthenticationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

package com.antyzero.njumeter.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Provides authentication form
 */
public class AuthenticationActivity extends Activity {

    public static final int REQUEST_CODE = 31819;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Start activity to create new user account
     *
     * @param activity
     */
    public static void start(Activity activity){

        Intent intent = new Intent(activity, AuthenticationActivity.class);

        activity.startActivityForResult(intent, REQUEST_CODE, Bundle.EMPTY);
    }
}

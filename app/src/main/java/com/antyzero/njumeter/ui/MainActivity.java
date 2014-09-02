package com.antyzero.njumeter.ui;

import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;

import com.antyzero.njumeter.BuildConfig;
import com.antyzero.njumeter.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if we have at least one account, if not redirect user to login screen
        if (AccountManager.get(this).getAccountsByType(BuildConfig.ACCOUNT_TYPE).length == 0) {
            AuthenticationActivity.start(this);
        }

        setContentView(R.layout.activity_main);
    }
}

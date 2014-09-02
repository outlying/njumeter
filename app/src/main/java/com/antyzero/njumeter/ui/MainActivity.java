package com.antyzero.njumeter.ui;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.antyzero.njumeter.BuildConfig;
import com.antyzero.njumeter.R;

public class MainActivity extends BaseActivity {

    private static final int REQUEST_CODE_ACCOUNT_CREATE = 30471;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if we have at least one account, if not redirect user to login screen
        if (AccountManager.get(this).getAccountsByType(BuildConfig.ACCOUNT_TYPE).length == 0) {
            AuthenticationActivity.start(this, REQUEST_CODE_ACCOUNT_CREATE);
        }

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_ACCOUNT_CREATE) {

            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Konto nie utworzone", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

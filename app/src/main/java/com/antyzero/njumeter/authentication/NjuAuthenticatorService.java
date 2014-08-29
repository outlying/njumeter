package com.antyzero.njumeter.authentication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 *
 */
public class NjuAuthenticatorService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        NjuAccountAuthenticator authenticator = new NjuAccountAuthenticator(this);
        return authenticator.getIBinder();
    }
}

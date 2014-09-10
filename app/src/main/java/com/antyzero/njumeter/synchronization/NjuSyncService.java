package com.antyzero.njumeter.synchronization;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * ...
 */
public class NjuSyncService extends Service {

    private static final Object syncAdapterLock = new Object();
    private static NjuSyncAdapter njuSyncAdapter = null;

    @Override
    public void onCreate() {

        synchronized (syncAdapterLock) {

            if (njuSyncAdapter == null)
                njuSyncAdapter = new NjuSyncAdapter(getApplicationContext(), true);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return njuSyncAdapter.getSyncAdapterBinder();
    }
}

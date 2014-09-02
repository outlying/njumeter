package com.antyzero.njumeter.ui;

import android.app.Activity;

import com.antyzero.njumeter.network.SpiceService;
import com.octo.android.robospice.SpiceManager;

/**
 * Created by tornax on 02.09.14.
 */
class BaseActivity extends Activity {

    private SpiceManager spiceManager = new SpiceManager(SpiceService.class);

    protected SpiceManager getSpiceManager() {
        return spiceManager;
    }
}

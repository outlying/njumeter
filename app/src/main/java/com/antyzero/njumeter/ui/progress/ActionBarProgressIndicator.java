package com.antyzero.njumeter.ui.progress;

import android.app.Activity;
import android.view.Window;

/**
 * Uses horizontal progress bar displayed under ActionBar
 */
public final class ActionBarProgressIndicator implements ProgressIndicator {

    private final Activity activity;

    public ActionBarProgressIndicator(Activity activity) {
        this.activity = activity;
        // TODO Consider exception on false
        activity.requestWindowFeature(Window.FEATURE_PROGRESS);
    }

    @Override
    public void showProgress() {
        activity.setProgressBarIndeterminate(true);
    }

    @Override
    public void hideProgress() {
        activity.setProgressBarIndeterminate(false);
    }
}

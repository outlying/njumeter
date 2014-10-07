package com.antyzero.njumeter.ui.progress;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.antyzero.njumeter.R;

import fr.castorflex.android.smoothprogressbar.SmoothProgressDrawable;

/**
 * Uses horizontal progress bar displayed under ActionBar
 */
public final class ActionBarProgressIndicator implements ProgressIndicator {

    private final Activity activity;

    private ProgressBar progressBar;

    private static final FrameLayout.LayoutParams LAYOUT_PARAMS;

    /**
     *
     */
    static {

        LAYOUT_PARAMS = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                300 ); // FrameLayout.LayoutParams.WRAP_CONTENT

        LAYOUT_PARAMS.gravity = Gravity.TOP;
    }

    private final SmoothProgressDrawable indeterminateDrawable;

    /**
     *
     *
     * @param activity
     */
    public ActionBarProgressIndicator( Activity activity ) {
        this.activity = activity;

        indeterminateDrawable = new SmoothProgressDrawable.Builder(activity)
                .colors(new int[]{Color.BLACK, Color.GREEN, Color.RED})
                .build();
    }

    @Override
    public void showProgress() {

        if(progressBar == null) {
            progressBar = (ProgressBar) activity.findViewById(R.id.progressBar);
            progressBar.setIndeterminateDrawable(indeterminateDrawable);
        }

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}

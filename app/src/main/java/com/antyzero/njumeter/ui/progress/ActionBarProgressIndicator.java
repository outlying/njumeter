package com.antyzero.njumeter.ui.progress;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import fr.castorflex.android.smoothprogressbar.SmoothProgressDrawable;

/**
 * Uses horizontal progress bar displayed under ActionBar
 */
public final class ActionBarProgressIndicator implements ProgressIndicator {

    private final Activity activity;

    private final ProgressBar progressBar;

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

    private final FrameLayout content;
    private final SmoothProgressDrawable indeterminateDrawable;

    /**
     *
     *
     * @param activity
     */
    public ActionBarProgressIndicator( Activity activity ) {
        this.activity = activity;

        activity.requestWindowFeature(Window.FEATURE_PROGRESS);

        progressBar = new ProgressBar( activity );
        progressBar.setBackgroundColor(Color.CYAN);

        indeterminateDrawable = new SmoothProgressDrawable.Builder(activity)
                .colors(new int[]{Color.BLACK, Color.GREEN, Color.RED})
                .build();

        progressBar.setIndeterminateDrawable(indeterminateDrawable);

        content = (FrameLayout) activity.findViewById(android.R.id.content);
        content.addView(progressBar, LAYOUT_PARAMS);
    }

    @Override
    public void showProgress() {

        content.bringChildToFront(progressBar);

        // On lower versions this is required by system after bringChildToFront()
        if( Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT ){
            content.requestLayout();
            content.invalidate();
        }

        indeterminateDrawable.start();
    }

    @Override
    public void hideProgress() {

    }
}

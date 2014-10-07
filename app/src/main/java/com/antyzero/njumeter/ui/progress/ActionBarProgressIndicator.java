package com.antyzero.njumeter.ui.progress;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import fr.castorflex.android.smoothprogressbar.SmoothProgressDrawable;

/**
 * Uses horizontal progress bar displayed under ActionBar
 */
public final class ActionBarProgressIndicator implements ProgressIndicator {

    private final ProgressBar progressBar;

    private static final FrameLayout.LayoutParams LAYOUT_PARAMS;

    /**
     *
     */
    static {

        LAYOUT_PARAMS = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);

        LAYOUT_PARAMS.gravity = Gravity.TOP;
    }

    private final FrameLayout content;

    /**
     *
     *
     * @param activity
     */
    public ActionBarProgressIndicator( Activity activity ) {

        SmoothProgressDrawable indeterminateDrawable = new SmoothProgressDrawable.Builder(activity)
                .build();

        progressBar = new ProgressBar(activity);
        progressBar.setBackgroundColor(Color.CYAN);
        progressBar.setIndeterminate(true);
        progressBar.setIndeterminateDrawable(indeterminateDrawable);

        content = (FrameLayout) activity.findViewById(android.R.id.content);
    }

    @Override
    public void showProgress() {

        if( progressBar.getParent() == null ) {
            content.addView(progressBar, LAYOUT_PARAMS);
        }

        content.bringChildToFront(progressBar);

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT){
            content.requestLayout();
            content.invalidate();
        }

        progressBar.setVisibility( View.VISIBLE );
    }

    @Override
    public void hideProgress() {

    }
}

package com.antyzero.njumeter.ui.progress;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
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
                300); // FrameLayout.LayoutParams.WRAP_CONTENT

        LAYOUT_PARAMS.gravity = Gravity.TOP;
    }

    /**
     *
     *
     * @param activity
     */
    public ActionBarProgressIndicator( Activity activity ) {

        SmoothProgressDrawable indeterminateDrawable = new SmoothProgressDrawable.Builder(activity)
                .color(0xff0000)
                .interpolator(new AccelerateInterpolator())
                .sectionsCount(4)
                .separatorLength(8)         //You should use Resources#getDimensionPixelSize
                .strokeWidth(8f)            //You should use Resources#getDimension
                .speed(2f)                 //2 times faster
                .progressiveStartSpeed(2)
                .reversed(false)
                .mirrorMode(false)
                .progressiveStart(true)
                .build();

        progressBar = new ProgressBar(activity);
        progressBar.setIndeterminate(true);
        progressBar.setIndeterminateDrawable(indeterminateDrawable);

        final FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        content.addView(progressBar, LAYOUT_PARAMS);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}

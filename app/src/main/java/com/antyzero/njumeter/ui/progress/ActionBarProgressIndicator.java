package com.antyzero.njumeter.ui.progress;

import android.app.Activity;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.GridLayoutAnimationController;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.antyzero.njumeter.R;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
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

        Resources resources = activity.getResources();

        final float progressBarSize = resources.getDimension(R.dimen.progress_bar_height);

        SmoothProgressDrawable.Builder builder = new SmoothProgressDrawable.Builder(activity)
                .strokeWidth(progressBarSize);

        LAYOUT_PARAMS.height = (int) progressBarSize;

        progressBar = new SmoothProgressBar(activity);
        progressBar.setIndeterminate(true);
        progressBar.setIndeterminateDrawable(builder.build());
        progressBar.setPadding(0, 0, 0, 0);
        progressBar.setVisibility(View.INVISIBLE);

        content = (FrameLayout) activity.findViewById(android.R.id.content);
    }

    @Override
    public void showProgress() {

        // Make sure that ProgressBar isn't already included
        if( progressBar.getParent() == null ) {
            content.addView(progressBar, LAYOUT_PARAMS);
        }

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}

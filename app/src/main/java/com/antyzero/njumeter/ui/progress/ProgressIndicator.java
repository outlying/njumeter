package com.antyzero.njumeter.ui.progress;

/**
 * Provides UI for indicating background operation progress
 */
public interface ProgressIndicator {

    /**
     * Display loading indicator on screen
     */
    public void showProgress();

    /**
     * Hide loading indicator
     */
    public void hideProgress();
}

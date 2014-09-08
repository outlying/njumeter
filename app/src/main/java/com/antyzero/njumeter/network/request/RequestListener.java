package com.antyzero.njumeter.network.request;

import com.octo.android.robospice.persistence.exception.SpiceException;

/**
 * Implemented listener, provides extra methods
 */
public abstract class RequestListener<T> implements com.octo.android.robospice.request.listener.RequestListener<T> {

    @Override
    public final void onRequestFailure( SpiceException spiceException ) {
        onFailure(spiceException);
        postRequest();
    }

    @Override
    public final void onRequestSuccess( T t ) {
        onSuccess(t);
    }

    public abstract void onFailure( SpiceException spiceException );

    public abstract void onSuccess( T t );

    /**
     * Override if some kind of action is needed after request is done regarding it success or failure
     */
    public void postRequest() {

    }

}

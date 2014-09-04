package com.antyzero.njumeter.network.request;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

/**
 *
 */
abstract class BaseRequest<T> extends SpringAndroidSpiceRequest<T> {

    public BaseRequest(Class<T> clazz) {
        super(clazz);
    }
}

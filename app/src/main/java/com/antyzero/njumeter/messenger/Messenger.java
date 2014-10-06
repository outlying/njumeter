package com.antyzero.njumeter.messenger;

import android.app.Activity;

/**
 * Interface for receiving and sending UI messages
 */
public interface Messenger {

    void message(Message message);

    void register(Activity subscriber);

    void unregister(Activity subscriber);

    void process(Activity activity, Message message);
}

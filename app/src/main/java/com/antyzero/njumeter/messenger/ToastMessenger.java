package com.antyzero.njumeter.messenger;

import android.app.Activity;
import android.widget.Toast;

import de.greenrobot.event.EventBus;

/**
 * Implantation of messenger that allows to send toasts
 */
public class ToastMessenger implements Messenger {

    private final EventBus eventBus = new EventBus();

    @Override
    public void message(Message message) {
        eventBus.postSticky(message);
    }

    @Override
    public void register(Activity subscriber) {
        eventBus.registerSticky(subscriber);
    }

    @Override
    public void unregister(Activity subscriber) {
        eventBus.unregister(subscriber);
    }

    @Override
    public void process(Activity activity, Message message) {
        Toast.makeText(activity, message.getText(), Toast.LENGTH_SHORT).show();
        eventBus.removeStickyEvent(message);
    }
}

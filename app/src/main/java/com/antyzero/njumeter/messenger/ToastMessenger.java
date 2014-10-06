package com.antyzero.njumeter.messenger;

import android.app.Activity;
import android.widget.Toast;

/**
 * Implantation of messenger that allows to send toasts
 */
public class ToastMessenger implements Messenger {

    private Activity context;

    @Override
    public void message(Message message) {
        Toast.makeText(context, message.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void register(Activity subscriber) {
        this.context = subscriber;
    }

    @Override
    public void unregister(Activity subscriber) {
        this.context = null;
    }

    @Override
    public void process(Activity activity, Message message) {
        // do nothing
    }
}

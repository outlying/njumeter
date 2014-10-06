package com.antyzero.njumeter.messenger;

import android.app.Activity;

import de.greenrobot.event.EventBus;
import de.keyboardsurfer.android.widget.crouton.Crouton;

/**
 *
 */
public final class CroutonMessenger implements Messenger {

    private final EventBus eventBus = new EventBus();

    /**
     * Send fully customized message
     *
     * @param message to display
     */
    @Override
    public void message(final Message message) {
        eventBus.postSticky( message );
    }

    /**
     * Way to register message receiving support
     *
     * @param subscriber Activity for displaying messages
     */
    @Override
    public void register(Activity subscriber) {
        eventBus.registerSticky( subscriber );
    }

    /**
     * Unregister UI
     *
     * @param subscriber Activity for displaying messages
     */
    @Override
    public void unregister(Activity subscriber) {
        eventBus.unregister( subscriber );
    }

    /**
     * Process incoming message
     *
     * @param activity that will display this message
     * @param message to display
     */
    @Override
    public void process(final Activity activity, final Message message) {

        Crouton.showText(activity, message.getText(), message.getStyle().croutonStyle);
        eventBus.removeStickyEvent(message);
    }
}

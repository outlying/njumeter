package com.antyzero.njumeter.messenger;

import android.app.Activity;
import android.widget.Toast;

import de.greenrobot.event.EventBus;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 *
 */
public enum Messenger {

    INSTANCE;

    private final EventBus eventBus = EventBus.getDefault();

    /**
     * Simple way to provide test information to user
     *
     * @param text of message
     */
    public void message( final String text ) {
        eventBus.post( Message.create( text ) );
    }

    /**
     * Way to register message receiving support
     *
     * @param subscriber Activity for displaying messages
     */
    public void register( Activity subscriber ) {
        eventBus.register( subscriber );
    }

    /**
     * Unregister UI
     *
     * @param subscriber Activity for displaying messages
     */
    public void unregister( Activity subscriber ) {
        eventBus.unregister( subscriber );
    }

    /**
     * Process incoming message
     *
     * @param activity that will display this message
     * @param message to display
     */
    public void process( final Activity activity, final Message message ) {
        Crouton.makeText( activity, message.getText(), Style.INFO ).show();
        // Redundant to see if Crouton is working
        Toast.makeText(activity, "Crouton above ? " + message.getText(), Toast.LENGTH_SHORT).show();
    }
}

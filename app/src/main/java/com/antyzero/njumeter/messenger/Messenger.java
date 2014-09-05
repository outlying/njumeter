package com.antyzero.njumeter.messenger;

import android.app.Activity;

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
     * @param text
     */
    public void message( String text ) {
        eventBus.post( Message.create( text ) );
    }

    /**
     * Way to register message reciving support
     *
     * @param subscriber
     */
    public void register( Activity subscriber ) {
        eventBus.register( subscriber );
    }

    /**
     * Unregister UI
     *
     * @param subscriber
     */
    public void unregister( Activity subscriber ) {
        eventBus.unregister( subscriber );
    }

    /**
     * ...
     *
     * @param activity
     * @param message
     */
    public void process( Activity activity, Message message ) {
        Crouton.makeText( activity, message.getText(), Style.INFO ).show();
    }

    /**
     * @return
     */
    public EventBus getEventBus() {
        return eventBus;
    }

}

package com.antyzero.njumeter.messenger;

import de.greenrobot.event.EventBus;

/**
 *
 */
public enum Messenger {

    INSTANCE;

    private final EventBus eventBus = EventBus.getDefault();

    /**
     *
     *
     * @param text
     */
    public void message(String text){
        eventBus.post( Message.create( text ) );
    }

    /**
     *
     *
     * @return
     */
    public EventBus getEventBus() {
        return eventBus;
    }
}

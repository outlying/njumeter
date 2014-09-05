package com.antyzero.njumeter.messenger;

/**
 * Created by tornax on 05.09.14.
 */
public class Message {

    private String message;
    private Type type;

    /**
     *
     */
    private Message(){

    }

    /**
     *
     *
     * @param message
     * @return
     */
    public static Message create(String message){
        return create( message, Type.DEFAULT );
    }

    /**
     *
     *
     * @param messageText
     * @param type
     * @return
     */
    public static Message create(String messageText, Type type){

        Message messageEvent = new Message();

        messageEvent.message = messageText;
        messageEvent.type = type;

        return messageEvent;
    }

    /**
     *
     */
    public enum Type {
        DEFAULT
    }
}

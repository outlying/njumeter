package com.antyzero.njumeter.messenger;

/**
 * Created by tornax on 05.09.14.
 */
public class Message {

    private CharSequence text;
    private Type type;

    /**
     *
     */
    private Message(){
    }

    public CharSequence getText() {
        return text;
    }

    public Type getType() {
        return type;
    }

    /**
     *
     *
     * @param text
     * @return
     */
    public static Message create(CharSequence text){
        return create( text, Type.DEFAULT );
    }

    /**
     *
     *
     * @param text
     * @param type
     * @return
     */
    public static Message create(CharSequence text, Type type){

        Message message = new Message();

        message.text = text;
        message.type = type;

        return message;
    }

    /**
     *
     */
    public enum Type {
        DEFAULT
    }
}

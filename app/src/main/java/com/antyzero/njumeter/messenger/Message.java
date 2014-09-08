package com.antyzero.njumeter.messenger;

/**
 * Created by tornax on 05.09.14.
 */
public class Message {

    private CharSequence text;
    private Style style;

    /**
     *
     */
    private Message() {
    }

    public CharSequence getText() {
        return text;
    }

    public Style getStyle() {
        return style;
    }

    /**
     * @param text
     * @return
     */
    public static Message create( CharSequence text ) {
        return create( text, Style.DEFAULT );
    }

    /**
     * @param text
     * @param style
     * @return
     */
    public static Message create( CharSequence text, Style style ) {

        Message message = new Message();

        message.text = text;
        message.style = style;

        return message;
    }

    /**
     *
     */
    public enum Style {
        DEFAULT
    }
}

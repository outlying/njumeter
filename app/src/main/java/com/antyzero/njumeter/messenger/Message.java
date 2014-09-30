package com.antyzero.njumeter.messenger;

/**
 * ...
 */
public class Message {

    private CharSequence text;
    private Style style;

    /**
     *
     */
    private Message() {
    }

    /**
     * ...
     *
     * @param builder for construction
     */
    private Message(Builder builder) {

    }

    public CharSequence getText() {
        return text;
    }

    /**
     *
     *
     * @return
     */
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

    /**
     *
     */
    public final static class Builder {

        private final String message;

        /**
         * Default private constructor
         *
         * @param message mandatory
         */
        private Builder(String message){
            this.message = message;
        }

        public Message build(){
            return new Message(this);
        }
    }

}

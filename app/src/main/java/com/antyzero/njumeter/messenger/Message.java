package com.antyzero.njumeter.messenger;

/**
 * ...
 */
public class Message {

    private final CharSequence text;
    private final Style style;

    /**
     * ...
     *
     * @param builder for construction
     */
    private Message(Builder builder) {

        this.text = builder.message;
        this.style = builder.style;
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
     *
     *
     * @param text
     * @param style
     * @return
     */
    public static Message create( CharSequence text, Style style ) {

        return prepare()
                .setMessage(text)
                .setStyle(style)
                .build();
    }

    /**
     * Message builder
     *
     * @return builder object
     */
    public static Builder prepare(){
        return new Builder();
    }

    /**
     * Predefined styles
     */
    public enum Style {
        DEFAULT,
        ERROR
    }

    /**
     *
     */
    public final static class Builder {

        private CharSequence message;

        private Style style = Style.DEFAULT;

        /**
         * Default private constructor
         */
        private Builder(){

        }

        /**
         *
         *
         * @param message
         * @return
         */
        public Builder setMessage(CharSequence message){
            this.message = message;
            return this;
        }

        /**
         * Set message style
         *
         * @param style
         * @return
         */
        public Builder setStyle(Style style){
            this.style = style;
            return this;
        }

        /**
         * Creates message
         *
         * @return Message object
         */
        public Message build(){
            return new Message(this);
        }
    }

}

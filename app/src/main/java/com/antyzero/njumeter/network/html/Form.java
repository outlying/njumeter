package com.antyzero.njumeter.network.html;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class Form {

    private static final String REGEXR_FORM = "<form(?=(?:.*?id=\"(.+?)\"))(?=(?:.*?action=\"(.+?)\")?)(?=(?:" +
            ".*?method=\"(.+?)\")?).*?>(.*?)</form>";

    private static final Pattern PATTERN_FORM = Pattern.compile( REGEXR_FORM );

    private final Inputs inputs;

    private String id;
    private String action;

    private Method method;

    /**
     *
     *
     * @param builder Form.Builder object
     */
    private Form( Builder builder ) {

        Matcher matcher = PATTERN_FORM.matcher( builder.html );

        if( !matcher.find() ) {
            throw new IllegalArgumentException( "No matching <form /> inside given string" );
        }

        id = matcher.group( 1 );
        action = matcher.group( 2 );
        method = Method.valueOf( matcher.group( 3 ).toUpperCase( Locale.getDefault() ) );

        inputs = Inputs.from( matcher.group( 4 ) );
    }

    /**
     * @return
     */
    public String getAction() {
        return action;
    }

    /**
     * ...
     *
     * @return list of Input fields
     */
    public Inputs getInputs() {
        return inputs;
    }

    /**
     * @param html
     * @return
     */
    public static Builder from( String html ) {
        return new Builder( html );
    }

    /**
     *
     */
    public enum Method {
        GET, POST
    }

    /**
     *
     */
    public static final class Builder {

        private String html;
        private String formId;

        /**
         * @param html of website
         */
        public Builder( String html ) {
            this.html = html;
        }

        /**
         * Adds search criteria
         *
         * @param formId only form with given Id
         * @return Builder object
         */
        public Builder withId( String formId ) {
            this.formId = formId;
            return this;
        }

        /**
         * Building
         *
         * @return new Form object
         */
        public Form build() {
            return new Form( this );
        }
    }
}

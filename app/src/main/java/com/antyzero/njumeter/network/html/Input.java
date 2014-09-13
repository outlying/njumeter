package com.antyzero.njumeter.network.html;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tornax on 13.09.14.
 */
public class Input {

    private final static String REGEXR_INPUT = "<input.*?>";
    private final static String REGEXR_FIELDS = "(?=(?:.*?name=\"(.*?)\"))(?=(?:.*?value=\"(.*?)\"))(?=(?:.*?type=\"(.*?)\"))";

    private final static Pattern PATTERN_INPUT = Pattern.compile( REGEXR_INPUT );
    private final static Pattern PATTERN_FIELDS = Pattern.compile( REGEXR_FIELDS );

    private String name;
    private String value;
    private String type;

    /**
     *
     *
     * @param htmlInput
     */
    private Input( String htmlInput ) {

        Matcher matcher = PATTERN_FIELDS.matcher( htmlInput );

       if(matcher.find()){
           name = matcher.group( 1 );
           value = matcher.group( 2 );
           type = matcher.group( 3 );
       }
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    /**
     *
     *
     * @param html
     * @return
     */
    public static List<Input> from( String html ) {

        List<Input> inputList = new ArrayList<Input>();

        Matcher matcher = PATTERN_INPUT.matcher( html );

        while( matcher.find() ){
            inputList.add( new Input( matcher.group( 0 )) );
        }

        return inputList;
    }

    @Override
    public String toString() {
        return "Input{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

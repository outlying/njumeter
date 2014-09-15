package com.antyzero.njumeter.network.html;

import com.antyzero.njumeter.tools.ForwardingList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tornax on 14.09.14.
 */
public class Inputs extends ForwardingList<Input> {

    private final static String REGEXR_INPUT = "<input.*?>";

    private final static Pattern PATTERN_INPUT = Pattern.compile( REGEXR_INPUT );

    /**
     *
     */
    public Inputs() {
        super( new ArrayList<Input>() );
    }

    /**
     *
     *
     * @param inputName
     * @return
     */
    public Input findByName( String inputName ){

        Input result = null;

        for( Input input : this){

            String name = input.getName();

            if( name != null && name.equalsIgnoreCase( inputName )){
                result = input;
                break;
            }
        }

        return result;
    }

    /**
     *
     *
     * @param html
     * @return
     */
    public static Inputs from( String html ) {

        Inputs inputs = new Inputs();

        Matcher matcher = PATTERN_INPUT.matcher( html );

        while( matcher.find() ){
            inputs.add( new Input( matcher.group( 0 )) );
        }

        return inputs;
    }

    /**
     * ...
     *
     * @return Map representing fields for POST request
     */
    public Map<String,String> toMap(){

        Map<String,String> result = new LinkedHashMap<>();

        for(Input input : this){
            result.put( input.getName(), input.getValue() );
        }

        return result;
    }
}

package com.antyzero.njumeter.network.html;

import com.antyzero.njumeter.tools.ForwardingList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by tornax on 14.09.14.
 */
public class Inputs extends ForwardingList<Input> {

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
    public static List<Input> from( String html ) {

        Inputs inputs = new Inputs();

        Matcher matcher = PATTERN_INPUT.matcher( html );

        while( matcher.find() ){
            inputs.add( new Input( matcher.group( 0 )) );
        }

        return inputs;
    }

    /**
     *
     *
     * @return
     */
    public Map<String,String> toMap(){

        Map<String,String> result = new LinkedHashMap<>();

        return result;
    }
}

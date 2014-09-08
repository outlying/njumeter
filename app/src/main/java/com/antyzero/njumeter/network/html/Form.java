package com.antyzero.njumeter.network.html;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tornax on 04.09.14.
 */
public class Form {

    private String id;
    private String action;
    private Method method;

    private Form(String formHtmlContent) {

        // TODO process content
    }

    /**
     *
     *
     * @param html
     * @param formId
     * @return
     */
    public static Form fromWithId(String html, String formId){

        Form form = null;

        String stringPattern = String.format(
                "(<form.?id=\"%s?\".*?>(.+?)</form>)", Pattern.quote(formId));

        Pattern pattern = Pattern.compile(stringPattern);

        Matcher matcher = pattern.matcher(html);

        if(matcher.find()){



            form = new Form("");
        }

        return form;
    }

    public enum Method {

        GET, POST


    }
}

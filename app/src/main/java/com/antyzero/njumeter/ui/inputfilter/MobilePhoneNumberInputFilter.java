package com.antyzero.njumeter.ui.inputfilter;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * InputFilter based on information contained in
 *
 * http://pl.wikipedia.org/wiki/Numery_telefoniczne_w_Polsce#Sieci_ruchome_.28kom.C3.B3rkowe.29
 *
 * Makes sure that user won't be able to provide invalid phone number
 */
public class MobilePhoneNumberInputFilter implements InputFilter {

    private static final String[] PHONE_PREFIX = {
            "50", "51", "53", "57", "60", "66", "69", "72", "73", "78", "79", "88"};

    private static final int PREFIX_LENGTH = 2;

    private final InputListener inputListener;

    /**
     * Base constructor
     */
    public MobilePhoneNumberInputFilter() {
        this(null);
    }

    /**
     * Constructor that allows us to provide extra listener
     *
     * @param inputListener for input results
     */
    public MobilePhoneNumberInputFilter(InputListener inputListener) {
        this.inputListener = inputListener;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        CharSequence result = null;

        // Intercept only when user is adding characters
        // TODO it is still a little buggy when we are editing in the middle
        if(dstart == dend && dstart < PREFIX_LENGTH) {

            boolean invalid = true;

            for (String prefix : PHONE_PREFIX) {

                String future = dest.toString() + source.toString();

                if(future.length() > PREFIX_LENGTH){
                    future = future.substring(0, PREFIX_LENGTH);
                }

                if(prefix.startsWith(future)) {
                    invalid = false;
                    break;
                }
            }

            if(invalid){
                result = "";
            }
        }

        if(inputListener != null){
            inputListener.inputCorrect(result == null);
        }

        return result;
    }

    /**
     * Provides feedback from this InputFilter
     */
    public static interface InputListener {

        /**
         * Called directly before InputFilter#filter return
         *
         * @param correct
         */
        public void inputCorrect(boolean correct);
    }
}

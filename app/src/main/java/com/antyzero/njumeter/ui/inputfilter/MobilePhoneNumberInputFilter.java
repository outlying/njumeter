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

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        return null;
    }
}

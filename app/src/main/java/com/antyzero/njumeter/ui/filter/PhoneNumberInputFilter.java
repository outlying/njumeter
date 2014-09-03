package com.antyzero.njumeter.ui.filter;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Allow only phone number characters
 */
public class PhoneNumberInputFilter implements InputFilter {

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        return null;
    }
}

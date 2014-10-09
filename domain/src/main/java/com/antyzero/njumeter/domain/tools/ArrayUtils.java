package com.antyzero.njumeter.domain.tools;

import java.lang.reflect.Array;

/**
 * Simple array utils
 */
public class ArrayUtils {

    private ArrayUtils(){
        throw new IllegalAccessError();
    }

    /**
     * Join two object arrays of same type
     *
     * @param array1 first array
     * @param array2 second array
     * @param <T> type
     * @return joined array
     */
    public static <T> T[] join(T[] array1, T[] array2){

        int aLen = array1.length;
        int bLen = array2.length;

        @SuppressWarnings("unchecked")
        T[] C = (T[]) Array.newInstance(array1.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(array1, 0, C, 0, aLen);
        System.arraycopy(array2, 0, C, aLen, bLen);

        return C;
    }
}

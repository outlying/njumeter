package com.antyzero.njumeter.log;

import android.text.TextUtils;
import android.util.Log;

import com.crashlytics.android.Crashlytics;

/**
 * ...
 */
public class LOG {

    private String tag;
    private String message;

    private Throwable throwable;

    private int level = Log.INFO;

    private boolean crashlytics = false;

    /**
     * Empty constructor is not allowed
     */
    private LOG() {
        throw new IllegalStateException( "Disallowed" );
    }

    /**
     * Proper construction
     *
     * @param tag identifies log
     */
    public LOG( String tag ) {
        this.tag = tag;
    }

    /**
     * Changes log level. Check {@link android.util.Log} for supported levels.
     *
     * @param logLevel new value
     * @return LOG object
     */
    public LOG level(int logLevel){

        if( logLevel < Log.VERBOSE || logLevel > Log.ASSERT){
            throw new IndexOutOfBoundsException( "This log value is not allowed" );
        }

        this.level = logLevel;
        return this;
    }

    /**
     * Enables parallel log to Crashlytics
     *
     * @return LOG object
     */
    public LOG crashlytics() {
        crashlytics = true;
        return this;
    }

    /**
     * Send log
     */
    public void commit() {

        if( message == null && throwable == null ) {
            throw new IllegalStateException( "Either log message or throwable has to be != null" );
        }

        /**
         * Log to Crashlytics
         */
        if( crashlytics ) {

            if( throwable != null ) {
                Crashlytics.logException( throwable );
            }

            if( !TextUtils.isEmpty( message ) ) {
                Crashlytics.log( level, tag, message );
            }
        }

        /**
         * Pass log to Android
         */
        switch( level ){

            case Log.VERBOSE:
                Log.v( tag, message, throwable );
                break;

            case Log.DEBUG:
                Log.d( tag, message, throwable );
                break;

            case Log.INFO:
                Log.i( tag, message, throwable );
                break;

            case Log.WARN:
                Log.w( tag, message, throwable );
                break;

            case Log.ERROR:
                Log.e( tag, message, throwable );
                break;

            case Log.ASSERT:
                Log.wtf( tag, message, throwable );
                break;

            default:
                throw new IllegalStateException( String.format( "Log level %s is not supported", level ) );
        }

    }

    /**
     * Creates new logging object
     *
     * @param tag required
     * @return LOG object
     */
    public static LOG start( String tag ) {
        return new LOG( tag );
    }
}

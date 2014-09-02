package com.antyzero.njumeter.authentication;

import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.os.Bundle;
import android.test.ApplicationTestCase;

import com.antyzero.njumeter.NjuApplication;

import static org.assertj.core.api.Assertions.assertThat;


/**
 *
 */
public class NjuAccountAuthenticatorTest extends ApplicationTestCase<NjuApplication> {

    private NjuAccountAuthenticator authenticator;

    public NjuAccountAuthenticatorTest() {
        super( NjuApplication.class );
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        createApplication();
        authenticator = new NjuAccountAuthenticator( getApplication() );
    }

    /**
     * Is implementation OK
     *
     * @throws NetworkErrorException
     */
    public void testAddAccountIsBundleNotNull() throws NetworkErrorException {
        Bundle bundle = authenticator.addAccount( null, null, null, null, null );
        assertThat( bundle ).isNotNull();
    }

    /**
     * Check if bundle is implemented in right way
     */
    public void testAddAccountIsBundleValid() throws NetworkErrorException {

        Bundle bundle = authenticator.addAccount( null, null, null, null, null );

        validateAuthBundle( bundle );
    }

    public void testGetAuthTokenIsValid() throws NetworkErrorException {
        Bundle bundle = authenticator.getAuthToken( null, null, null, null );

        validateAuthBundle( bundle );
    }

    private void validateAuthBundle( Bundle bundle ) {
        assertThat( bundle.containsKey( AccountManager.KEY_INTENT ) ||
                bundle.containsKey( AccountManager.KEY_ACCOUNT_NAME ) ||
                bundle.containsKey( AccountManager.KEY_ERROR_CODE ) ).isTrue();
    }
}

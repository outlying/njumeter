package com.antyzero.njumeter.ui;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

import static org.assertj.android.api.Assertions.assertThat;

public class AuthenticationActivityTest extends ActivityInstrumentationTestCase2<AuthenticationActivity> {

    public AuthenticationActivityTest() {
        super(AuthenticationActivity.class);
    }

    public void testCreation() throws Exception {
        assertThat(getActivity()).isNotNull();
    }

    /**
     * Testing proper enable / disable behaviour for button
     */
    @UiThreadTest
    public void testViewEnabled(){

        // Make sure state after creation is OK
        assertThat(getActivity().editTextUser).isEnabled();
        assertThat(getActivity().editTextPassword).isDisabled();
        assertThat(getActivity().button).isDisabled();

        // Partly provided phone number should not change enable state
        getActivity().editTextUser.setText("12345");

        assertThat(getActivity().editTextPassword).isDisabled();

        // Valid full length phone number should enable password input
        getActivity().editTextUser.setText("123456789");

        assertThat(getActivity().editTextPassword).isEnabled();

        // Non empty password should enable login button
        getActivity().editTextPassword.setText("password");

        assertThat(getActivity().button).isEnabled();


        // ------- Disable, go back ----------

        getActivity().editTextPassword.setText("");

        assertThat(getActivity().button).isDisabled();

        getActivity().editTextUser.setText("");

        assertThat(getActivity().editTextPassword).isDisabled();
    }
}
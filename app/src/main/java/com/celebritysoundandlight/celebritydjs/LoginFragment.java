package com.celebritysoundandlight.celebritydjs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by RyanFletcher on 9/16/2015.
 *
 * This fragment allows a DJ to login or create an account. The DJ logging in subscribes
 * the device to Parse PUSH services that deliver song requests from the customer(s).
 * This fragment is accessible by tapping the company logo in the ActionBar seven (7) times
 * in thirty (30) seconds if the DJ is not logged in.
 */
public class LoginFragment extends Fragment{

    private static final String TAG = "LoginFragment";

    // EditText that the DJ enters his or her username into
    private EditText usernameEditText;

    // EditText that the DJ enters his or her password into
    private EditText passwordEditText;

    // Button that logs the DJ into the service.
    private Button loginButton;

    // Button that creates an account for the DJ.
    private Button createButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        // Inflate the view.
        View v =  inflater.inflate(R.layout.fragment_login, container, false);

        // Find the GUI elements.
        usernameEditText = (EditText) v.findViewById(R.id.login_username);
        passwordEditText = (EditText) v.findViewById(R.id.login_password);
        loginButton = (Button) v.findViewById(R.id.login_login_button);
        createButton = (Button) v.findViewById(R.id.login_create_button);

        // Set onClickListeners for loginButton and createButton.
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAccount();
                showLogoutFragment();
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });

        return v;
    }

    // Logs the DJ into the service and sets a callback.
    private void loginAccount(){
        ParseUser.logInInBackground(usernameEditText.getText().toString(),
                passwordEditText.getText().toString(),
                new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        // User has successfully logged in, so display Toast
                        // and subscribe to ParsePush.
                        if (user != null) {
                            Toast.makeText(getContext(),
                                    "Logged in.",
                                    Toast.LENGTH_SHORT).show();
                            ParsePush.subscribeInBackground(user.getUsername());
                        }
                        // Error message has occurred, so display Toast.
                        else {
                            if (e.getCode() == ParseException.CONNECTION_FAILED) {
                                Toast.makeText(getContext(),
                                        "Connection Failed. Try again later",
                                        Toast.LENGTH_SHORT).show();
                            } else if (e.getCode() == ParseException.OBJECT_NOT_FOUND) {
                                Toast.makeText(getContext(),
                                        "Invalid username and/or password.",
                                        Toast.LENGTH_SHORT).show();
                            } else if (e.getCode() == ParseException.USERNAME_TAKEN) {
                                Toast.makeText(getContext(),
                                        "Username taken. Please try another.",
                                        Toast.LENGTH_SHORT).show();
                            } else if (e.getCode() == ParseException.USERNAME_MISSING) {
                                Toast.makeText(getContext(),
                                        "Please enter username.",
                                        Toast.LENGTH_SHORT).show();
                            } else if (e.getCode() == ParseException.PASSWORD_MISSING) {
                                Toast.makeText(getContext(),
                                        "Please enter a password.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    // Replaces the current fragment with LogoutFragment.
    private void showLogoutFragment(){
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new LogoutFragment())
                .addToBackStack(null)
                .commit();
    }

    // Create an account for the DJ and register a callback.
    private void createAccount(){
        ParseUser user = new ParseUser();
        user.setUsername(usernameEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                // Account created, so display Toast.
                if (e == null) {
                    Toast.makeText(getContext(),
                            "Account Created.",
                            Toast.LENGTH_SHORT).show();
                }
                // Error message has occurred, so display Toast.
                else {
                    if (e.getCode() == ParseException.CONNECTION_FAILED) {
                        Toast.makeText(getContext(),
                                "Connection Failed. Try again later",
                                Toast.LENGTH_SHORT).show();
                    }
                    else if (e.getCode() == ParseException.USERNAME_TAKEN) {
                        Toast.makeText(getContext(),
                                "Username Taken. Please try another.",
                                Toast.LENGTH_SHORT).show();
                    }
                    else if (e.getCode() == ParseException.USERNAME_MISSING){
                        Toast.makeText(getContext(),
                                "Please enter username.",
                                Toast.LENGTH_SHORT).show();
                    }
                    else if (e.getCode() == ParseException.PASSWORD_MISSING){
                        Toast.makeText(getContext(),
                                "Please enter a password.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}

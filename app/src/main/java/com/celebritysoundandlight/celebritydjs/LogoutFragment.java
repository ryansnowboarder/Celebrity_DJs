package com.celebritysoundandlight.celebritydjs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParsePush;
import com.parse.ParseUser;

/**
 * Created by RyanFletcher on 9/17/2015.
 *
 * This is a simple fragment with a button that allows the DJ to log out. This fragment
 * is accessible by tapping the company logo in the ActionBar seven (7) times in thirty (30)
 * seconds if the DJ is currently logged in.
 */
public class LogoutFragment extends Fragment {

    private static final String TAG = "LogoutFragment";

    // The Button used to log out the DJ.
    private Button mLogoutButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        // Inflate the view.
        View v = inflater.inflate(R.layout.fragment_logout, container, false);

        // Find the button.
        mLogoutButton = (Button) v.findViewById(R.id.logout_logout_button);

        // Set onClickListener on the button.
        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        return v;
    }

    // Logs the DJ out of the service.
    private void logout(){
        // Unsubscribe to ParsePush.
        ParsePush.unsubscribeInBackground(ParseUser.getCurrentUser().getUsername());
        // Logout ParseUser.
        ParseUser.logOut();
        // Display Toast.
        Toast.makeText(getContext(),
                "Logged Out.",
                Toast.LENGTH_SHORT).show();
        showLoginFragment();
    }

    // Replaces current fragment with LoginFragment.
    private void showLoginFragment(){
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment())
                .addToBackStack(null)
                .commit();
    }
}

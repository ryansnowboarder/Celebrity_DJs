package com.celebritysoundandlight.celebritydjs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by RyanFletcher on 9/15/2015.
 *
 * This is the first fragment that is displayed upon launch of the application.
 * The screen displays the company name and a subtitle as well as buttons, in the
 * form of TextViews, that direct the user to RequestFragment, AboutUsFragment, or
 * ContactFragment.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    // the TextView, utilized as a button, that directs the user to RequestFragment
    private TextView RequestTextView;

    // the TextView, utilized as a button, that directs the user to AboutUsFragment
    private TextView AboutUsTextView;

    // the TextView, utilized as a button, that directs the user to ContactFragment
    private TextView ContactTextView;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the view.
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // Find GUI elements.
        RequestTextView = (TextView) v.findViewById(R.id.home_request);
        AboutUsTextView = (TextView) v.findViewById(R.id.home_about_us);
        ContactTextView = (TextView) v.findViewById(R.id.home_contact);

        // Set onClickListeners for the buttons.
        RequestTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRequestFragment();
            }
        });

        AboutUsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAboutUsFragment();
            }
        });

        ContactTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContactFragment();
            }
        });


        return v;
    }

    // Replaces current Fragment with RequestFragment.
    private void showRequestFragment(){
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new RequestFragment())
                .addToBackStack(null)
                .commit();
    }

    // Replaces current fragment with AboutUsFragment.
    private void showAboutUsFragment(){
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new AboutUsFragment())
                .addToBackStack(null)
                .commit();
    }

    // Replaces current fragment with ContactFragment.
    private void showContactFragment(){
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new ContactFragment())
                .addToBackStack(null)
                .commit();
    }
}

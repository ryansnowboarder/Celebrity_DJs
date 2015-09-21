package com.celebritysoundandlight.celebritydjs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by RyanFletcher on 9/15/2015.
 *
 * A simple fragment that displays the "about us" text from
 * the Celebrity Sound and Light website (celebritysoundandlight.com).
 */
public class AboutUsFragment extends Fragment {

    private static final String TAG = "AboutUsFragment";

    public AboutUsFragment(){
    }

    @Override
         public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState){

        // Inflate the view.
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }
}

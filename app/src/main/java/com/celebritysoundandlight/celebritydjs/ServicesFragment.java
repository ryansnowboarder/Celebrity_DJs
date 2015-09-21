package com.celebritysoundandlight.celebritydjs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by RyanFletcher on 9/16/2015.
 */
public class ServicesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        // inflate the view.
        return inflater.inflate(R.layout.fragment_services, container, false);
    }
}

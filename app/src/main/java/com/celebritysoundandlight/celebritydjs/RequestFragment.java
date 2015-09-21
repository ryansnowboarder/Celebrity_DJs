package com.celebritysoundandlight.celebritydjs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RyanFletcher on 9/17/2015.
 *
 * This fragment is utilized by customers to send song requests to the DJ.
 */
public class RequestFragment extends Fragment {

    private static final String TAG = "RequestFragment";

    // Stores the names of the DJs that are registered to the service.
    private ArrayList<CharSequence> DJnames = new ArrayList<CharSequence>();

    // Stores the name of the DJ.
    private String DJname;

    // Displays the names of the DJs that are registered to the service.
    private Spinner selectDJSpinner;

    // Title of the song to be requested.
    private EditText titleEditText;

    // Artist of the song to be requested.
    private EditText artistEditText;

    // Button to submit the request.
    private Button submitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        // Inflate the view.
        View v = inflater.inflate(R.layout.fragment_request, container, false);

        // Find GUI elements.
        selectDJSpinner = (Spinner) v.findViewById(R.id.request_select_dj_spinner);
        titleEditText = (EditText) v.findViewById(R.id.request_title_edittext);
        artistEditText = (EditText) v.findViewById(R.id.request_artist_edittext);
        submitButton = (Button) v.findViewById(R.id.request_submit_button);

        // Find list of DJs from Parse and populate selectDJSpinner.
        populateListOfDJs();

        // setOnItemSelectedListener on selectDJSpinner to set DJName to selected DJ name of
        // spinner
        selectDJSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DJname = (String) DJnames.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });


        // set onClickListerner on submitButton
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });


        return v;
    }

    // Finds a list of the DJs subscribed to the service from parse and
    // populates SelectdJSpinner with the list.
    private void populateListOfDJs(){
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        try {
            List <ParseUser> objects = query.find();
            for (int i = 0; i < objects.size(); i++) {
                DJnames.add(objects.get(i).getUsername());
            }
            // Create an ArrayAdapter using the string array and a default spinner layout.
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                    getContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    DJnames);
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Set the adapter.
            selectDJSpinner.setAdapter(adapter);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    // Send a song request to the DJ.
    private void sendRequest(){
        if (DJname != null) {
            ParsePush push = new ParsePush();
            push.setChannel(DJname);
            push.setMessage("New Request: " + titleEditText.getText().toString() + " by " +
                    artistEditText.getText().toString());
            push.sendInBackground();
            Toast.makeText(getContext(),
                    "Request Sent!",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getContext(),
                    "Select a DJ",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }
}

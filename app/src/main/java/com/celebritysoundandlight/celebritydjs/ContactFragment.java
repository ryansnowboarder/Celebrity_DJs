package com.celebritysoundandlight.celebritydjs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by RyanFletcher on 9/15/2015.
 *
 * A fragment that displays the address, in a Google Map marker, of
 * Celebrity Sound and Light. This fragment also displays the phone number,
 * email address, and links to the Facebook, Twitter, and Pinterest pages
 * of the company. The phone number and email address are displayed
 * in a TextView and the social media sites are linked via pictures of
 * their logos that open the default browser to the URL of the page.
 */
public class ContactFragment extends Fragment {

    private static final String TAG = "ContactFragment";

    // Facebook logo that links to the company page
    private ImageView facebookLogo;

    // Twitter logo that links to the company page
    private ImageView twitterLogo;

    // Pinterest logo that links to the company page
    private ImageView pinterestLogo;

    public ContactFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the view.
        View v = inflater.inflate(R.layout.fragment_contact, container, false);

        // Find the Google Map fragment for later initialization.
        final SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map2);

        // Get the Google Map and set a callback.
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                // Store lat and long of office location.
                // Note that these coordinates had to be changed from the
                // lat and long found by a Google address lookup
                // because those coordinates point to the building
                // across the street.
                LatLng office = new LatLng(42.418893, -71.111757);
                // Store lat and long of the point at which the map is centered.
                // This is slightly further north than the office location
                // so that the caption of the map marker is shown
                // at this default camera position.
                LatLng cameraPosition = new LatLng(42.36, -71.111757);
                // Add the marker pointing to the office location to the map
                // and add the caption.
                map.addMarker(new MarkerOptions()
                        .position(office)
                        // Add company name to the marker.
                        .title(getResources().getString(R.string.company_name))
                        // Add company address to the marker.
                        .snippet(getResources().getString(R.string.company_address)))
                        // Show the marker caption.
                        .showInfoWindow();
                // Move the vantage point to the coordinates specified above.
                map.moveCamera(CameraUpdateFactory.newLatLng(cameraPosition));
                // Zoom in to sufficient detail so that the map does not
                // display the entire world, but instead the local area.
                // This is an 11x zoom and was found by trial and error.
                map.moveCamera(CameraUpdateFactory.zoomTo((float) 11.00));
            }
        });

        // find the GUI elements
        facebookLogo = (ImageView) v.findViewById(R.id.contact_facebook);
        twitterLogo = (ImageView) v.findViewById(R.id.contact_twitter);
        pinterestLogo = (ImageView) v.findViewById(R.id.contact_pinterest);

        // set onClickListeners for facebookLogo, twitterLogo, and pinterestLogo objects
        // that open the respective pages of Celebrity Sound and Light in the browser
        facebookLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFacebookWebView();
            }
        });

        twitterLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTwitterWebView();
            }
        });

        pinterestLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPinterestWebView();
            }
        });

        return v;
    }

    // Opens the Facebook page of the company in the default browser.
    private void showFacebookWebView(){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(getResources().getString(R.string.facebook_page_link)));
        startActivity(i);
    }

    // Opens the Twitter page of the company in the default browser.
    private void showTwitterWebView(){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(getResources().getString(R.string.twitter_page_link)));
        startActivity(i);
    }

    // Opens the Pinterest page of the company in the default browser.
    private void showPinterestWebView(){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(getResources().getString(R.string.pinterst_page_link)));
        startActivity(i);
    }
}

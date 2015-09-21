package com.celebritysoundandlight.celebritydjs;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.parse.Parse;
import com.parse.ParseUser;

/**
 * Created by RyanFletcher on 9/15/2015.
 *
 * This is the only non-library Activity of this application and handles the custom action
 * bar as well as the fragments.
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // Keeps tack of how many times the user has tapped the company
    // logo in the ActionBar. If tapped seven (7) times in thirty (30) seconds,
    // the current fragment is replaced with LoginFragment.
    private int loginTabClicks = 0;

    // A thread to reset loginTabClicks to zero thirty seconds after the
    // first tap of the company logo.
    private boolean threadRunning = false;

    // The company logo in the ActionBar
    private ImageView loginTab;

    // The navigation to HomeFragment in the ActionBar.
    private TextView homeTab;

    // The navigation to ServicesFragment in the ActionBar.
    private TextView servicesTab;

    // The navigation to ContactFragment in the ActionBar.
    private TextView contactTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the view.
        setContentView(R.layout.activity_main);

        // Show the custom ActionBar.
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_custom);

        // Find the GUI elements of the custom ActionBar.
        loginTab = (ImageView) getSupportActionBar().getCustomView().findViewById(R.id.actionbar_logo);
        homeTab = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.actionbar_home);
        servicesTab = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.actionbar_services);
        contactTab = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.actionbar_contact);


        // Set onClickListeners to the GUI elements of the custom ActionBar.
        loginTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLoginTabClick();
            }
        });

        homeTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHomeFragment();
            }
        });

        servicesTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showServicesFragment();
            }
        });

        contactTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContactFragment();
            }
        });

        // Enable Parse local datastore.
        Parse.enableLocalDatastore(this);

        // Initialize Parse MBAAS service.
        Parse.initialize(this, "1tOE6kBvXmbbW4ocXBjCqJqlga5I7Nz9CBmdaigj", "1rIFgybIevHJAaQ16VrsGcBFlweTSASclR2GP6UD");

        // Show the HomeFragment.
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();
    }

    // There is no menu, so we simply return true.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    // Keeps track of taps on the company logo.
    private void handleLoginTabClick(){
        loginTabClicks++;
        if (!threadRunning){
            resetLoginClicks();
        }
        if (loginTabClicks == 7){
            loginTabClicks = 0;
            if (ParseUser.getCurrentUser() != null)
                showLogoutFragment();
            else
                showLoginFragment();
        }
    }

    // Resets loginTabClicks to zero (0) after thirty (30) seconds.
    private void resetLoginClicks(){
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                loginTabClicks = 0;
            }
        };
        thread.start();
    }

    // Replaces the current fragment with LoginFragment.
    private void showLoginFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment())
                .addToBackStack(null)
                .commit();
    }

    // Replaces the current fragment with LogoutFragment.
    private void showLogoutFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new LogoutFragment())
                .addToBackStack(null)
                .commit();
    }

    // Replaces the current fragment with HomeFragment.
    private void showHomeFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .addToBackStack(null)
                .commit();
    }

    // Replaces the current fragment with ServicesFragment.
    private void showServicesFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new ServicesFragment())
                .addToBackStack(null)
                .commit();
    }

    // Replaces the current fragment with ContactFragment.
    private void showContactFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new ContactFragment())
                .addToBackStack(null)
                .commit();
    }
}

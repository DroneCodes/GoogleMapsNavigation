package com.example.googlemapsnavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // initialise variable

    EditText location, destination;
    Button track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign variable to id
        location = findViewById(R.id.location);
        destination = findViewById(R.id.destination);
        track = findViewById(R.id.track);

        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get value from edit test
                String yourlocation = location.getText().toString().trim();
                String yourdestination = destination.getText().toString().trim();

                // check condition
                if (yourlocation.equals("") && yourdestination.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter both Location", Toast.LENGTH_SHORT).show();
                } else {
                    // When both vales are filled
                    // Display track
                    DisplayTrack(yourlocation, yourdestination);
                }
            }
        });
    }

    private void DisplayTrack(String yourlocation, String yourdestination) {
        // if the device does not have a map installed, then redirect user to play store
        try {
            // when google map is installed
            // Initialise uri
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + yourlocation + "/"
                    + yourdestination);
            // Initialise intent with action view
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            // Set package
            intent.setPackage("com.google.android.apps.maps");

            // set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // start activity
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // When google map is not installed
            // Initialise uri
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");

            // initialise intent with action view
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            // Set Flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Start activity
            startActivity(intent);
        }
    }
}
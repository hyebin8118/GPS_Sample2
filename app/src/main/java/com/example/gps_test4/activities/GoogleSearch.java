package com.example.gps_test4.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gps_test4.R;
import com.example.gps_test4.adapter.LocationArrayAdapter;
import com.example.gps_test4.controller.GoogleMapController;
import com.example.gps_test4.controller._LocationController;
import com.example.gps_test4.model._Location;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;

public class GoogleSearch extends AppCompatActivity implements OnMapReadyCallback {
    // Google Map @from https://webnautes.tistory.com/647
    // Android Studio keyPass 및 SHA-1 인증서
    // (Terminal - keytool -list -v -keystore "%USERPROFILE%\.android\debug.keystore" -alias androiddebugkey -storepass android -keypass android)

    _LocationController locationController;
    GoogleMapController googleMapController;
    ArrayList<_Location> locations;
    Spinner spinner_dropdown;
    TextView text_location;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_google_search);

        locationController = new _LocationController(this);
        locations = locationController.getLocationData();

        spinner_dropdown = findViewById(R.id.code_spinner);

        spinner_dropdown.setAdapter(new LocationArrayAdapter(this, locations));
        text_location = findViewById(R.id.location_text);


        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);
        setEvent();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMapController = new GoogleMapController(getApplicationContext(), googleMap);
    }

    public void setEvent(){
        Log.d("CALL_SETEVENT : ","OK");

        // onNothingSelected Method Error

        spinner_dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("SPINNER_SELECTED : ", " : " + position);

                LocationArrayAdapter adapter = (LocationArrayAdapter) parent.getAdapter();
                _Location selectedLocation = (_Location) adapter.getItem(position);

                Log.d("SPINNER_SELECTED : ", " : " + selectedLocation);
                Log.d("SPINNER_SELECTED2 : ", " : " + selectedLocation.getLocation());

                if (selectedLocation != null) {
                    String location_value = selectedLocation.getLocation();
                    String location_code = selectedLocation.getCode();

                    Log.d("SPINNER_RESULT = ", ":" + location_value);

                    text_location.setText(location_code);
                    googleMapController.Search(location_value);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
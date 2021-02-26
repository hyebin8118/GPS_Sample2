package com.example.gps_test4.activities;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gps_test4.R;
import com.example.gps_test4.controller.GoogleMapController;
import com.example.gps_test4.controller._LocationController;
import com.example.gps_test4.model._Location;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.ArrayList;

public class GoogleSearch extends AppCompatActivity implements OnMapReadyCallback {
    // Google Map @from https://webnautes.tistory.com/647
    // Android Studio keyPass 및 SHA-1 인증서

    _LocationController locationController;
    GoogleMapController googleMApController;
    ArrayList<_Location> locations;
    Spinner spinner_dropdown;
    TextView text_location;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_search);
        // excel 파일에서 location 정보를 읽어와 location 객체에 저장한다.



    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}

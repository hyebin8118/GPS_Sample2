package com.example.gps_test4.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gps_test4.R;
import com.example.gps_test4.adapter.LocationArrayAdapter;
import com.example.gps_test4.controller.GoogleMapController;
import com.example.gps_test4.controller._LocationController;
import com.example.gps_test4.model.City;
import com.example.gps_test4.model._Location;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;

public class GoogleSearch extends AppCompatActivity implements OnMapReadyCallback {
    // Google Cloud Platform 관련 링크 참고
    // Google Map @from https://webnautes.tistory.com/647

    _LocationController locationController;
    GoogleMapController googleMapController;
    ArrayList<City> locations_administrative;
    ArrayList<City> locations_court;
    Spinner spinner_city, spinner_gu, spinner_dong;
    TextView text_location, location_administrative_text;
    TextView code_court_text, location_court_text;
    TextView text_latlng;


    /*
     * onCreate Method - Activity 생명 주기로 따지자면 Activity가 실행된 후 가장 먼저 호출되는 메서드
     * 자세한 사항은 https://www.crocus.co.kr/1560 링크 참고
     */
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_google_search);

        locationController = new _LocationController(this);
        locations_administrative = locationController.getLocationData_administrative();
        //locations_court = locationController.getLocationData_court();

        // Spinner
        spinner_city = findViewById(R.id.spinner_city);
        spinner_gu = findViewById(R.id.spinner_gu);
        spinner_dong = findViewById(R.id.spinner_dong);

        code_court_text = findViewById(R.id.code_court_text);
        location_court_text = findViewById(R.id.location_court_text);
        location_administrative_text = findViewById(R.id.location_administrative_text);

        // 첫번째 스피너에 행정구역명을 붙임
        spinner_city.setAdapter(new LocationArrayAdapter(this, locations_administrative));

        text_location = findViewById(R.id.code_administrative_text);
        text_latlng = findViewById(R.id.latitudeAndLongitude_text);

        // SupportMapFragment =
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

        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    //text_location.setText(locationController.classify_city().toString());
                    googleMapController.Search(location_value);
                    text_latlng.setText(googleMapController.latitudeLongitude.toString());
                    //location_administrative_text.setText(_city+" "+_gu+" "+_dong);
                    location_administrative_text.setText(location_value);

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
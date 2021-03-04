package com.example.gps_test4.activities;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class GoogleSearch extends AppCompatActivity implements OnMapReadyCallback {
    // Google Cloud Platform 관련 링크 참고
    // Google Map @from https://webnautes.tistory.com/647

    _LocationController locationController;
    GoogleMapController googleMapController;
    ArrayList<_Location> locations;
    Spinner spinner_dropdown;
    TextView text_location;
    TextView text_latlng;


    /*
    * onCreate Method - Activity 생명 주기로 따지자면 Activity가 실행된 후 가장 먼저 호출되는 메서드
    * 필수적으로 구현해야 한다.
    * 전체 수명 주기 동안 단 한 번만 발생해야 하는 애플리케이션 시작 로직을 실행
    * 데이터를 목록에 바인딩, 일부 클래스 범위 변수를 인스턴스화 가능.
    * 파라미터로 갖는 saveInstanceState = 활동 이전의 상태가 포함된 Bundle
    * 처음 생성된 활동인 경우 Bundle 객체의 값은 null이다.
    * Bundle? 여러가지 타입의 값을 저장하는 Map Class
    * 자세한 사항은 https://www.crocus.co.kr/1560 링크 참고
    */
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_google_search);

        locationController = new _LocationController(this);
        locations = locationController.getLocationData();

        spinner_dropdown = findViewById(R.id.code_spinner);

        spinner_dropdown.setAdapter(new LocationArrayAdapter(this, locations));
        text_location = findViewById(R.id.location_text);
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
                    text_latlng.setText(googleMapController.latitudeLongitude.toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
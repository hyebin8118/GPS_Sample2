package com.example.gps_test4.controller;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class GoogleMapController {
    Context context;
    private GoogleMap googleMap;
    LatLng latitudeLongitude;

    public GoogleMapController(Context context, GoogleMap googleMap){
        this.context = context;
        this.googleMap = googleMap;
    }
    public void Search(String address){
        Location location = getLocationFromAddress(address);
        // 지오코더에서 .getLatitude()와 .getLongitude() 메서드는 각각 위도와 경도를 받는다.
        latitudeLongitude = new LatLng(location.getLatitude(), location.getLongitude());

        // 마커를 경도와 위도가 위치한 곳에 찍는다.
        setMarker(latitudeLongitude);

        // 카메라 좌표를 마커가 찍힌 곳으로 이동
        // newLatLngZoom 인자로는 위치(위도 및 경도)와 Zoom Level이 들어간다.
        // 1 : World, 5 : Landmass / continent, 10 : City, 15 : Streets, 20: Buildings
        // 자세한 사항은 https://breadboy.tistory.com/282 참고
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latitudeLongitude, 18));
    }

    /*public LatLng outputLatlng(String address){
        Location location = getLocationFromAddress(address);
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        return latLng;
    }*/

    // GeoCoding 이란 고유 명칭(주소, 산, 호수의 이름 등)을 가지고 위도와 경도의 좌표값을 얻는 것
    // GeoCoder 를 import 하면 위도와 경도를 가져와 사용할 수 있다.
    public Location getLocationFromAddress(String address){
        Geocoder geocoder = new Geocoder(context);
        List<Address> addressList;
        Location location = new Location("");

        try{
            Log.d("Address_Length : ",address);
            addressList = geocoder.getFromLocationName(address, address.length());
            if(addressList == null || addressList.size()==0){
                return null;
            }else{
                Address addressLoc = addressList.get(0);
                location.setLatitude(addressLoc.getLatitude());
                location.setLongitude(addressLoc.getLongitude());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return location;

    }

    // 지도(위도, 경도에 맞춰)에 마커를 생성
    public void setMarker(LatLng latLng){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        googleMap.addMarker(markerOptions);
    }
}

package com.example.gps_test4.controller;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

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

    public GoogleMapController(Context context, GoogleMap googleMap){
        this.context = context;
        this.googleMap = googleMap;
    }
    public void Search(String address){
        Location location = getLocationFromAddress(address);
        LatLng latitudeLongitude = new LatLng(location.getLatitude(), location.getLongitude());
        setMarker(latitudeLongitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latitudeLongitude, 16));
    }
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
    public void setMarker(LatLng latLng){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        googleMap.addMarker(markerOptions);
    }
}

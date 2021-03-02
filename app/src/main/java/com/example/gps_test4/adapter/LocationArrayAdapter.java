package com.example.gps_test4.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gps_test4.R;
import com.example.gps_test4.model._Location;

import java.util.ArrayList;

public class LocationArrayAdapter extends BaseAdapter {
    Context context;
    private ArrayList<_Location> items;

    public LocationArrayAdapter(Context context, ArrayList<_Location> locations){
        this.context = context;
        this.items = locations;
    }

    //array 계열 container의 size를 반환
    @Override
    public int getCount(){
        // 참이면 0을 반환, 거짓이면 items의 size를 반환
        return (items == null)? 0 : items.size();
    }

    // position (index)에 해당하는 item 반환
    @Override
    public Object getItem(int position){
        return items.get(position);
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent){
        if(converView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            converView = inflater.inflate(R.layout.location_spinner_item, parent, false);
        }
        _Location location = items.get(position);
        TextView spinner_text = converView.findViewById(R.id.spinner_text);
        spinner_text.setText(location.getLocation());
        return converView;
    }
}

// List를 보여주는 것은 전부 ArrayAdapter 사용
// 왜 ArrayAdapter가 있는데 LocationArrayAdapter 사용하는지
// 사용하지 않으면 어떻게 되는지
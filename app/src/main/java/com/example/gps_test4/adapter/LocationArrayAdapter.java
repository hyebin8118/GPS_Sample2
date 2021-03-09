package com.example.gps_test4.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gps_test4.R;
import com.example.gps_test4.controller._LocationController;
import com.example.gps_test4.model._City;
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

    // position 에 해당하는 item 반환
    @Override
    public Object getItem(int position){
        return items.get(position);
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.location_spinner_item, parent, false);
        }
        _Location location = items.get(position);
        TextView spinner_text = convertView.findViewById(R.id.spinner_text);
        spinner_text.setText(location.getLocation());
        return convertView;
    }
}

/*
* _LocationController Class에서 City, Gu, Dong을 전부 나누는 메서드를 생성했으나 어떻게 가져와서 사용해야 할 지 모르겠음
* 법정코드 엑셀 파일을 읽는 메서드는 ExcelController에 추가되어 있음
* 하지만 이 법정 코드를 어떻게 Spinner 에 선택된 item을 기준으로 코드 및 구역 명칭을 연동해서 아래 TextView에 세팅할 지 모르겠음
* */
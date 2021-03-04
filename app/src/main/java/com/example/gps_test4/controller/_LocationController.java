package com.example.gps_test4.controller;

import android.content.Context;

import com.example.gps_test4.model._Location;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class _LocationController {
    final String FILE_NAME = "administractive_code.xls";
    final String COLUMN_CODE = "행정코드";
    final String COLUMN_LOCATION = "행정구역명";
    final String COLUMN_ISEXIST = "여부";

    ExcelController excelController;

    public _LocationController(Context context){
        this.excelController = new ExcelController(context);
    }

    public ArrayList<_Location> getLocationData(){

        // _Location model Class : 행정코드, 행정구역명, 여부의 데이터를 담는 model Class
        ArrayList<_Location> locations = new ArrayList<_Location>();
        ArrayList<HashMap<String, String>> excel_arrayList = this.excelController.readExcel(FILE_NAME);

        // forEach() -- Android Studio SDK version 24부터 지원 (gradle -> minSDK version 29로 변경
        // HashMap<String, String>으로 되어있는 데이터를 Location Class Model로 맵핑하는 과정
        // excel_arrayList의 원소를 각각 꺼내 (a_data) 각각을 Location Class 객체로 맵핑
        excel_arrayList.forEach(a_data->{
            _Location a_location = new _Location();

            String code = a_data.get(COLUMN_CODE);
            String location = a_data.get(COLUMN_LOCATION);
            String isExist = a_data.get(COLUMN_ISEXIST);

            a_location.setCode(code);
            a_location.setLocation(location);

            if(isExist != null && isExist.equals("Y")){
                a_location.setIsExist(true);
            } else{
                a_location.setIsExist(false);
            }
            // HashMap -> Location 객체로 맵핑이 끝났으니 결과 ArrayList에 저장
            locations.add(a_location);
        });
        return locations;
    }
}

package com.example.gps_test4.controller;

import android.content.Context;

import com.example.gps_test4.model._Location;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class _LocationController {
    final String FILE_NAME = "administractive_code.xlsx";
    final String COLUMN_CODE = "행정코드";
    final String COLUMN_LOCATION = "주소";
    final String COLUMN_ISEXIST = "여부";

    ExcelController excelController;

    public _LocationController(Context context){
        this.excelController = new ExcelController();
    }

    public ArrayList<_Location> getLocationData(){
        ArrayList<_Location> locations = new ArrayList<_Location>();
        ArrayList<HashMap<String, String>> excel_arrayList = this.excelController.readExcel(FILE_NAME);

        // forEach() -- Android Studio SDK version 24부터 지원 (gradle -> minSDKversion 24로 변경
        excel_arrayList.forEach(a_data->{
            _Location a_location = new _Location();

            String code = a_data.get(COLUMN_CODE);
            String location = a_data.get(COLUMN_LOCATION);
            String isExist = a_data.get(COLUMN_ISEXIST);

            a_location.setCode(code);
            a_location.setLocation(location);

            if(isExist != null && isExist.equals("Y")){
                a_location.setIsExist(false);
            }
            locations.add(a_location);
        });
        return locations;
    }
}

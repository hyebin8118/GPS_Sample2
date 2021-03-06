package com.example.gps_test4.controller;

import android.content.Context;

import com.example.gps_test4.model.City;
import com.example.gps_test4.model.LocationData;
import com.example.gps_test4.model._Location;

import java.util.ArrayList;
import java.util.HashMap;

public class _LocationController {

    // 행정코드 파일
    final String FILE_NAME_ADMINISTRATIVE = "administrative_code.xls";
    final String COLUMN_CODE_ADMINISTRATIVE = "행정코드";
    final String COLUMN_LOCATION_ADMINISTRATIVE = "행정구역명";
    final String COLUMN_ISEXIST_ADMINISTRATIVE = "여부";

    // 법정코드 파일
    final String FILE_NAME_COURT = "court_code.xls";
    final String COLUMN_CODE_COURT = "법정동코드";
    final String COLUMN_LOCATION_COURT = "법정동명";
    final String COLUMN_ISEXIST_COURT = "여부";

    ExcelController excelController;

    public _LocationController(Context context){
        this.excelController = new ExcelController(context);
    }

    public ArrayList<City> getLocationData_administrative(){

        // _Location model Class : 행정코드, 행정구역명, 여부의 데이터를 담는 model Class
        ArrayList<City> locations = new ArrayList();
        ArrayList<String> excel_arrayList = this.excelController.administrative_readExcel(FILE_NAME_ADMINISTRATIVE);

       /* excel_arrayList.forEach(a_data->{
            _Location a_location = new _Location();

            String code = a_data.get(COLUMN_CODE_ADMINISTRATIVE);
            String location = a_data.get(COLUMN_LOCATION_ADMINISTRATIVE);
            String isExist = a_data.get(COLUMN_ISEXIST_ADMINISTRATIVE);

            a_location.setCode(code);
            a_location.setLocation(location);

            if(isExist != null && isExist.equals("Y")){
                a_location.setIsExist(true);
            } else{
                a_location.setIsExist(false);
            }
            // HashMap -> Location 객체로 맵핑이 끝났으니 결과 ArrayList에 저장
            locations.add(a_location);
        });*/
        return locations;
    }


    // 어디서든 접근 가능한 getLocationData_court()메서드를 생성한다. 이 메서드는 _Location 클래스를 형으로 갖는 ArrayList 를 반환해야 한다.
    public ArrayList<_Location> getLocationData_court(){
        ArrayList<_Location> locations = new ArrayList<>();

        return locations;
    }
}
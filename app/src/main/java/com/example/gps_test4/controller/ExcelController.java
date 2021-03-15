package com.example.gps_test4.controller;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.example.gps_test4.model.City;
import com.example.gps_test4.model.Dong;
import com.example.gps_test4.model.Gu;
import com.example.gps_test4.model.LocationData;

import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;

public class ExcelController extends LocationData{
    Context context;
    public ExcelController(){}
    ArrayList resultArrayList;

    public ExcelController(Context context){
        this.context = context;
    }

    public ArrayList administrative_readExcel(String fileName) {
        resultArrayList = new ArrayList<>();

        try {
            // fileName 은 ExcelController 에서 읽어올 파일으로 초기화 시켜줌
            InputStream inputStream = context.getResources().getAssets().open(fileName);
            Workbook workbook = Workbook.getWorkbook(inputStream);

            // Excel 파일이 있다면
            if(workbook != null){
                // 파일의 첫번째 시트를 불러옴
                Sheet sheet = workbook.getSheet(0);

                // 시트가 존재한다면
                if(sheet != null){

                    // Column 열
                    int colTotal = sheet.getColumns();

                    // Row 행 (int rowTotal = sheet.getRows(); 로 사용해도 무관하다.)
                    int rowTotal = sheet.getColumn(colTotal-1).length;
                    String cellTest = sheet.getCell(0, 1).getContents();
                    String cellTest2 = sheet.getCell(1,2).getContents();

                    // 행(가로)을 반복해서 읽음
                    for(int row_index = 0; row_index < rowTotal; row_index++){

                        String tempValue = sheet.getCell(0, row_index).getContents();
                        LocationData locationData_aObject;

                        switch (tempValue.length()){
                            case 2:
                                if(row_index==0) continue;
                                locationData_aObject = new City();
                                locationData_aObject.setAdministrative_code(tempValue);
                                break;
                            case 5:
                                if(row_index==0) continue;
                                locationData_aObject = new Gu();
                                locationData_aObject.setAdministrative_code(tempValue);
                                break;
                            case 7:
                                if(row_index==0) continue;
                                locationData_aObject = new Dong();
                                locationData_aObject.setAdministrative_code(tempValue);
                                break;
                            default:
                                if(row_index==0) continue;
                                Log.e("tempValue ","Error");
                                locationData_aObject = null;
                        }

                        tempValue = sheet.getCell(1, row_index).getContents();

                        locationData_aObject.setAdministrative_locationName(tempValue);


                        if(row_index != 0){
                            resultArrayList.add(locationData_aObject);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("EXCEL_CONTROLLER : ", e.toString());
        }

        return resultArrayList;
    }

    public ArrayList court_readExcel(String fileName, ArrayList resultArrayList){

        try{
            InputStream inputStream = context.getResources().getAssets().open(fileName);
            Workbook workbook = Workbook.getWorkbook(inputStream);

            if(workbook!=null){
                Sheet sheet = workbook.getSheet(0);

                if(sheet!=null){
                    int rowTotal = sheet.getRows();

                    for(int row_index = 0; row_index < rowTotal; row_index++){
                        String tempValue = sheet.getCell(0, row_index).getContents();

                        // @from https://os94.tistory.com/148, https://cofs.tistory.com/387
                        int count_tempValue = StringUtils.countMatches(tempValue, "0");
                        LocationData temp_locationData;

                        if(row_index != 0 && count_tempValue >= 8){
                            temp_locationData = (LocationData) resultArrayList.get(0);
                            temp_locationData.setCourt_code(tempValue);
                            Log.d("City temp_locationData"," "+tempValue);

                        } else if(row_index != 0 && count_tempValue >= 6){
                            temp_locationData = (LocationData) resultArrayList.get(1);
                            temp_locationData.setCourt_code(tempValue);

                        } else if(row_index != 0 && count_tempValue >= 4){
                            temp_locationData = (LocationData)resultArrayList.get(2);
                            temp_locationData.setCourt_code(tempValue);

                        } else {
                            Log.e("court : ","Error court_code");
                            return null;
                        }
                        tempValue = sheet.getCell(1, row_index).getContents();
                        temp_locationData.setCourt_locationName(tempValue);


                        if(row_index != 0){
                            // resultArrayList에 add를 하면 안되고
                        }
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            Log.d("Court-file", " Error");
        }
        return resultArrayList;
    }
}
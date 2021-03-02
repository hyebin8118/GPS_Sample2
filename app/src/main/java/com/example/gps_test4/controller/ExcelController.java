package com.example.gps_test4.controller;

import android.content.Context;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;

public class ExcelController {

    Context context;
    public ExcelController(){
    }

    public ExcelController(Context context){
        this.context = context;
    }

    public ArrayList readExcel(String fileName) {
        // 결과를 담을 arrayList
        ArrayList resultArrayList = new ArrayList<HashMap<String, String>>();

        try {
            // InputStream - 본인이 작성한 코드 상의 데이터가 아니라, 그 이외의 데아터 유형을 받기 위한 클래스 (파일을 읽고 싶은 경우)
            // 이와 반대로 내보내고 싶을 경우에는 OutputStream 클래스를 이용한다.(파일을 쓰거나 생성하고 싶을 경우)
            InputStream inputStream = context.getResources().getAssets().open("administractive_code.xlsx");

            // ariex574.tistory.com/35
            Workbook workbook = Workbook.getWorkbook(inputStream);

            if(workbook != null){
                Sheet sheet = workbook.getSheet(0);

                if(sheet != null){
                    int colTotal = sheet.getColumns();
                    // 전체 가로줄을 가져옴 -- A열, B열 …
                    int rowTotal = sheet.getColumn(colTotal-1).length;
                    ArrayList<String> fieldNameList = new ArrayList<>();
                    Log.d("EXCEL_CONTROLLER : col", colTotal+"");
                    Log.d("EXCEL_CONTROLLER : row",rowTotal+"");

                   for(int data_index = 0; data_index < rowTotal; data_index++){
                       HashMap a_data = new HashMap<String, String>();

                       for(int field_index = 0; field_index < colTotal; field_index++){
                           // 좌표의 셀의 값을 가져옴
                           String cellValue = sheet.getCell(field_index, data_index).getContents();
                           Log.d("EXCEL_CONTROLLER : ", cellValue);

                           if(data_index == 0){
                               fieldNameList.add(cellValue);
                           } else {
                               String fieldName = fieldNameList.get(field_index);
                               a_data.put(fieldName, cellValue);
                           }
                       }
                       if(data_index != 0){
                           resultArrayList.add(a_data);
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
}

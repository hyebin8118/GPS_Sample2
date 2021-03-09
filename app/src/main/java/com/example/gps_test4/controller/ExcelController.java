package com.example.gps_test4.controller;

import android.content.Context;
import android.util.Log;

import com.example.gps_test4.model._City;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelController {

    Context context;
    public ExcelController(){}

    public ExcelController(Context context){
        this.context = context;
    }

    public ArrayList readExcel(String fileName) {
        // 결과를 담을 arrayList / HashMap을 사용한 이유 - 한 데이터 안의 field 가 key, value 값으로 대응되기 때문
        // ex. 행정코드      : '11'
        // ex. 행정구역명    : '서울특별시'
        // ex. 여    부     : 'Y'
        ArrayList resultArrayList = new ArrayList<HashMap<String, String>>();

        try {
            // fileName은 ExcelController에서 읽어올 파일으로 초기화 시켜줌
            InputStream inputStream = context.getResources().getAssets().open(fileName);

            Workbook workbook = Workbook.getWorkbook(inputStream);

            // Excel 파일이 있다면
            if(workbook != null){
                // 파일의 첫번째 시트를 불러옴
                Sheet sheet = workbook.getSheet(0);

                // 시트가 존재한다면
                if(sheet != null){

                    // Column - 세로 -- 1열, 2열
                    int colTotal = sheet.getColumns();

                    // Row - 가로(전체 가로줄을 가져옴 -- 1행, 2행 …)
                    int rowTotal = sheet.getColumn(colTotal-1).length;

                    ArrayList<String> fieldNameList = new ArrayList<>();

                    // 행(가로)을 반복해서 읽음 (1행, 2행…)
                   for(int data_index = 0; data_index < rowTotal; data_index++){
                       HashMap a_data = new HashMap<String, String>();

                       // 열(세로)을 반복해서 읽음 -- 1행 2행…
                       for(int field_index = 0; field_index < colTotal; field_index++){
                           // 좌표의 셀의 값을 가져옴
                           String cellValue = sheet.getCell(field_index, data_index).getContents();

                           // 0번째 열이라면 - 첫번째 열은 필드 이름
                           if(data_index == 0){
                               fieldNameList.add(cellValue);
                           }
                           // 0번째 열이 아니라면
                           else {
                               String fieldName = fieldNameList.get(field_index);
                               a_data.put(fieldName, cellValue);

                           }
                       }
                       // 결과를 저장할 resultArrayList에 HashMap 객체를 저장
                       if(data_index != 0){
                           resultArrayList.add(a_data);
                       }
                   }
                }
                // administrative_code file 의 4002개, court_code file 의 20,546개를 읽어온다.
                Log.d("resultArrayList.size"," "+resultArrayList.size());

                try{
                    String elements = resultArrayList.get(0).toString();
                    Log.d("index of resultArrayList"," "+elements);
                } catch (Exception e){
                    e.printStackTrace();
                    Log.d("index of resultArrayList Error", " ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("EXCEL_CONTROLLER : ", e.toString());
        }
        return resultArrayList;
    }
}

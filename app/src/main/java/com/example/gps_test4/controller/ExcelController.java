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

    // 엑셀 파일에서 데이터를 읽어올 때 시 - 시에 해당하는 구 - 그 구에 해당하는 동
    // 더이상 그 구에 해당하는 동이 없을 경우에는 시에 해당하는 다음 구로 넘어가 반복
    // 더이상 그 시에 해당하는 구가 없을 경우에는 다음 시로 넘어감

    Context context;
    public ExcelController(){}

    public ExcelController(Context context){
        this.context = context;
    }

    public ArrayList readExcel(String fileName) {
        ArrayList resultArrayList = new ArrayList<HashMap<String, String>>();

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

                    // 첫번째(index 0) 헹을 담을 ArrayList (행정 코드, 행정 구역명, 여부)
                    ArrayList<String> fieldNameList = new ArrayList<>();

                    // 행(가로)을 반복해서 읽음
                   for(int data_index = 0; data_index < rowTotal; data_index++){
                       HashMap a_data = new HashMap<String, String>();

                       // 열(세로)을 반복해서 읽음 -- 1행 2행…
                       for(int field_index = 0; field_index < colTotal; field_index++){
                           // 좌표의 셀의 값을 가져옴
                           String cellValue = sheet.getCell(field_index, data_index).getContents();
                           Log.d("Before cellValue", " "+cellValue);

                           // 0번째 행이라면 - 첫번째 행은 필드 이름
                           if(data_index == 0){
                               fieldNameList.add(cellValue);
                           }
                           // 0번째 행이 아니라면(행정구역명)
                           else {
                               String fieldName = fieldNameList.get(field_index);
                               a_data.put(fieldName, cellValue); //cellValue - 데이터를 저장하는 임시
                           }
                       }

                       // 결과를 저장할 resultArrayList에 HashMap 객체를 저장
                       if(data_index != 0){
                           resultArrayList.add(a_data);
                           Log.d("resultArrayList index 0"," "+resultArrayList.get(0));
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
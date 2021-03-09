package com.example.gps_test4.controller;

import android.content.Context;
import android.util.Log;

import com.example.gps_test4.model._City;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;

public class ExcelController {

    Context context;
    public ExcelController(){}

    public ExcelController(Context context){
        this.context = context;
    }

    public ArrayList readExcel(String fileName) {
        // 결과를 담을 arrayList / HashMap을 사용한 이유 - 한 데이터 안의 field가 key, value 값으로 대응되기 때문
        // ex. 행정코드      : '11'
        // ex. 행정구역명    : '서울특별시'
        // ex. 여    부     : 'Y'
        ArrayList resultArrayList = new ArrayList<HashMap<String, String>>();

        try {
            // fileName은 ExcelController에서 읽어올 파일으로 초기화 시켜줌
            InputStream inputStream = context.getResources().getAssets().open(fileName);

            Workbook workbook = Workbook.getWorkbook(inputStream);

            /*
            * 존재하는 모든 시트와 데이터를 얻는다면
            * 엑셀 파일의 시트 개수를 구하고
            * 반복문(for/while)을 실행하여 각각 데이터를 파싱한 후 ArrayList Class 객체에 저장해 주면 된다.
            * Sheet sheet = workbook.getSheet
            * */

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


                               // sheet.getCell 의 첫번째 셀만 가져온다.

                               // cellValue의 길이가 2와 같다면
                               if(cellValue.length()==2){
                                   Log.d("cellValue.length==2 : ", ""+cellValue);

                               }
                           }
                       }
                       // 결과를 저장할 resultArrayList에 HashMap 객체를 저장
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

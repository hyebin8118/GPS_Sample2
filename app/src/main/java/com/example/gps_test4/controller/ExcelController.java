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
    // 아래의 ExcelController는 Context를 오버로드 했으므로 기본 생성자를 정의해주었다.
    public ExcelController(){}

    public ExcelController(Context context){
        this.context = context;
    }
    // Context란 실행되고 있는 안드로이드 애플리케이션 객체의 정보를 담고 있는 인터페이스.
    // 프로젝트의 디렉토리 구조와 같은 정보로 이를 통해 손쉽게 프로젝트 내부의 경로에 접근할 수 있다.
    // 자세한 정보는 링크 참고 : https://shnoble.tistory.com/57

    public ArrayList readExcel(String fileName) {
        // 결과를 담을 arrayList / HashMap을 사용한 이유 - 한 데이터 안의 field가 key, value 값으로 대응되기 때문
        // ex. 행정코드      : '11'
        // ex. 행정구역명    : '서울특별시'
        // ex. 여    부     : 'Y'
        ArrayList resultArrayList = new ArrayList<HashMap<String, String>>();

        try {
            // fileName은 ExcelController에서 읽어올 파일으로 초기화 시켜줌
            InputStream inputStream = context.getResources().getAssets().open(fileName);

            // Android 에서는 xlxs 파일을 지원하지 않는다. 이를 가능하게(읽을 수 있도록) 하는 외부 라이브러리 - 아래 링크 참고
            // ariex574.tistory.com/35
            // poi 라이브러리를 사용할 수 있으나 이는 Android와 호환성이 좋지 않으므로 사용하지 않았다.
            Workbook workbook = Workbook.getWorkbook(inputStream);

            // Excel 파일이 있다면
            if(workbook != null){
                // 파일의 첫번째 시트를 불러옴
                Sheet sheet = workbook.getSheet(0);

                // 시트가 존재한다면
                if(sheet != null){

                    // Column - 세로
                    int colTotal = sheet.getColumns();

                    // Row - 가로(전체 가로줄을 가져옴 -- 1열, 2열 …)
                    int rowTotal = sheet.getColumn(colTotal-1).length;

                    // 아래 field의 값을 읽어올 때 field_index라는 특정 인덱스를 사용하여 불러오기 때문에
                    // index 를 통해 값을 읽는 배열이나 ArrayList에 field 리스트를 저장해 두면 field_index 하나로 그에 대응하는 이름과 값을 한번에 읽을 때 사용할 수 있음
                    ArrayList<String> fieldNameList = new ArrayList<>();


                    // 열을 반복해서 읽음 (1열, 2열…)
                   for(int data_index = 0; data_index < rowTotal; data_index++){
                       HashMap a_data = new HashMap<String, String>();

                       // 행을 반복해서 읽음(A행, B행…)
                       for(int field_index = 0; field_index < colTotal; field_index++){
                           // 좌표의 셀의 값을 가져옴
                           String cellValue = sheet.getCell(field_index, data_index).getContents();

                           // 0번째 열이라면 - 첫번째 열은 필드 이름
                           if(data_index == 0){
                               fieldNameList.add(cellValue);
                           } else {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("EXCEL_CONTROLLER : ", e.toString());
        }
        // for문을 정상적으로 수행했다면 resultArrayList 객체에는 엑셀 파일에서 읽은 데이터들이 HashMap 객체에 파싱되어 저장되어 있을 것
        // 만약 실패했다면 위의 catch문에서 에러가 발생하며 (for문을 도는 도중 에러가 발생했다면)resultArrayList 에는 모든 결과가 담기지 않을 수도 있다.
        return resultArrayList;
    }
}

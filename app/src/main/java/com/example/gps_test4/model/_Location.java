package com.example.gps_test4.model;

public class _Location {

    // [행정코드] [행정구역명] [여부] 로 이루어진 데이터를 객체화 할 Model(_Location) Class

    private String code;        // 행정코드를 담음
    private String location;    // 행정구역명을 담음
    private Boolean isExist;    // 여부를 담음

    public Boolean getExist(){
        return isExist;
    }
    public String getCode(){
        return code;
    }
    public String getLocation(){
        return location;
    }

    public void setCode(String code){
        this.code = code;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public void setIsExist(Boolean isExist){
        this.isExist = isExist;
    }
}

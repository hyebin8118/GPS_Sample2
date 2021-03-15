package com.example.gps_test4.model;

import androidx.annotation.GuardedBy;

public class _Location {

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


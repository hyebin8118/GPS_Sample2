package com.example.gps_test4.model;

public class _Location {
    private String code;
    private String location;
    private Boolean isExist;

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

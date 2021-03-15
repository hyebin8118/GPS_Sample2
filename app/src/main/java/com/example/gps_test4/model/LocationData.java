package com.example.gps_test4.model;

public class LocationData {
    private String administrative_code;
    private String administrative_locationName;
    private String court_code;
    private String court_locationName;

    public String getAdministrative_code() {
        return administrative_code;
    }

    public String getAdministrative_locationName(){
        return administrative_locationName;
    }

    public void setAdministrative_code(String administrative_code){
        this.administrative_code = administrative_code;
    }

    public void setAdministrative_locationName(String administrative_locationName){
        this.administrative_locationName = administrative_locationName;
    }

    public String getCourt_code(){
        return  court_code;
    }

    public String getCourt_locationName(){
        return court_locationName;
    }
    public void setCourt_code(String court_code){
        this.court_code = court_code;
    }
    public void setCourt_locationName(String court_locationName){
        this.court_locationName = court_locationName;
    }
}

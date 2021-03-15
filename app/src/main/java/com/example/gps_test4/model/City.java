package com.example.gps_test4.model;

import androidx.annotation.GuardedBy;

import java.lang.reflect.Array;
import java.util.ArrayList;

// 시·도를 담을 Class
public class City extends LocationData {
    ArrayList<Gu> guList;

    public ArrayList<Gu> getGuList(){
        return guList;
    }

    public void setGuList(ArrayList<Gu> guList){
        this.guList = guList;
    }

    // 구를 구 리스트에 넣어주는 세터 메서드
    public void addGu(Gu gu){
        this.guList.add(gu);
    }
}

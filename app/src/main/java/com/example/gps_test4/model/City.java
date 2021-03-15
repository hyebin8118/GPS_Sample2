package com.example.gps_test4.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

// 시·도를 담을 Class
public class City extends LocationData {
    ArrayList<Gu> guList;

    public ArrayList<Gu> getGuList(){
        return guList;
    }

    // 이 세터 메서드만을 사용하면 구를 받을 경우 쌓이는게 아닌 새로운 ArrayList 가 생성되기 때문에
    // 아래 addGu 라는 세터 메서드를 생성하였다.
    public void setGuList(ArrayList<Gu> guList){
        this.guList = guList;
    }

    // 구를 구 리스트에 넣어주는 세터 메서드
    public void addGu(Gu gu){
        this.guList.add(gu);
    }
}

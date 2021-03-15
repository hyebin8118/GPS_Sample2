package com.example.gps_test4.model;

import java.util.ArrayList;

// 시·군·구를 담을 Class
public class Gu extends LocationData {
    ArrayList<Dong> dongList;

    public ArrayList<Dong> getDongList(){
        return dongList;
    }

    public void setDongList(ArrayList<Dong> dongList){
        this.dongList = dongList;
    }

    public void addDong(Dong dong){
        this.dongList.add(dong);
    }
}

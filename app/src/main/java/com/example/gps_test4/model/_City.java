package com.example.gps_test4.model;

import java.util.ArrayList;

// 시·도를 담을 Class
public class _City extends _LocationData{
    ArrayList<_Gu> _guList;

    public ArrayList<_Gu> _guList(){
        return _guList;
    }
}

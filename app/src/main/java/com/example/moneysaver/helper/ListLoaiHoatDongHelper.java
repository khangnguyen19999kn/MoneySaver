package com.example.moneysaver.helper;

import com.example.moneysaver.model.LoaiHoatDong;

import java.util.ArrayList;

public class ListLoaiHoatDongHelper {

    public static ArrayList<LoaiHoatDong> getListLoaiHD(){
        ArrayList<LoaiHoatDong> list = new ArrayList<>();
        list.add(new LoaiHoatDong(1001,"Cafe","cafe.png",0));
        list.add(new LoaiHoatDong(1002,"Mua sắm","",0));
        list.add(new LoaiHoatDong(1003,"Thiết bị điện tử","",0));
        list.add(new LoaiHoatDong(1004,"Nhà Hàng","cafe.png",0));
        list.add(new LoaiHoatDong(1005,"Sách Vở","cafe.png",0));
        list.add(new LoaiHoatDong(1006,"Đồ ăn","cafe.png",0));
        return list;
    }
}
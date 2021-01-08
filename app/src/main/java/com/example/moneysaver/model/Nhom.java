package com.example.moneysaver.model;

public class Nhom {
    public int id;
    public int imgNhom;
    public String tenNhom;

    public Nhom(int id, int imgNhom, String tenNhom) {
        this.id      = id;
        this.imgNhom = imgNhom;
        this.tenNhom = tenNhom;
    }
    public int getId() {
        return id;
    }

    public int getImgNhom() {
        return imgNhom;
    }

    public String getTenNhom() {
        return tenNhom;
    }
}

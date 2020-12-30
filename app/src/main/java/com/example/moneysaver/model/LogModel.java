package com.example.moneysaver.model;

public class LogModel {
    private String tenHD;
    private int tien;
    private String date;
    private int isThu;

    public LogModel() {
    }

    public LogModel(String tenHD, int tien, String date,int isThu) {
        this.tenHD = tenHD;
        this.tien = tien;
        this.date = date;
        this.isThu = isThu;

    }

    public int getIsThu() {
        return isThu;
    }

    public void setIsThu(int isThu) {
        this.isThu = isThu;
    }

    public String getTenHD() {
        return tenHD;
    }

    public void setTenHD(String tenHD) {
        this.tenHD = tenHD;
    }

    public int getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

package com.example.moneysaver.model;

public class LoaiHoatDong {
    private int id;
    private String tenHoatDong;
    private String maIcon;
    private int isThu;//1-Thu  0-Chi

    public LoaiHoatDong() {
    }

    public LoaiHoatDong(int id,String tenHoatDong, String maIcon, int isThu) {
        this.id = id;
        this.tenHoatDong = tenHoatDong;
        this.maIcon = maIcon;
        this.isThu = isThu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenHoatDong() {
        return tenHoatDong;
    }

    public void setTenHoatDong(String tenHoatDong) {
        this.tenHoatDong = tenHoatDong;
    }

    public String getMaIcon() {
        return maIcon;
    }

    public void setMaIcon(String maIcon) {
        this.maIcon = maIcon;
    }

    public int isThu() {
        return isThu;
    }

    public void setThu(int thu) {
        isThu = thu;
    }

    @Override
    public String toString() {
        return "LoaiHoatDong{" +
                "id=" + id +
                ", tenHoatDong='" + tenHoatDong + '\'' +
                ", maIcon='" + maIcon + '\'' +
                ", isThu=" + isThu +
                '}';
    }
}

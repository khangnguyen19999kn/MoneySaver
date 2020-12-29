package com.example.moneysaver.model;

public class LogModel {
    private String tenHD;
    private int tien;
    private String ngayThang;
    private String maIcon;
    private String ghiChu;

    public LogModel() {
    }

    public LogModel(String tenHD, int tien, String ngayThang, String maIcon, String ghiChu) {
        this.tenHD = tenHD;
        this.tien = tien;
        this.ngayThang = ngayThang;
        this.maIcon = maIcon;
        this.ghiChu = ghiChu;
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

    public String getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(String ngayThang) {
        this.ngayThang = ngayThang;
    }

    public String getMaIcon() {
        return maIcon;
    }

    public void setMaIcon(String maIcon) {
        this.maIcon = maIcon;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}

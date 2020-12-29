package com.example.moneysaver.model;

public class ChiTieu {
    private long id;
    private long idLoaiHoatDong;
    private long money;
    private String date;
    private String note;

    public long getId() {
        return id;
    }
    public ChiTieu(){

    }
    public ChiTieu(long idLoaiHoatDong, long money, String date, String note) {
        this.idLoaiHoatDong = idLoaiHoatDong;
        this.money = money;
        this.date = date;
        this.note = note;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdLoaiHoatDong() {
        return idLoaiHoatDong;
    }

    public void setIdLoaiHoatDong(long idLoaiHoatDong) {
        this.idLoaiHoatDong = idLoaiHoatDong;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "ChiTieu{" +
                "id=" + id +
                ", idLoaiHoatDong=" + idLoaiHoatDong +
                ", money=" + money +
                ", date='" + date + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}

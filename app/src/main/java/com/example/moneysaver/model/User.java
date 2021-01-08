package com.example.moneysaver.model;

public class User {
    private String id;
    private String pass;
    private int status;
    private String idVi;

    public User(){
    }
    public User(String id, String pass, int status, String idVi) {
        this.id = id;
        this.pass = pass;
        this.status = status;
        this.idVi = idVi;
    }

    public String getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    public int getStatus() {
        return status;
    }

    public String getIdVi() {
        return idVi;
    }
}

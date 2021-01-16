package com.example.moneysaver.model;

public class User {
    private String id;
    private String pass;
    private int status;
    private String idVi;
    private int level;

    public User(){
    }
    public User(String id, String pass, int status, String idVi, int level) {
        this.id = id;
        this.pass = pass;
        this.status = status;
        this.idVi = idVi;
        this.level = level;
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

    public int getLevel() {
        return level;
    }
}

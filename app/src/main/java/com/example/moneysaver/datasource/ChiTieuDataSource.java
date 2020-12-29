package com.example.moneysaver.datasource;


import android.content.Context;
import android.database.SQLException;


import com.example.moneysaver.SQLite;



public class ChiTieuDataSource {
    // Các trường database.
    public static final String TABLE_CHITIEU = "chitieu";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ID_LOAI_HOAT_DONG = "idLoaiHoatDong";
    public static final String COLUMN_MONEY = "money";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_ID_VI = "idVi";


    private SQLite dbHelper;

    public ChiTieuDataSource(Context context) {
        dbHelper = new SQLite(context, "taikhoan.sqlite",null, 1);
    }

    public void open() throws SQLException {
    }

    public void close() {
    }

    public void createChiTieu(long idLoaiHoatDong, long money, String date, String note,String idVi) {
        //create table
        String sql = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CHITIEU + "( " + COLUMN_ID
                + " integer primary key autoincrement, " + COLUMN_ID_LOAI_HOAT_DONG
                + " interger not null, "+ COLUMN_MONEY
                + " text not null, "+ COLUMN_DATE
                + " text not null, "+ COLUMN_NOTE
                + " text, "+COLUMN_ID_VI+ " text not null );";
        dbHelper.queryData(sql);
        //insert data


        dbHelper.queryData("INSERT INTO chitieu (idLoaiHoatDong,money,date,note,idVi) VALUES ("+idLoaiHoatDong+","+money+
                ",\"" + date + "\",\"" + note + "\", \""+idVi+"\")");

    }

}

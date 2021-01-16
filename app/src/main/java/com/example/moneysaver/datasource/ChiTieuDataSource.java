package com.example.moneysaver.datasource;


import android.content.Context;
import android.database.SQLException;


import com.example.moneysaver.SQLite;
import com.example.moneysaver.model.ChiTieu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


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
//        String sql = "CREATE TABLE IF NOT EXISTS "
//                + TABLE_CHITIEU + "( " + COLUMN_ID
//                + " integer primary key autoincrement, " + COLUMN_ID_LOAI_HOAT_DONG
//                + " interger not null, "+ COLUMN_MONEY
//                + " text not null, "+ COLUMN_DATE
//                + " text not null, "+ COLUMN_NOTE
//                + " text, "+COLUMN_ID_VI+ " text not null );";
//        dbHelper.queryData(sql);
        //insert data


//        dbHelper.queryData("INSERT INTO chitieu (idLoaiHoatDong,money,date,note,idVi) VALUES ("+idLoaiHoatDong+","+money+
//                ",\"" + date + "\",\"" + note + "\", \""+idVi+"\")");



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("chitieu");
        final ChiTieu chiTieu = new ChiTieu(idLoaiHoatDong,money,date,note,"user1vi1");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // getValue() returns Long
                ArrayList<Object> list = (ArrayList<Object>) dataSnapshot.getValue();
                myRef.child(list.size()+"").setValue(chiTieu);  // <= Change to ++count
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // throw an error if setValue() is rejected
                throw databaseError.toException();
            }
        });

    }

}

package com.example.moneysaver.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.moneysaver.SQLite;
import com.example.moneysaver.model.ChiTieu;
import com.example.moneysaver.model.LoaiHoatDong;

public class SqlLoaiHoatDongHelper {
    private SQLite dbHelper;
    SQLiteOpenHelper _openHelper;
    public static final String TABLE_CHITIEU = "loaihoatdong";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEN = "tenHoatDong";
    public static final String COLUMN_MAICON = "maIcon";
    public static final String COLUMN_ISTHU = "isThu";


    public SqlLoaiHoatDongHelper(Context context) {

        _openHelper = new SimpleSQLiteOpenHelper(context);
        dbHelper = new SQLite(context, "taikhoan.sqlite",null, 1);


    }
    class SimpleSQLiteOpenHelper extends SQLiteOpenHelper {

        SimpleSQLiteOpenHelper(Context context) {
            super(context, "taikhoan.sqlite", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table loaihoatdong (id integer primary key,tenHoatDong text,maIcon text,isThu integer)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }


    public Cursor getAll() {
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }
        return db.rawQuery("select * from "+TABLE_CHITIEU, null);
    }


    public LoaiHoatDong findOne(long id) {
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }
        LoaiHoatDong loaiHoatDong = new LoaiHoatDong();
        Cursor cur = db.rawQuery("select tenHoatDong, maIcon, isThu from "+TABLE_CHITIEU+ " where id = ?", new String[] { String.valueOf(id) });
        if (cur.moveToNext()) {
            loaiHoatDong.setTenHoatDong(cur.getString(0));
            loaiHoatDong.setMaIcon(cur.getString(1));
            loaiHoatDong.setThu(cur.getInt(2));

        }
        cur.close();
        db.close();
        return loaiHoatDong;
    }


    public long add(LoaiHoatDong loaiHoatDong) {

        String sql = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CHITIEU + "( " + COLUMN_ID
                + " integer primary key, " + COLUMN_TEN
                + " text not null, "+ COLUMN_MAICON
                + " text not null, "+ COLUMN_ISTHU
                + " integer not null);";
        dbHelper.queryData(sql);




        SQLiteDatabase db = _openHelper.getWritableDatabase();
        if (db == null) {
            return 0;
        }
        ContentValues row = new ContentValues();
        row.put(COLUMN_ID,loaiHoatDong.getId());
        row.put(COLUMN_TEN, loaiHoatDong.getTenHoatDong());
        row.put(COLUMN_MAICON, loaiHoatDong.getMaIcon());
        row.put(COLUMN_ISTHU, loaiHoatDong.isThu());


        long id = db.insert(TABLE_CHITIEU, null, row);
        db.close();
        return id;
    }


    public void delete(long id) {
        SQLiteDatabase db = _openHelper.getWritableDatabase();
        if (db == null) {
            return;
        }
        db.delete(TABLE_CHITIEU, "id = ?", new String[] { String.valueOf(id) });
        db.close();
    }

    public void update(LoaiHoatDong loaiHoatDong) {
        SQLiteDatabase db = _openHelper.getWritableDatabase();
        if (db == null && loaiHoatDong.getId() ==-1) {
            return;
        }
        ContentValues row = new ContentValues();
        row.put(COLUMN_TEN, loaiHoatDong.getTenHoatDong());
        row.put(COLUMN_MAICON, loaiHoatDong.getMaIcon());
        row.put(COLUMN_ISTHU, loaiHoatDong.isThu());
        db.update(TABLE_CHITIEU, row, "id = ?", new String[] { String.valueOf(loaiHoatDong.getId()) } );
        db.close();
    }
}

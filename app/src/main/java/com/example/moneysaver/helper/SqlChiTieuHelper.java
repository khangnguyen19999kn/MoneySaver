package com.example.moneysaver.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.moneysaver.SQLite;
import com.example.moneysaver.model.ChiTieu;

public class SqlChiTieuHelper {
    private SQLite dbHelper;
    SQLiteOpenHelper _openHelper;
    public static final String TABLE_CHITIEU = "chitieu";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ID_LOAI_HOAT_DONG = "idLoaiHoatDong";
    public static final String COLUMN_MONEY = "money";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_ID_VI = "idVi";


    public SqlChiTieuHelper(Context context) {

        _openHelper = new SimpleSQLiteOpenHelper(context);
        dbHelper = new SQLite(context, "taikhoan.sqlite",null, 1);


    }
    class SimpleSQLiteOpenHelper extends SQLiteOpenHelper {

        SimpleSQLiteOpenHelper(Context context) {
            super(context, "taikhoan.sqlite", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table ct_hoatdong (id integer primary key autoincrement,idLoaiHoatDong integer,money integer,date text,note text,idVi text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }


    public Cursor getAllCtHoatDong() {
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }
        return db.rawQuery("select * from "+TABLE_CHITIEU, null);
    }


    public ChiTieu findOneCtHoatDong(long id) {
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }
       ChiTieu chiTieu = new ChiTieu();
        Cursor cur = db.rawQuery("select idLoaiHoatDong, money, date, note from "+TABLE_CHITIEU+ " where id = ?", new String[] { String.valueOf(id) });
        if (cur.moveToNext()) {
            chiTieu.setIdLoaiHoatDong(cur.getInt(1));
            chiTieu.setMoney(cur.getInt(2));
            chiTieu.setDate(cur.getString(3));
            chiTieu.setNote(cur.getString(4));

        }
        cur.close();
        db.close();
        return chiTieu;
    }


    public long addCtHoatDong(ChiTieu chiTieu) {

        String sql = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CHITIEU + "( " + COLUMN_ID
                + " integer primary key autoincrement, " + COLUMN_ID_LOAI_HOAT_DONG
                + " interger not null, "+ COLUMN_MONEY
                + " text not null, "+ COLUMN_DATE
                + " text not null, "+ COLUMN_NOTE
                + " text, "+COLUMN_ID_VI+ " text not null );";
        dbHelper.queryData(sql);




        SQLiteDatabase db = _openHelper.getWritableDatabase();
        if (db == null) {
            return 0;
        }
        ContentValues row = new ContentValues();
        row.put("idLoaiHoatDong", chiTieu.getIdLoaiHoatDong());
        row.put("money", chiTieu.getMoney());
        row.put("date", chiTieu.getDate());
        row.put("note", chiTieu.getNote());
        row.put("idVi",1);

        long id = db.insert(TABLE_CHITIEU, null, row);
        db.close();
        return id;
    }


    public void deleteCtHoatDong(long id) {
        SQLiteDatabase db = _openHelper.getWritableDatabase();
        if (db == null) {
            return;
        }
        db.delete("ct_hoatdong", "id = ?", new String[] { String.valueOf(id) });
        db.close();
    }

    public void updateCtHoatDong(ChiTieu chiTieu) {
        SQLiteDatabase db = _openHelper.getWritableDatabase();
        if (db == null && chiTieu.getId() ==-1) {
            return;
        }
        ContentValues row = new ContentValues();
        row.put("idLoaiHoatDong", chiTieu.getIdLoaiHoatDong());
        row.put("money", chiTieu.getMoney());
        row.put("date", chiTieu.getDate());
        row.put("note", chiTieu.getNote());
        db.update("ct_hoatdong", row, "id = ?", new String[] { String.valueOf(chiTieu.getId()) } );
        db.close();
    }
}

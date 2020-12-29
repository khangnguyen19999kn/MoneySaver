package com.example.moneysaver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.moneysaver.model.LogModel;

public class SqlLiteHelper {
    SQLiteOpenHelper _openHelper;

    public SqlLiteHelper(Context context) {

        _openHelper = new SimpleSQLiteOpenHelper(context);


    }
    class SimpleSQLiteOpenHelper extends SQLiteOpenHelper {
        SimpleSQLiteOpenHelper(Context context) {
            super(context, "test.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table ct_hoatdong (_id integer primary key autoincrement,tenHD text, tien int, ngayThang text,maIcon text,ghiChu text)");
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
        return db.rawQuery("select * from ct_hoatdong", null);
    }


    public ContentValues get(long id) {
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }
        ContentValues row = new ContentValues();
        Cursor cur = db.rawQuery("select tenHD, tien, maIcon from ct_hoatdong where _id = ?", new String[] { String.valueOf(id) });
        if (cur.moveToNext()) {
            row.put("tenHD", cur.getString(0));
            row.put("tien", cur.getInt(1));
            row.put("maIcon", cur.getInt(2));
        }
        cur.close();
        db.close();
        return row;
    }


    public long add(LogModel logModel) {
        SQLiteDatabase db = _openHelper.getWritableDatabase();
        if (db == null) {
            return 0;
        }
        ContentValues row = new ContentValues();
        row.put("tenHD", logModel.getTenHD());
        row.put("tien", logModel.getTien());
        row.put("ngayThang", logModel.getNgayThang());
        row.put("maIcon", logModel.getMaIcon());
        row.put("ghiChu", logModel.getGhiChu());
        long id = db.insert("ct_hoatdong", null, row);
        db.close();
        return id;
    }


    public void delete(long id) {
        SQLiteDatabase db = _openHelper.getWritableDatabase();
        if (db == null) {
            return;
        }
        db.delete("ct_hoatdong", "_id = ?", new String[] { String.valueOf(id) });
        db.close();
    }

    public void update(long id, String title, int priority) {
        SQLiteDatabase db = _openHelper.getWritableDatabase();
        if (db == null) {
            return;
        }
        ContentValues row = new ContentValues();
        row.put("title", title);
        row.put("priority", priority);
        db.update("todos", row, "_id = ?", new String[] { String.valueOf(id) } );
        db.close();
    }
}

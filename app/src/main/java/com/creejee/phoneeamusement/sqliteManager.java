package com.creejee.phoneeamusement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import java.sql.Blob;

/**
 * Created by my on 2016-12-03.
 */

public class sqliteManager extends SQLiteOpenHelper{
    public SQLiteDatabase _db = null;
    public Cursor cursor = null;

    public sqliteManager(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블을 생성한다.
        // create table 테이블명 (컬럼명 타입 옵션);
            db.execSQL("CREATE TABLE IF NOT EXIST `cardList`(" +
                        " `idx` INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " `cardName` TEXT "+
                        " `tagInfo` BLOB, " +
                        " `tagId` BLOB" +
                        " `tagContent` BLOB"+ //split data for ','
                    ");");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
            when database column is change
            //but I think it doesn't change :D
         */
    }
    public void open(){
        _db = getWritableDatabase();
    }
    public void query(String _query) {
        _db.execSQL(_query);
    }
    public void close(){
        _db.close();
    }
    public byte[][][] getData(String tableName, String columnArray[]) {

        SQLiteDatabase db = getReadableDatabase();
        String columnString = "`"+TextUtils.join("` , `",columnArray)+"`";
        cursor = db.rawQuery("select "+columnString+" from "+"`tableName`", null);
        byte data[][][] = {};
        int cursorNum = 0;
        while(cursor.moveToNext()) {
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                data[cursorNum][i] = cursor.getBlob(i);
            }
            cursorNum++;
        }
        return data;
    }
    public void print(){

    }
}


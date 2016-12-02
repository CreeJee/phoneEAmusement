package com.creejee.phoneeamusement;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by my on 2016-12-03.
 */

public class sqliteManager extends SQLiteOpenHelper{
    public SQLiteDatabase _db = null;

    public sqliteManager(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블을 생성한다.
        // create table 테이블명 (컬럼명 타입 옵션);
            db.execSQL("CREATE TABLE FOOD_LIST(" +
                        " idx INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " tagInfo BLOB, " +
                        " tagId TEXT" +
                        " tagContent BLOB"+ //split data for ','
                    ");");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

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
}


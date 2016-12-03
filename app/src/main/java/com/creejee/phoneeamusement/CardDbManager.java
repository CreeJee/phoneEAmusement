package com.creejee.phoneeamusement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import java.sql.Blob;
import java.util.Hashtable;
import java.util.Objects;

/**
 * Created by my on 2016-12-03.
 */

public class CardDbManager extends SQLiteOpenHelper{

    public SQLiteDatabase _db = null;
    public Cursor cursor = null;
    public String mode = null;

    public CardDbManager(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블을 생성한다.
        // create table 테이블명 (컬럼명 타입 옵션);
            db.execSQL("CREATE TABLE IF NOT EXIST `cardList`(" +
                        " `idx` INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " `cardName` BLOB "+
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
    public CardDbManager open(String mode){
        _db = (mode == "r") ? getReadableDatabase() : (mode == "w") ? getWritableDatabase() : null;
        this.mode = mode;
        return this;
    }
    public CardDbManager query(String _query) {
        if (this.mode == "w") {
            _db.execSQL(_query);
        }
        else if(this.mode == "r"){
            _db.rawQuery(_query,null);
        }
        else {
            throw new ArithmeticException("you have to send query before open");
        }
        return this;
    }
    public void close(){
        _db.close();
    }
    public Hashtable<Integer,Hashtable<String,Object>> getData(String tableName, String columnArray[]) {

        SQLiteDatabase db = getReadableDatabase();
        String columnString = "`"+TextUtils.join("` , `",columnArray)+"`";
        cursor = db.rawQuery("select "+columnString+" from "+"`"+tableName+"`", null);
        Hashtable<Integer,Hashtable<String,Object>> data = new Hashtable<>();
        int cursorNum = 0;
        while(cursor.moveToNext()) {

            Hashtable<String,Object> tempData = new Hashtable<>();
            tempData.put("idx",cursor.getInt(0));
            data.put(cursorNum,tempData);

            cursorNum++;
        }
        return data;
    }
    public void print(){

    }
}


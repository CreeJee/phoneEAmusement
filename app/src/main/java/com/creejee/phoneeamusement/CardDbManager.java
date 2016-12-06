package com.creejee.phoneeamusement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.sql.Blob;
import java.util.Hashtable;
import java.util.Objects;

import com.creejee.phoneeamusement.DbStruct;

/**
 * Created by my on 2016-12-03.
 */

public class CardDbManager extends SQLiteOpenHelper{

    public SQLiteDatabase _db = null;
    public Cursor cursor = null;
    public String mode = null;
    public DbStruct dbStruct = DbStruct.getInstance();

    public CardDbManager(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블을 생성한다.
        // create table 테이블명 (컬럼명 타입 옵션);
            db.execSQL("CREATE TABLE IF NOT EXISTS `cardList`(" +
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
    public CardDbManager open(String mode){
        _db = (mode == "r") ? getReadableDatabase() : (mode == "w") ? getWritableDatabase() : null;
        this.mode = mode;
        return this;
    }
    public CardDbManager query(String _query) {
        if (this.mode == "w") {
            _db.execSQL(_query);
            Log.d("CardDbMangager","is Write Mode");
        }
        else if(this.mode == "r"){
            cursor = _db.rawQuery(_query,null);
            Log.d("CardDbMangager","is Read Mode");
        }
        else {
            throw new ArithmeticException("you have to send query before open");
        }
        return this;
    }
    public void close(){
        _db.close();
    }
    public DbStruct getData() {
        if(this.mode == "r") {
            int cursorNum = 0;
            while (cursor.moveToNext()) {
                dbStruct.putRow(cursorNum,"idx",cursor.getInt(0));
                dbStruct.putRow(cursorNum,"cardName",cursor.getString(1));
                dbStruct.putRow(cursorNum,"tagInfo",cursor.getBlob(2));
                dbStruct.putRow(cursorNum,"tagId",cursor.getBlob(3));
                dbStruct.putRow(cursorNum,"tagContent",cursor.getBlob(4));
                cursorNum++;
            }
            return DbStruct.getInstance();
        }
        else{
            throw new ArithmeticException("when called your mode is not readable");
        }
    }
    public void print(){

    }
}


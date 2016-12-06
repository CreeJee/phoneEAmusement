package com.creejee.phoneeamusement;

import java.util.Hashtable;

/**
 * Created by my on 2016-12-03.
 * @// TODO: 2016-12-03 Add HashTable Template
 */
public class DbStruct {
    //private vars
    private static DbStruct instance = new DbStruct();
    private Hashtable<Integer,Hashtable<String,Object>> dbStruct;
    private Hashtable<String,Object> tempResult;

    //constructor
    private DbStruct() {
        dbStruct = new Hashtable<>();
        tempResult = new Hashtable<>();
    }

    //
    public static DbStruct getInstance() {
        return instance;
    }
    public Hashtable<String,Object> getRow(Integer rowKey){
            return dbStruct.get(rowKey);
    }
    public Object getColumn(Integer rowKey,String ColumnName){
        rowKey = (rowKey > 0) ? rowKey : dbStruct.size();
        return dbStruct.get(rowKey).get(ColumnName);
    }
    public void putRow(Integer rowKey,String columnName,Object someValues){
        rowKey = (rowKey > 0) ? rowKey : dbStruct.size();

        tempResult.put(columnName,someValues);
        dbStruct.put(rowKey,tempResult);
        tempResult.clear();
    }
    public void clear(){
        dbStruct.clear();
    }


    /*get set function*/
    public void setdbStruct(Hashtable<Integer, Hashtable<String, Object>> dbStruct) {
        this.dbStruct = dbStruct;
    }
    public Hashtable<Integer, Hashtable<String, Object>> getdbStruct() {
        return dbStruct;
    }
}

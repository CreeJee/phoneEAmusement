package com.creejee.phoneeamusement;

import java.util.ArrayList;
import java.util.Hashtable;
//TODO : add multimap import

/**
 * Created by my on 2016-12-03.
 * @// TODO: 2016-12-03 Add HashTable Template
 */
public class DbStruct {
    //private vars
    private static DbStruct instance = new DbStruct();
    private ArrayList<Hashtable<String,String>> dbStruct;
    private Hashtable<String,String> tempResult;
    //constructor
    private DbStruct() {
        dbStruct = new ArrayList<>();
        tempResult = new Hashtable<>();
}

    //instance
    public static DbStruct getInstance() {
        return instance;
    }

    //getData
    public String getColumn(Integer rowKey,String columnName){
        String ret = dbStruct.get(rowKey).get(columnName);
        if(ret != null){
            return ret;
        }
        else{
            throw new NullPointerException("value is null");
        }
    }
    public void putRow(Integer rowKey,String columnName,String someValues){
        rowKey = (rowKey > 0) ? rowKey : dbStruct.size();
        tempResult.put(columnName,someValues);
        dbStruct.add(tempResult);
        tempResult.clear();
    }

    //data check
    public boolean isEmpty(){
        return dbStruct.isEmpty();
    }
    //struct clear
    public void clear(){
        dbStruct.clear();
    }

    public int size(){
        return dbStruct.size();
    }
    /*get set function*/
    public void setdbStruct(ArrayList<Hashtable<String,String>> dbStruct) {
        this.dbStruct = dbStruct;
    }
    public ArrayList<Hashtable<String,String>> getdbStruct() {
        return dbStruct;
    }
}

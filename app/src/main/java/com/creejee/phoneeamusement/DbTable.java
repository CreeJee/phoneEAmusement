package com.creejee.phoneeamusement;

import java.util.Hashtable;

/**
 * Created by my on 2016-12-03.
 * @// TODO: 2016-12-03 Add HashTable Template
 */
public class DbTable {
    private static DbTable instance = new DbTable();
    private Hashtable<Integer,Hashtable<String,Object>> _dbStruct;
    public static DbTable getInstance() {
        return instance;
    }
    private Hashtable<String,Object> tempResult = new Hashtable<>();
    private DbTable() {
        _dbStruct = new Hashtable<>();
    }
    public Hashtable<String,Object> getRow(Integer rowKey){
            return _dbStruct.get(rowKey);
    }
    public Object getColumn(Integer rowKey,String ColumnName){
        rowKey = (rowKey > 0) ? rowKey : _dbStruct.size();
        return _dbStruct.get(rowKey).get(ColumnName);
    }
    public void putRow(Integer rowKey,String columnName,Object someValues){
        rowKey = (rowKey > 0) ? rowKey : _dbStruct.size();

        tempResult.put(columnName,someValues);
        _dbStruct.put(rowKey,tempResult);
        tempResult.clear();
    }
    public void clear(){
        _dbStruct.clear();
    }


    /*get set function*/
    public void set_dbStruct(Hashtable<Integer, Hashtable<String, Object>> _dbStruct) {
        this._dbStruct = _dbStruct;
    }

    public Hashtable<Integer, Hashtable<String, Object>> get_dbStruct() {
        return _dbStruct;
    }
}

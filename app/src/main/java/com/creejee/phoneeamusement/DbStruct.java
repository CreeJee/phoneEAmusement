package com.creejee.phoneeamusement;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Created by my on 2016-12-03.
 * @// TODO: 2016-12-03 Add HashTable Template
 */
public class DbStruct {
    //private vars
    private static DbStruct instance = new DbStruct();
    private Hashtable<Integer,Hashtable<String,String>> dbStruct;
    private Hashtable<String,String> tempResult;
    private int nextRow = 0;
    private Iterator<Map.Entry<Integer,Hashtable<String,String>>> it;

    //constructor
    private DbStruct() {
        dbStruct = new Hashtable<>();
        tempResult = new Hashtable<>();
        it = dbStruct.entrySet().iterator();
}

    //instance
    public static DbStruct getInstance() {
        return instance;
    }

    //getData
    public Hashtable<String,String> getRow(Integer rowKey){
        Hashtable<String,String> temp = new Hashtable<>();
        for (Map.Entry<Integer,Hashtable<String,String>> o : dbStruct.entrySet()   ) {
            if(o.getKey() == rowKey){
                for (Map.Entry<String,String> oo : o.getValue().entrySet()    ) {
                    temp.put(oo.getKey(), oo.getValue());
                }
            }
        }
        return temp;
    }
    public HashMap<String,String> getColumn(Integer rowKey,String ColumnName){
        HashMap<String,String> temp = new HashMap<>();
        for (Map.Entry<Integer,Hashtable<String,String>> o : dbStruct.entrySet()   ) {
            if(o.getKey() == rowKey){
                for (Map.Entry<String,String> oo : o.getValue().entrySet()    ) {
                    if (oo.getKey() == ColumnName){
                        temp.put(oo.getKey(), oo.getValue());
                    }
                }
            }
        }
        return temp;
    }
    public void putRow(Integer rowKey,String columnName,String someValues){
        rowKey = (rowKey > 0) ? rowKey : dbStruct.size();

        tempResult.put(columnName,someValues);
        dbStruct.put(rowKey,tempResult);
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

    public Iterator<Map.Entry<Integer,Hashtable<String,String>>> getIterator(){
         return (this.it = dbStruct.entrySet().iterator());
    }
    public boolean hasNext(){
        return it.hasNext();
    }
    public Map.Entry next(){
        Map.Entry<Integer,Hashtable<String,String>> data = it.next();
        Map.Entry entry = null;
        for (Map.Entry o: data.getValue().entrySet()){
             entry = o;
        }
        return entry;
    }
    public int size(){
        return dbStruct.size();
    }
    /*get set function*/
    public void setdbStruct(Hashtable<Integer, Hashtable<String, String>> dbStruct) {
        this.dbStruct = dbStruct;
    }
    public Hashtable<Integer, Hashtable<String, String>> getdbStruct() {
        return dbStruct;
    }
}

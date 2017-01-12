package com.creejee.phoneeamusement;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
//TODO : add multimap import

/**
 * Created by my on 2016-12-03.
 * @// TODO: 2016-12-03 Add HashTable Template
 */
public class DbStruct {
    //private vars
    private static DbStruct instance = new DbStruct();
    private Multimap<String,String> dbStruct;
    private HashMap<String,String> temp;
    //constructor
    private DbStruct() {
        dbStruct = ArrayListMultimap.create();
    }
    //instance
    public static DbStruct getInstance() {
        return instance;
    }

    //putData
    public void putRow(String columnName,String someValues){
        dbStruct.put(columnName,someValues);
    }
    //getData
    public Map<String,String> get(String[] keyList){
        Map<String,String> res = new HashMap<>();
        for (Map.Entry collection : this.dbStruct.entries()){
            for (int i = 0; i < keyList.length; i++) {
                if(keyList[i] == collection.getKey()){
                    res.put(collection.getKey().toString(),collection.getValue().toString());
                }
            }
        }
        return res;
    }
    public Map<String,String> get(Collection<String> keyList){
        Map<String,String> res = new HashMap<>();
        for (Map.Entry collection : this.dbStruct.entries()){
            for (String key:keyList) {
                if(key == collection.getKey()){
                    res.put(collection.getKey().toString(),collection.getValue().toString());
                }
            }
        }
        return res;
    }
    public String get(String keyName){
        for (Map.Entry collection : this.dbStruct.entries()){
                if(keyName == collection.getKey()) {
                    return collection.getValue().toString();
                }
        }
        return null;
    }
    public Map<String,String> get(){
        Map<String,String> res = new HashMap<>();
        for (Map.Entry collection : this.dbStruct.entries()){
            res.put(collection.getKey().toString(),collection.getValue().toString());
        }
        return res;
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
    //getset

    public Multimap<String, String> getDbStruct() {
        return dbStruct;
    }
    public void setDbStruct(Multimap<String, String> dbStruct) {
        this.dbStruct = dbStruct;
    }
}

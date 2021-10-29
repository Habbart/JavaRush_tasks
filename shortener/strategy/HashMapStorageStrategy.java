package com.javarush.task.task33.task3310.strategy;
import java.util.*;

public class HashMapStorageStrategy implements StorageStrategy {
    private HashMap<Long, String> data = new HashMap<>();
    
    public boolean containsKey(Long key){
        return data.containsKey(key);
    }
    public boolean containsValue(String value){
        return data.containsValue(value);
    }
    public void put(Long key, String value){
        data.put(key, value);
    }
    public Long getKey(String value){
        Long result = null;
        for(Map.Entry <Long, String> item : data.entrySet()){
            if(item.getValue().equals(value)){
                result = item.getKey();
            }
        }
        return result;
    }
    public String getValue(Long key){
        return data.get(key);
    }
}
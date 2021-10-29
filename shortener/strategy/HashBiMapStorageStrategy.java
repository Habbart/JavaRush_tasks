package com.javarush.task.task33.task3310.strategy;

import com.google.common.collect.HashBiMap;


public class HashBiMapStorageStrategy implements StorageStrategy{
    HashBiMap data;

    public HashBiMapStorageStrategy() {
        data = HashBiMap.create();
    }

    @Override
    public boolean containsKey(Long key) {
        return  data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
            data.put(key, value);
            data.put(value, key);
    }

    @Override
    public Long getKey(String value) {
        data.inverse();
        return (Long) data.get(value);
    }

    @Override
    public String getValue(Long key) {
        return (String)data.get(key);
    }
}

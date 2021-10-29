package com.javarush.task.task33.task3310;


import com.javarush.task.task33.task3310.strategy.*;

import java.util.*;

public class Solution {



    public static void main(String[] args){
            testStrategy(new HashMapStorageStrategy(), 10000);
            testStrategy(new OurHashMapStorageStrategy(), 10000);
            testStrategy(new FileStorageStrategy(), 10);
            testStrategy(new OurHashBiMapStorageStrategy(), 10000);
            testStrategy(new HashBiMapStorageStrategy(), 10000);
            testStrategy(new DualHashBidiMapStorageStrategy(), 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> result = new HashSet<>();
        for (String s: strings) {
            result.add(shortener.getId(s));
        }
        return result;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> result = new HashSet<>();
        for (Long l: keys) {
            result.add(shortener.getString(l));
        }
        return result;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        String strategyName = strategy.getClass().getSimpleName();
        Helper.printMessage(strategyName);
        Set<String> testSet = new HashSet<>();
        for (long i = 0; i < elementsNumber; i++) {
                testSet.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        long start = new Date().getTime();
        Set<Long> keys = getIds(shortener, testSet);
        long end = new Date().getTime();
        Helper.printMessage(String.valueOf(end  - start));
        start = new Date().getTime();
        Set<String> testResult = getStrings(shortener, keys);
        end = new Date().getTime();
        Helper.printMessage(String.valueOf(end  - start));

        if(testSet.containsAll(testResult)){
                Helper.printMessage("Тест пройден.");
        } else{
                Helper.printMessage("Тест не пройден.");
            }


    }
}
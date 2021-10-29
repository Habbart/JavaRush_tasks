package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class CurrencyManipulatorFactory {

    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        if(map.get(currencyCode) != null) {
            return map.get(currencyCode);
        } else {
            CurrencyManipulator currencyManipulator = new CurrencyManipulator(currencyCode);
            map.put(currencyCode, currencyManipulator);
            return currencyManipulator;
        }
    }
    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        return new HashSet<>(map.values());
    }

}

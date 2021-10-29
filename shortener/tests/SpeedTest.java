package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        long start = new Date().getTime();
        for (String s :
                strings) {
            ids.add(shortener.getId(s));
        }
        long end = new Date().getTime();
        long result = end - start;
        return result;
    }

    public long getTimeToGetStrings(Shortener shortener,Set<Long> ids, Set<String> strings){
        long start = new Date().getTime();
        for (Long i :
                ids) {
            strings.add(shortener.getString(i));
        }
        long end = new Date().getTime();
        long result = end - start;
        return result;
    }

    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        Set<Long> ids = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        long timeForShortener1, timeForShortener2;
        timeForShortener1 = getTimeToGetIds(shortener1, origStrings, ids );
        timeForShortener2 = getTimeToGetIds(shortener2, origStrings, ids );
        Assert.assertTrue(timeForShortener1 > timeForShortener2);
        timeForShortener1 = getTimeToGetStrings(shortener1, ids, origStrings);
        timeForShortener2 = getTimeToGetStrings(shortener2, ids, origStrings);
        Assert.assertEquals(0, 0, 30);
       // System.out.println(timeForShortener1 - timeForShortener2);

    }
}

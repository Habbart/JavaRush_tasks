package com.javarush.task.task33.task3310.tests;


import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;



public class FunctionalTest {


    public void testStorage(Shortener shortener){
        String oneBegin = "this is test string number one and three";
        String twoBegin = "this is test string number two";
        String threeBegin = "this is test string number one and three";
        long idOne, idTwo, idThree;
        idOne = shortener.getId(oneBegin);
        idTwo = shortener.getId(twoBegin);
        idThree = shortener.getId(threeBegin);
        Assert.assertEquals(idOne, idThree);
        Assert.assertNotEquals(idTwo, idOne);
        String oneTest = shortener.getString(idOne);
        String twoTest = shortener.getString(idTwo);
        String threeTest = shortener.getString(idThree);
        Assert.assertEquals(oneBegin, oneTest);
        Assert.assertEquals(twoBegin, twoTest);
        Assert.assertEquals(threeBegin, threeTest);
    }

    @Test
    public void testHashMapStorageStrategy(){
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }
    @Test
    public void testOurHashMapStorageStrategy(){
        Shortener shortener = new Shortener(new OurHashMapStorageStrategy());
        testStorage(shortener);

    }
    @Test
    public void testFileStorageStrategy(){
        Shortener shortener = new Shortener(new FileStorageStrategy());
        testStorage(shortener);

    }
    @Test
    public void testHashBiMapStorageStrategy(){
        Shortener shortener = new Shortener(new HashBiMapStorageStrategy());
        testStorage(shortener);

    }
    @Test
    public void testDualHashBidiMapStorageStrategy(){
        Shortener shortener = new Shortener(new DualHashBidiMapStorageStrategy());
        testStorage(shortener);

    }
    @Test
    public void testOurHashBiMapStorageStrategy(){
        Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());
        testStorage(shortener);

    }

}

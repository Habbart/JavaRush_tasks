package com.javarush.task.task33.task3310;
import java.security.SecureRandom;
import java.math.BigInteger;

public class Helper {

    public static String generateRandomString(){
        return new BigInteger(150, new SecureRandom()).toString(36);
        }
    
    public static void printMessage(String message){
        System.out.println(message);
    }
}

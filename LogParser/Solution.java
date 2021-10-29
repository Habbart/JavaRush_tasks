package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;

//D:\JAVA POG\JavaRushTasks\4.JavaCollections\src\com\javarush\task\task39\task3913\logs\example.log

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("D:\\JAVA POG\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task39\\task3913\\logs"));

        System.out.println(logParser.execute("get event for date = \"30.08.2012 16:08:13\""));






    }
}
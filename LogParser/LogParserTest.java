package com.javarush.task.task39.task3913;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LogParserTest {


    public void testLogparser(LogParser logParser) {
        String test1 = "get user for date = \"30.08.2012 16:08:13\""; //Amigo
        String test2 = "get ip for user = \"Eduard Petrovich Morozko\""; // 146.34.15.5
        String test3 = "get status for date = \"30.01.2014 12:56:22\""; // OK
        String test4 = "get event for date = \"14.11.2015 07:08:01\""; // WRITE_MESSAGE
        String test5 = "get date for user = \"Amigo\""; // 30.08.2012 16:08:13 21.10.2021 19:45:25   29.2.2028 5:4:7
        String test6 = "get user for ip = \"127.0.0.1\""; // Amigo   Vasya Pupkin
        String test7 = "get ip for status = \"ERROR\""; // 192.168.100.2
        String test8 = "get ip for event = \"SOLVE_TASK \""; // 192.168.100.2   12.12.12.12 120.120.120.122
        String test9 = "get user for status = \"FAILED\""; //Eduard Petrovich Morozko
        String test10 = "get event for user = \"Amigo\""; //LOGIN   WRITE_MESSAGE   SOLVE_TASK

        Set<Object> test1Set = new HashSet<>();
        Set<Object> test2Set = new HashSet<>();
        Set<Object> test3Set = new HashSet<>();
        Set<Object> test4Set = new HashSet<>();
        Set<Object> test5Set = new HashSet<>();
        Set<Object> test6Set = new HashSet<>();
        Set<Object> test7Set = new HashSet<>();
        Set<Object> test8Set = new HashSet<>();
        Set<Object> test9Set = new HashSet<>();
        Set<Object> test10Set = new HashSet<>();

        Set<Object> test1SetResult = logParser.execute(test1);
        Set<Object> test2SetResult = logParser.execute(test2);
        Set<Object> test3SetResult = logParser.execute(test3);
        Set<Object> test4SetResult = logParser.execute(test4);
        Set<Object> test5SetResult = logParser.execute(test5);
        Set<Object> test6SetResult = logParser.execute(test6);
        Set<Object> test7SetResult = logParser.execute(test7);
        Set<Object> test8SetResult = logParser.execute(test8);
        Set<Object> test9SetResult = logParser.execute(test9);
        Set<Object> test10SetResult = logParser.execute(test10);

        test1Set.add("Amigo");
        test2Set.add("146.34.15.5");
        test2Set.add("127.0.0.1");
        test3Set.add(Status.ERROR);
        test4Set.add(Event.WRITE_MESSAGE);
        test5Set.add(new Date(2012, Calendar.AUGUST, 30, 16, 8, 13));// 30.08.2012 16:08:13
        test5Set.add(new Date(121, Calendar.OCTOBER, 21, 19, 45, 25)); //21.10.2021 19:45:25
        test5Set.add(new Date(128, Calendar.FEBRUARY, 29, 5, 4, 7));//29.2.2028 5:4:7
        test5Set.add(new Date(113, Calendar.DECEMBER, 11, 10, 11, 12));//11.12.2013 10:11:12
        test6Set.add("Amigo");
        test6Set.add("Vasya Pupkin");
        test7Set.add("192.168.100.2");
        test8Set.add("192.168.100.2");
        test8Set.add("12.12.12.12");
        test8Set.add("120.120.120.122");
        test9Set.add("Eduard Petrovich Morozko");
        test9Set.add("Amigo");
        test10Set.add(Event.LOGIN);
        test10Set.add(Event.WRITE_MESSAGE);
        test10Set.add(Event.SOLVE_TASK);

        Assert.assertEquals(test1Set, test1SetResult);
        Assert.assertEquals(test2Set, test2SetResult);
        Assert.assertEquals(test3Set, test3SetResult);
        Assert.assertEquals(test4Set, test4SetResult);
        Assert.assertEquals(test5Set, test5SetResult);
        Assert.assertEquals(test6Set, test6SetResult);
        Assert.assertEquals(test7Set, test7SetResult);
        Assert.assertEquals(test8Set, test8SetResult);
        Assert.assertEquals(test9Set, test9SetResult);
        Assert.assertEquals(test10Set, test10SetResult);
        Assert.assertNotEquals(test1Set, test2SetResult);
        Assert.assertNotEquals(test4Set, test5SetResult);
        Assert.assertNotEquals(test2Set, test10SetResult);
        Assert.assertNotEquals(test6Set, test7SetResult);
        Assert.assertNotEquals(test4Set, test8SetResult);

    }
    @Test
    public void execute(){
        LogParser logParser = new LogParser(Paths.get("D:\\JAVA POG\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task39\\task3913\\logs"));
        testLogparser(logParser);
    }

}
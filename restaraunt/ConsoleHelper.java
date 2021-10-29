package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return bufferedReader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> result = new ArrayList<>();
        writeMessage("Please enter the dish or enter \"exit\" to complete the order");
        System.out.println(Dish.allDishesToString());
        while (true) {

            String dish = readString();
            if (dish.equalsIgnoreCase("exit")) return result;
            switch (dish.toLowerCase()) {
                case "fish":
                    result.add(Dish.FISH);
                    ConsoleHelper.writeMessage(dish + " was added succesfuly.");
                    break;
                case "juice":
                    result.add(Dish.JUICE);
                    ConsoleHelper.writeMessage(dish + " was added succesfuly.");
                    break;
                case "soup":
                    result.add(Dish.SOUP);
                    ConsoleHelper.writeMessage(dish + " was added succesfuly.");
                    break;
                case "steak":
                    result.add(Dish.STEAK);
                    ConsoleHelper.writeMessage(dish + " was added succesfuly.");
                    break;
                case "water":
                    result.add(Dish.WATER);
                    ConsoleHelper.writeMessage(dish + " was added succesfuly.");
                    break;
                default:
                    writeMessage("Sorry, we don't have this dish in our menu.");
                    break;
            }
        }
    }
}

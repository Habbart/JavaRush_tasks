package com.javarush.task.task27.task2712.kitchen;

import java.util.Observer;
import java.util.Observable;
import com.javarush.task.task27.task2712.ConsoleHelper;

public class Waiter implements Observer {
    
    @Override
    public void update(Observable cook, Object order) {
        String message = order + " was cooked by " + cook;
        ConsoleHelper.writeMessage(message);
    }
}

// Please choose a dish from the list:FISH, STEAK, SOUP, JUICE, WATER
//  or type 'exit' to complete the order
// FISH has been successfully added to your order
// JUICE has been successfully added to your order
// Your order: [FISH, JUICE] of Tablet{number=5}
// Start cooking - Your order: [FISH, JUICE] of Tablet{number=5}
// Your order: [FISH, JUICE] of Tablet{number=5} was cooked by Amigo
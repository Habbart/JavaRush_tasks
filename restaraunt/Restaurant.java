package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.*;
import java.util.Observable;
import java.util.Observer;

public class Restaurant {

    public static void main(String[] args) {
        Tablet tablet = new Tablet(5);
        Cook cook = new Cook("Grigory");
        tablet.addObserver(cook);
        cook.addObserver(new Waiter());
        tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();

    }

}

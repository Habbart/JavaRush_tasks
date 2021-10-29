package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Order {

    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public int getTotalCookingTime(){
        return dishes.stream().mapToInt(Dish::getDuration).sum();
    }

    public boolean isEmpty(){
        return dishes.isEmpty();
    }

    @Override
    public String toString() {
        if(isEmpty()){
            return "";
        }
        StringBuilder sb = new StringBuilder("Your order: [");
        for (Dish s :
                dishes) {
            sb.append(s.toString()).append(", ");
        }
        String result = sb.substring(0, sb.lastIndexOf(",")) + "]";
        result = result + " of " + tablet.toString() + String.format(", cooking time %dmin", getTotalCookingTime());
        
        return result;
    }
}

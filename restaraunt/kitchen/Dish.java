package com.javarush.task.task27.task2712.kitchen;

public enum Dish {
    FISH (25),
    STEAK(30),
    SOUP(15),
    JUICE(5),
    WATER(3);

    private int duration;



    public static String allDishesToString(){
        int i= 0;
        StringBuilder sb = new StringBuilder();
        while(i < Dish.values().length){
            sb.append(Dish.values()[i]).append(", ");
            i++;
        }
        return sb.substring(0, sb.lastIndexOf(","));
    }

    Dish(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
}

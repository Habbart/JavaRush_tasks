package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable{
    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(this.x, this.y, this.width, this.height);
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public Box(int x, int y) {
        super(x, y);
    }
}

package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Home extends GameObject{


    public Home(int x, int y) {
        super(x, y);
        this.height = Model.FIELD_CELL_SIZE;
        this.width = Model.FIELD_CELL_SIZE;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillOval(x, y, width, height);
    }
}

package com.javarush.task.task34.task3410.model;

import java.awt.*;

public abstract class GameObject {
    protected int x, y, width, height;
    
    public GameObject(int x, int y){
        this.x = x;
        this.y = y;
        width = Model.FIELD_CELL_SIZE;
        height = Model.FIELD_CELL_SIZE;
    }
    public GameObject(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height){
        this.height = height;
    }
    
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
    
    
    public abstract void draw(Graphics graphics);
}
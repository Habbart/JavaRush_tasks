package com.javarush.task.task25.task2515;


public class SpaceShip extends BaseObject {
    
    private static int[][] matrix = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1},
    };
    private double dx;

    public SpaceShip(double x, double y){
        super(x, y, 3);
    }
    public void moveLeft(){
        dx = -1;
    }
    public void moveRight(){
        dx = 1;
    }
    @Override
    public void move(){
        x = x + dx; 
        this.checkBorders(0, Space.game.getWidth() - radius + 1, 0, Space.game.getHeight()- radius + 1);
    }
    @Override
    public void draw(Canvas canvas){
       canvas.drawMatrix(x - radius + 1, y - radius + 1, matrix, 'M');
    }
    public void fire(){
        Rocket rocketLeft = new Rocket(x - 2, y);
        Rocket rocketRight = new Rocket(x + 2, y);
        Space.game.getRockets().add(rocketLeft);
        Space.game.getRockets().add(rocketRight);
    }
}
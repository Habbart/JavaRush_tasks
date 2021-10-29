package com.javarush.task.task34.task3410.model;



public abstract class CollisionObject extends GameObject {

    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction){
        int move = Model.FIELD_CELL_SIZE;

        switch (direction){
            case UP: return ((gameObject.getX() == x) && (gameObject.getY() == y - move));
            case DOWN: return (gameObject.getX() == x) && (gameObject.getY()  == (y + move));
            case LEFT: return (gameObject.getX()  == (x - move)) && (gameObject.getY() == y);
            case RIGHT: return(gameObject.getX()  == (x + move)) && (gameObject.getY() == y);
        }
        return false;
    }


}

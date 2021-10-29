package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;



public class Model {
    private EventListener eventListener;
    public static final int FIELD_CELL_SIZE = 20;
    GameObjects gameObjects;
    int currentLevel = 60;
    String path = "D:\\JAVA POG\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task34\\task3410\\res\\levels.txt";
    LevelLoader levelLoader = new LevelLoader(Paths.get(path));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return this.gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        if(currentLevel > 60) currentLevel = 1;
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restartLevel(currentLevel);
    }

    public void move(Direction direction) {
        Player player = gameObjects.player;
        if (checkWallCollision(player, direction)) return;
        if (checkBoxCollisionAndMoveIfAvailable(direction)) return;
        moveObject(player, direction);
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall :
                gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        Player player = gameObjects.player;
        for (Box box :
                gameObjects.getBoxes()) {
            if (player.isCollision(box, direction)) {
                if (checkWallCollision(box, direction)) return true;
                for (Box checkBox :
                        gameObjects.getBoxes()) {
                    if (box == checkBox) {
                        continue;
                    }
                    if (box.isCollision(checkBox, direction)) return true;
                }
                moveObject(box, direction);
            }
        }
        return false;
    }

    public void checkCompletion() {
        for (Home home :
                gameObjects.getHomes()) {
            boolean hasBox = false;
            for (Box box :
                    gameObjects.getBoxes()) {
                if (box.getX() == home.getX() && box.getY() == home.getY()) {
                    hasBox = true;
                }
            }
            if(!hasBox) return;
        }
        eventListener.levelCompleted(currentLevel);
    }

    public void moveObject(GameObject gameObject, Direction direction) {
        switch (direction) {
            case RIGHT:
                gameObject.setX(gameObject.getX() + Model.FIELD_CELL_SIZE);
                break;
            case LEFT:
                gameObject.setX(gameObject.getX() - Model.FIELD_CELL_SIZE);
                break;
            case UP:
                gameObject.setY(gameObject.getY() - Model.FIELD_CELL_SIZE);
                break;
            case DOWN:
                gameObject.setY(gameObject.getY() + Model.FIELD_CELL_SIZE);
                break;
        }
    }
}

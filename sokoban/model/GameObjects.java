package com.javarush.task.task34.task3410.model;

import java.util.HashSet;
import java.util.Set;

public class GameObjects {
    protected Set<Wall> walls;
    protected Set<Box> boxes;
    protected Set<Home> homes;
    protected Player player;

    public Set<Wall> getWalls() {
        return walls;
    }

    public Set<Box> getBoxes() {
        return boxes;
    }

    public Set<Home> getHomes() {
        return homes;
    }

    public Player getPlayer() {
        return player;
    }

    public GameObjects(Set<Wall> walls, Set<Box> boxes, Set<Home> homes, Player player) {
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;
    }
    public Set<GameObject> getAll(){
        Set<GameObject> result = new HashSet<>();
        result.addAll(getBoxes());
        result.addAll(getHomes());
        result.add(player);
        result.addAll(getWalls());
        return result;
    }
}

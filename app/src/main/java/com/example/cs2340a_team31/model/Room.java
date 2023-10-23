package com.example.cs2340a_team31.model;

import java.util.ArrayList;

public class Room {
    private ArrayList<Wall> walls;

    public Room() {
        walls = new ArrayList<Wall>();
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public void addWall(Wall wall) {
        walls.add(wall);
    }
}

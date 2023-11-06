package com.example.cs2340a_team31.model;

import com.example.cs2340a_team31.model.enemyFactoryPattern.Enemy;

import java.util.ArrayList;

public class Room {
    private ArrayList<Wall> walls;

    private ArrayList<Enemy> enemies;

    public Room() {
        walls = new ArrayList<Wall>();
        enemies = new ArrayList<Enemy>();
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public void addWall(Wall wall) {
        walls.add(wall);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }
}

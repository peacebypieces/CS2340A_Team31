package com.example.cs2340a_team31.model;

import com.example.cs2340a_team31.model.decorator.PowerUp;
import com.example.cs2340a_team31.model.enemyFactoryPattern.Enemy;

import java.util.ArrayList;

public class Room {
    private ArrayList<Wall> walls;

    private ArrayList<Enemy> enemies;

    private ArrayList<PowerUp> powerUps;

    public Room() {
        walls = new ArrayList<Wall>();
        enemies = new ArrayList<Enemy>();
        powerUps = new ArrayList<PowerUp>();
    }


    public ArrayList<PowerUp> getPowerUps() { return powerUps; }

    public void addPowerUps(PowerUp powerUp, double x, double y) {
        powerUp.setLocation(x, y);
        powerUps.add(powerUp);
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

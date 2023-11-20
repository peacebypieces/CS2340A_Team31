package com.example.cs2340a_team31.model.enemyFactoryPattern;

import com.example.cs2340a_team31.model.Player;

public class DawgEnemy extends Enemy {
    DawgEnemy(double w, double h, double difficulty, String direction) {
        setSize(10 * w, 12 * h);
        setMovementSpeed(w / 10);
        setDamage(difficulty * 25.0); // Scales the damage based on difficulty
        setHealth(difficulty * 200.0);
        setAlive(true);
        setType("dawg");
        setDirection(direction);
    }

    @Override
    public void checkCollision(Player player) {
        super.checkCollision(player);
    }
}

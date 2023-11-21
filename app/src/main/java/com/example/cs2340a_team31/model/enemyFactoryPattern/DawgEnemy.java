package com.example.cs2340a_team31.model.enemyFactoryPattern;

import com.example.cs2340a_team31.model.Player;

/**
 * DawgEnemy is a type of Enemy in the game. It extends the Enemy class and
 * has specific attributes such as size, movement speed, damage, health,
 * type, and direction.
 */
public class DawgEnemy extends Enemy {
    DawgEnemy(double w, double h, double difficulty, String direction, int steps) {
        setSize(10 * w, 12 * h);
        setMovementSpeed(w / 10);
        setDamage(difficulty * 25.0); // Scales the damage based on difficulty
        setHealth(difficulty * 200.0);
        setAlive(true);
        setType("dawg");
        setMovementCounterCap(steps);
        setDirection(direction);
    }

    @Override
    public void checkCollision(Player player) {
        super.checkCollision(player);
    }
}

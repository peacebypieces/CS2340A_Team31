package com.example.cs2340a_team31.model.enemyFactoryPattern;

import com.example.cs2340a_team31.model.Player;

/**
 * DawgEnemy is a type of Enemy in the game. It extends the Enemy class and
 * has specific attributes such as size, movement speed, damage, health,
 * type, and direction.
 */
public class DawgEnemy extends Enemy {

     // Constructs a new DawgEnemy with the specified width, height, difficulty, and direction.

    //  w The width of the DawgEnemy.
    // h The height of the DawgEnemy.
    //difficulty The difficulty level of the DawgEnemy.
    //  direction The direction of the DawgEnemy.

    DawgEnemy(double w, double h, double difficulty, String direction) {
        setSize(10 * w, 12 * h);
        setMovementSpeed(w / 10);
        setDamage(difficulty * 25.0); // Scales the damage based on difficulty
        setHealth(0);
        setAlive(true);
        setType("dawg");
        setDirection(direction);
    }

    @Override
    public void checkCollision(Player player) {
        super.checkCollision(player);
    }
}

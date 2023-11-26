package com.example.cs2340a_team31.model.enemyFactoryPattern;

import com.example.cs2340a_team31.model.Player;

/**
 * Represents a specific type of enemy - MiceEnemy.
 * Extends the Enemy class.
 */
public class MiceEnemy extends Enemy {
    /**
     * Constructor for the MiceEnemy class.
     *
     * @param w          Width of the enemy.
     * @param h          Height of the enemy.
     * @param difficulty Difficulty level affecting enemy attributes.
     * @param direction  Initial direction of movement.
     * @param steps      Number of steps the enemy can take.
     */
    MiceEnemy(double w, double h, double difficulty, String direction, int steps) {
        setSize(1.5 * w, 1.5 * h);
        setMovementSpeed(w / 4);
        setDamage(difficulty * 5.0); // Scales the damage based on difficulty
        setHealth(difficulty * 10.0);
        setEnemyPoint(difficulty * 10.0);
        setAlive(true);
        setType("mice");
        setMovementCounterCap(steps);
        setDirection(direction);
    }

    /**
     * Overrides the checkCollision method to handle collision detection with the player.
     *
     * @param player The player object for collision detection.
     */
    @Override
    public void checkCollision(Player player) {
        super.checkCollision(player);
    }
}

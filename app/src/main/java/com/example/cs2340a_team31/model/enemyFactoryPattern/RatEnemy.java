package com.example.cs2340a_team31.model.enemyFactoryPattern;

import com.example.cs2340a_team31.model.Player;
/**
 * This class represents a RatEnemy in the game. It extends the Enemy class.
 * The RatEnemy has specific characteristics such as size,
 * movement speed, damage, health, and enemy points.
 */
public class RatEnemy extends Enemy {
    /**
     * Constructs a new RatEnemy object.
     *
     * @param w          The width of the enemy.
     * @param h          The height of the enemy.
     * @param difficulty The difficulty level of the enemy.
     * @param direction  The direction of the enemy's movement.
     * @param steps      The number of steps the enemy can take.
     */
    RatEnemy(double w, double h, double difficulty, String direction, int steps) {
        setSize(2 * w, 2 * h);
        setMovementSpeed(w / 4);
        setDamage(difficulty * 10.0); // Scales the damage based on difficulty
        setHealth(difficulty * 25.0);
        setEnemyPoint(difficulty * 25.0);
        setAlive(true);
        setType("rat");
        setMovementCounterCap(steps);
        setDirection(direction);
    }
    /**
     * Checks if the enemy has collided with the player.
     *
     * @param player The player to check collision with.
     */
    @Override
    public void checkCollision(Player player) {
        super.checkCollision(player);
    }
}

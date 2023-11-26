package com.example.cs2340a_team31.model.enemyFactoryPattern;

/**
 * Represents a factory that creates different types of enemies for a space-themed game.
 * Extends the EnemyFactory class.
 */
public class SpaceEnemyFactory extends EnemyFactory {
    /**
     * Creates an enemy based on the provided parameters.
     *
     * @param item       Type of enemy to create (e.g., "mice", "rat", "dog", "dawg").
     * @param w          Width of the enemy.
     * @param h          Height of the enemy.
     * @param difficulty Difficulty level affecting enemy attributes.
     * @param direction  Initial direction of movement.
     * @param steps      Number of steps the enemy can take.
     * @return An instance of the specific enemy type based on the provided parameters.
     */
    Enemy createEnemy(String item, double w, double h, double difficulty,
                      String direction, int steps) {
        switch (item) {
        case "mice":
            return new MiceEnemy(w, h, difficulty, direction, steps);
        case "rat":
            return new RatEnemy(w, h, difficulty, direction, steps);
        case "dog":
            return new DogEnemy(w, h, difficulty, direction, steps);
        case "dawg":
            return new DawgEnemy(w, h, difficulty, direction, steps);
        default:
            return null;
        }
    }
}

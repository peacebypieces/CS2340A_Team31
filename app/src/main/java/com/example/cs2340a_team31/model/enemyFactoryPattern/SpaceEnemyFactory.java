package com.example.cs2340a_team31.model.enemyFactoryPattern;

public class SpaceEnemyFactory extends EnemyFactory {

    Enemy createEnemy(String item, double w, double h, double difficulty, String direction, int steps) {
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

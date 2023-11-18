package com.example.cs2340a_team31.model.enemyFactoryPattern;

public class SpaceEnemyFactory extends EnemyFactory {

    Enemy createEnemy(String item, double w, double h, double difficulty, String direction) {
        switch (item) {
        case "mice":
            return new MiceEnemy(w, h, difficulty, direction);
        case "rat":
            return new RatEnemy(w, h, difficulty, direction);
        case "dog":
            return new DogEnemy(w, h, difficulty, direction);
        case "dawg":
            return new DawgEnemy(w, h, difficulty, direction);
        default:
            return null;
        }
    }
}

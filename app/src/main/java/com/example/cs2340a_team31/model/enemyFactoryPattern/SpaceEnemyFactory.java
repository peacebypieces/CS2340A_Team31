package com.example.cs2340a_team31.model.enemyFactoryPattern;

public class SpaceEnemyFactory extends EnemyFactory {

    Enemy createEnemy(String item, double w, double h) {
        switch (item) {
            case "mice":
                return new MiceEnemy(w, h);
            case "rat":
                return new RatEnemy(w, h);
            case "dog":
                return new DogEnemy(w, h);
            case "dawg":
                return new DawgEnemy(w, h);
            default:
                return null;
        }
    }
}

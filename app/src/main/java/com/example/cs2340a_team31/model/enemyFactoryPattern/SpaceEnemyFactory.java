package com.example.cs2340a_team31.model.enemyFactoryPattern;

import android.content.Context;

public class SpaceEnemyFactory extends EnemyFactory {

    Enemy createEnemy(String item, double w, double h, double difficulty) {
        switch (item) {
            case "mice":
                return new MiceEnemy(w, h, difficulty);
            case "rat":
                return new RatEnemy(w, h, difficulty);
            case "dog":
                return new DogEnemy(w, h, difficulty);
            case "dawg":
                return new DawgEnemy(w, h, difficulty);
            default:
                return null;
        }
    }
}

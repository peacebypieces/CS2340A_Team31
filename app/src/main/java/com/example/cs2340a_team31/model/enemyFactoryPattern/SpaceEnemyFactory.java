package com.example.cs2340a_team31.model.enemyFactoryPattern;

import android.content.Context;

public class SpaceEnemyFactory extends EnemyFactory {

    Enemy createEnemy(String item, double w, double h, Context c) {
        switch (item) {
            case "mice":
                return new MiceEnemy(w, h, c);
            case "rat":
                return new RatEnemy(w, h, c);
            case "dog":
                return new DogEnemy(w, h, c);
            case "dawg":
                return new DawgEnemy(w, h, c);
            default:
                return null;
        }
    }
}

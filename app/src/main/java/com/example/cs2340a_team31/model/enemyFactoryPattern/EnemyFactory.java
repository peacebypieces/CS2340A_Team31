package com.example.cs2340a_team31.model.enemyFactoryPattern;

import android.content.Context;

public abstract class EnemyFactory {
    public Enemy spawnEnemy(String type, double x, double y, double difficulty, double w, double h) {
        Enemy enemy;
        enemy = createEnemy(type, w, h, difficulty);
        enemy.setLocation(x, y);
        return enemy;
    }

    //factory method
    abstract Enemy createEnemy(String type, double w, double h, double difficulty);
}


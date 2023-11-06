package com.example.cs2340a_team31.model.enemyFactoryPattern;

public abstract class EnemyFactory {
    public Enemy spawnEnemy(String type, double x, double y, String direction, double w, double h) {
        Enemy enemy;
        enemy = createEnemy(type, w, h);
        enemy.setLocation(x, y);
        enemy.setDirection(direction);
        return enemy;
    }

    //factory method
    abstract Enemy createEnemy(String type, double w, double h);
}


package com.example.cs2340a_team31.model.enemyFactoryPattern;

public abstract class EnemyFactory {
    public Enemy spawnEnemy(String type, double x, double y, double difficulty, double w,
                            double h, String direction) {
        Enemy enemy;
        enemy = createEnemy(type, w, h, difficulty, direction);
        enemy.setLocation(x, y);
        return enemy;
    }

    //factory method
    abstract Enemy createEnemy(String type, double w, double h, double difficulty,
                               String direction);
}


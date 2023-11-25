package com.example.cs2340a_team31.model.enemyFactoryPattern;

public abstract class EnemyFactory {
    public Enemy spawnEnemy(String type, double[] rect,
                            double difficulty, String direction, int steps) {
        Enemy enemy;
        enemy = createEnemy(type, rect[2], rect[3], difficulty, direction, steps);
        enemy.setLocation(rect[0], rect[1]);
        return enemy;
    }

    //factory method
    abstract Enemy createEnemy(String type, double w, double h, double difficulty,
                               String direction, int steps);
}


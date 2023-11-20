package com.example.cs2340a_team31.model.enemyFactoryPattern;

import com.example.cs2340a_team31.model.Player;

public class DogEnemy extends Enemy {
    DogEnemy(double w, double h, double difficulty, String direction) {
        setSize(2 * w, 2 * h);
        setMovementSpeed(h / 4);
        setDamage(difficulty * 15.0); // Scales the damage based on difficulty
        setHealth(difficulty * 50.0);
        setAlive(true);
        setType("dog");
        setDirection(direction);
    }

    @Override
    public void checkCollision(Player player) {
        super.checkCollision(player);
    }
}

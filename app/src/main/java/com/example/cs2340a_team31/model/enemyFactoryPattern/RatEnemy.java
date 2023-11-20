package com.example.cs2340a_team31.model.enemyFactoryPattern;

import com.example.cs2340a_team31.model.Player;

public class RatEnemy extends Enemy {
    RatEnemy(double w, double h, double difficulty, String direction) {
        setSize(2 * w, 2 * h);
        setMovementSpeed(w / 4);
        setDamage(difficulty * 10.0); // Scales the damage based on difficulty
        setHealth(difficulty * 25.0);
        setAlive(true);
        setType("rat");
        setDirection(direction);
    }

    @Override
    public void checkCollision(Player player) {
        super.checkCollision(player);
    }
}

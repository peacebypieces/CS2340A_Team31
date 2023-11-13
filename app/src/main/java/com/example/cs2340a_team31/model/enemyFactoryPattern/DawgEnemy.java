package com.example.cs2340a_team31.model.enemyFactoryPattern;

import com.example.cs2340a_team31.model.Player;

public class DawgEnemy extends Enemy {
    DawgEnemy(double w, double h, double difficulty, String direction) {
        setSize(10 * w, 12 * h);
        movementSpeed = w / 10;
        damage = difficulty * 25.0; // Scales the damage based on difficulty
        health = 0.0;
        alive = true;
        type = "dawg";
        this.direction = direction;
    }

    @Override
    public void checkCollision(Player player) {
        super.checkCollision(player);
    }
}

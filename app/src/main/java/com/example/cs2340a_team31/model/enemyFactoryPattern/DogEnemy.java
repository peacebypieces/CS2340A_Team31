package com.example.cs2340a_team31.model.enemyFactoryPattern;

import com.example.cs2340a_team31.model.Player;

public class DogEnemy extends Enemy {
    DogEnemy(double w, double h, double difficulty, String direction) {
        setSize(2 * w, 2 * h);
        movementSpeed = h / 4;
        damage = difficulty * 15.0; // Scales the damage based on difficulty
        health = 0.0;
        alive = true;
        type = "dog";
        this.direction = direction;
    }

    @Override
    public void checkCollision(Player player) {
        super.checkCollision(player);
    }
}

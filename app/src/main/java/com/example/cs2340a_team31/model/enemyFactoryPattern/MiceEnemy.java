package com.example.cs2340a_team31.model.enemyFactoryPattern;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.cs2340a_team31.R;
import com.example.cs2340a_team31.model.Player;

public class MiceEnemy extends Enemy {
    MiceEnemy(double w, double h, double difficulty, String direction) {
        setSize(1.5 * w, 1.5 * h);
        movementSpeed = w/4;
        damage = difficulty * 5.0; // Scales the damage based on difficulty
        health = 0.0;
        alive = true;
        type = "mice";
        this.direction = direction;
    }

    @Override
    public void checkCollision(Player player) {
        super.checkCollision(player);
    }
}

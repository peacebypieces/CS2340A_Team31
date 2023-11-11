package com.example.cs2340a_team31.model.enemyFactoryPattern;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.cs2340a_team31.R;
import com.example.cs2340a_team31.model.Player;

public class RatEnemy extends Enemy {
    RatEnemy(double w, double h, double difficulty) {
        setSize(2 * w, 2 * h);
        movementSpeed = w/4;
        damage = difficulty * 10.0; // Scales the damage based on difficulty
        health = 0.0;
        alive = true;
        type = "rat";
    }

    @Override
    public void checkCollision(Player player) {
        super.checkCollision(player);
    }
}

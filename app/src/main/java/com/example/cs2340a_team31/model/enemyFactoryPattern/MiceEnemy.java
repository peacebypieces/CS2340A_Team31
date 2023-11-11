package com.example.cs2340a_team31.model.enemyFactoryPattern;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.cs2340a_team31.R;
import com.example.cs2340a_team31.model.Player;

public class MiceEnemy extends Enemy {
    MiceEnemy(double w, double h, Context c) {
        // TODO: Set enemy stats
        setSize(1.5 * w, 1.5 * h);
        movementSpeed = w/4;
        damage = 0.0;
        health = 0.0;
        alive = true;
        type = "mice";
    }

    @Override
    public void updatePlayerPosition(double playerX, double playerY) {
        super.updatePlayerPosition(playerX, playerY);
    }

    @Override
    public void checkCollision(Player player) {
        super.checkCollision(player);
    }
}

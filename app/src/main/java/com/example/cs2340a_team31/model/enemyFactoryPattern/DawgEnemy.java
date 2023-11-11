package com.example.cs2340a_team31.model.enemyFactoryPattern;

import android.app.Activity;
import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.cs2340a_team31.R;
import com.example.cs2340a_team31.model.Player;

public class DawgEnemy extends Enemy {
    DawgEnemy(double w, double h, Context c) {
        // TODO: Set enemy stats
        setSize(10 * w, 12 * h);
        movementSpeed = w/10;
        damage = 0.0;
        health = 0.0;
        alive = true;
        type = "dawg";
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

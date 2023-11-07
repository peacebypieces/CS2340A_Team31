package com.example.cs2340a_team31.model.enemyFactoryPattern;

import android.content.Context;

import com.example.cs2340a_team31.model.Player;

public class RatEnemy extends Enemy {
    RatEnemy(double w, double h, Context c) {
        // TODO: Set enemy stats
        setSize(0.0 * w, 0.0 * h);
        movementSpeed = 0.0;
        damage = 0.0;
        sprite = null;
        health = 0.0;
        alive = true;
    }

    @Override
    public void updatePlayerPosition(double playerX, double playerY) {
        // TODO: Make enemy move based on player position
    }

    @Override
    public void checkCollision(Player player) {
        if (false) {
            player.notifyCollision(player, damage);
        }
    }
}

package com.example.cs2340a_team31.model.observers;

import com.example.cs2340a_team31.model.Player;

public interface EnemyObserver {

    void checkCollision(Player player);

    void move();
}

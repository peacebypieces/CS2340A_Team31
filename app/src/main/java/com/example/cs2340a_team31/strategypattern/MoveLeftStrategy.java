package com.example.cs2340a_team31.strategypattern;

import com.example.cs2340a_team31.model.Player;

public class MoveLeftStrategy implements MovementStrategy {
    Player player = Player.getPlayer();

    public void move() {
        player.setX(Math.max(0, player.getX() - player.getMovementSpeed()));
    }
}

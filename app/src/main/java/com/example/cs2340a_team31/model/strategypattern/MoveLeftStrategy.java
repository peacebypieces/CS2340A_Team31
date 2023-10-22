package com.example.cs2340a_team31.model.strategypattern;

import com.example.cs2340a_team31.model.Player;

public class MoveLeftStrategy implements MovementStrategy {


    public void move(float x) {
        Player player = Player.getPlayer();
        player.setX(Math.max(x, player.getX() - player.getMovementSpeed()));
    }
}

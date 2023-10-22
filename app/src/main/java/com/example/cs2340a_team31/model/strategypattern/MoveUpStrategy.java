package com.example.cs2340a_team31.model.strategypattern;

import com.example.cs2340a_team31.model.Player;

public class MoveUpStrategy implements MovementStrategy {


    public void move(float x) {
        Player player = Player.getPlayer();
        player.setY(Math.max(x, player.getY() - player.getMovementSpeed()));
    }
}

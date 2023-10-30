package com.example.cs2340a_team31.model.strategypattern;

import com.example.cs2340a_team31.model.Player;

public class MoveDownStrategy implements MovementStrategy {
    public void move(float x) {
        Player player = Player.getPlayer();
        player.setY(Math.min(player.getY() + player.getMovementSpeedY(), x));
    }
}

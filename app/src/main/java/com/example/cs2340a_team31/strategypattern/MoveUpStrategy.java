package com.example.cs2340a_team31.strategypattern;

import com.example.cs2340a_team31.model.Player;

public class MoveUpStrategy implements MovementStrategy {
    Player player = Player.getPlayer();

    public void move() {
        player.setY(Math.max(0, player.getY() - player.getMovementSpeed()));
    }
}

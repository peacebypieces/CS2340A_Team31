package com.example.cs2340a_team31.strategypattern;

import com.example.cs2340a_team31.model.Player;

public class MoveRightStrategy implements MovementStrategy {
    Player player = Player.getPlayer();

    public void move() {
        player.setX(Math.min(player.getX() + player.getMovementSpeed(), 10000));
    }
}

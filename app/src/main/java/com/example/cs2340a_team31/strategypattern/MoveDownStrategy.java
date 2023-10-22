package com.example.cs2340a_team31.strategypattern;

import com.example.cs2340a_team31.model.Player;

public class MoveDownStrategy implements MovementStrategy {
    Player player = Player.getPlayer();

    public void move() {
        player.setY(Math.min(player.getY() + player.getMovementSpeed(), 10000));
    }
}

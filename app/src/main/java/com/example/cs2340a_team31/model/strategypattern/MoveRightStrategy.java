package com.example.cs2340a_team31.model.strategypattern;

import com.example.cs2340a_team31.model.Player;

public class MoveRightStrategy implements MovementStrategy {


    public void move(float x) {
        Player player = Player.getPlayer();
        player.setX(Math.min(player.getX() + player.getMovementSpeed(), x));
    }
}

package com.example.cs2340a_team31.model.decorator;

import com.example.cs2340a_team31.model.Player;

public class HealthPowerUp extends PowerUp {

    public HealthPowerUp(Player player, double buffAmount, double width, double height) {
        setBuff(buffAmount);
        setSize(width, height);
        setPlayer(player);
        setType("HealthPowerUp");
        setStatus(false);
    }

    @Override
    public void applyPowerUp() {
        double currentHeath = getPlayer().getHealth();
        getPlayer().setHealth(currentHeath * getBuff());
    }


    //check collusions method

}

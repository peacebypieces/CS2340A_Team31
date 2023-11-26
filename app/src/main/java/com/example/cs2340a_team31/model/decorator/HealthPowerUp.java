package com.example.cs2340a_team31.model.decorator;

import com.example.cs2340a_team31.model.Player;

//health power up extending abstract class
public class HealthPowerUp extends PowerUp {

    //constructor creating instance of buff
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




}

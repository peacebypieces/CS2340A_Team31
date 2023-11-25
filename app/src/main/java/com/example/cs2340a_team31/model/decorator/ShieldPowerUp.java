package com.example.cs2340a_team31.model.decorator;

import com.example.cs2340a_team31.model.Player;

public class ShieldPowerUp extends PowerUp {

    public ShieldPowerUp(Player player, double buffAmount, double width, double height) {
        setBuff(buffAmount);
        setSize(width, height);
        setPlayer(player);
        setType("ShieldPowerUp");
        setStatus(false);
    }

    @Override
    public void applyPowerUp() {
        double currentShield = getPlayer().getShield();
        getPlayer().setShield(currentShield * getBuff());
    }


    //check collusions method

}

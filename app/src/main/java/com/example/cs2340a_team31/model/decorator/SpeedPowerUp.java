package com.example.cs2340a_team31.model.decorator;

import com.example.cs2340a_team31.model.Player;

public class SpeedPowerUp extends PowerUp {

    public SpeedPowerUp(Player player, double buffAmount, double width, double height) {
        setBuff(buffAmount);
        setSize(width, height);
        setPlayer(player);
        setType("SpeedPowerUp");
        setStatus(false);
    }

    @Override
    public void applyPowerUp() {
        double currentMovementSpeedX = getPlayer().getMovementSpeedX();
        double currentMovementSpeedY = getPlayer().getMovementSpeedY();
        getPlayer().setMovementSpeed(currentMovementSpeedX * getBuff(), currentMovementSpeedY * getBuff());

    }


    //check collusions method

}

package com.example.cs2340a_team31.model.decorator;

import com.example.cs2340a_team31.model.Player;

public class ShieldPowerUp extends PowerUp {
    /**
     * This class represents a Shield PowerUp in the game. It extends the PowerUp class.
     * When applied, it increases the player's shield by a certain buff amount.
     */

    /**
    * Constructs a new ShieldPowerUp object.
    *
    * @param player     The player to which the power-up is to be applied.
    * @param buffAmount The amount by which the player's shield is to be increased.
    * @param width      The width of the power-up.
    * @param height     The height of the power-up.
    */
    public ShieldPowerUp(Player player, double buffAmount, double width, double height) {
        setBuff(buffAmount);
        setSize(width, height);
        setPlayer(player);
        setType("ShieldPowerUp");
        setStatus(false);
    }

    /**
     * Applies the power-up to the player. This increases the player's shield by the buff amount.
     */
    @Override
    public void applyPowerUp() {
        double currentShield = getPlayer().getShield();
        getPlayer().setShield(currentShield * getBuff());
    }


    //check collusions method

}

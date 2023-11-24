package com.example.cs2340a_team31.model.decorator;

import com.example.cs2340a_team31.model.Player;

public class AttackPowerUp extends PowerUp {

    public AttackPowerUp(Player player, double buffAmount, double x, double y,double width, double height) {
        setBuff(buffAmount);
        setLocation(x, y);
        setSize(width, height);
        setPlayer(player);
    }

    @Override
    public void applyPowerUp() {
        double currentAttackDamage = getPlayer().getAttackDamage();
        getPlayer().setAttackDamage(currentAttackDamage * getBuff());
    }


    //check collusions method

}

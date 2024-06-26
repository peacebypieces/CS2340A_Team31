package com.example.cs2340a_team31.model.decorator;

import com.example.cs2340a_team31.model.Player;

public class AttackPowerUp extends PowerUp {

    public AttackPowerUp(Player player, double buffAmount, double width, double height) {
        setBuff(buffAmount);
        setSize(width, height);
        setPlayer(player);
        setType("AttackPowerUp");
        setStatus(false);
    }

    @Override
    public void applyPowerUp() {
        double currentAttackDamage = getPlayer().getAttackDamage();
        getPlayer().setAttackDamage(currentAttackDamage * getBuff());
    }


    //check collusions method

}

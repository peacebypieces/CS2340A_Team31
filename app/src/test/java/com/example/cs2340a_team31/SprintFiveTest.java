package com.example.cs2340a_team31;


import static org.junit.Assert.assertEquals;

import com.example.cs2340a_team31.model.Player;
import com.example.cs2340a_team31.model.decorator.HealthPowerUp;
import com.example.cs2340a_team31.model.decorator.PowerUp;
import com.example.cs2340a_team31.model.enemyFactoryPattern.DawgEnemy;
import com.example.cs2340a_team31.model.enemyFactoryPattern.*;

import org.junit.Test;
public class SprintFiveTest {


    private Player player;

    //#1 Kenny Nguyen
    @Test
    public void testInitialization() {
        player = Player.getPlayer();
        player.setHealth(100);

        assertEquals(0.0,player.getHeight(),0);
        assertEquals(0.0, player.getWidth(), 0);
        assertEquals(0.0,player.getX(),0);
        assertEquals(0.0,player.getY(),0);
        assertEquals(25.0,player.getMovementSpeedX(),0);
        assertEquals(100,player.getHealth(),0);
        assertEquals(1,player.getShield(),0);
        assertEquals(5,player.getAttackDamage(),0);

    }
    //#2 Kenny Nguyen
    @Test
    public void testInitializationHealthPowerUpDecorator() {
        HealthPowerUp healthPow = new HealthPowerUp(player, 100,100,100);
        player = Player.getPlayer();
        player.setHealth(100);
        assertEquals("HealthPowerUp", healthPow.getType());
        assertEquals(100, healthPow.getBuff(),0);
        assertEquals(100, healthPow.getWidth(),0);
        assertEquals(100, healthPow.getHeight(),0);

    }

}

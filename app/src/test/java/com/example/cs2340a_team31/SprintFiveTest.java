package com.example.cs2340a_team31;


import static org.junit.Assert.assertEquals;

import com.example.cs2340a_team31.model.Player;
import com.example.cs2340a_team31.model.decorator.AttackPowerUp;
import com.example.cs2340a_team31.model.decorator.HealthPowerUp;
import com.example.cs2340a_team31.model.decorator.PowerUp;
import com.example.cs2340a_team31.model.decorator.ShieldPowerUp;
import com.example.cs2340a_team31.model.enemyFactoryPattern.DawgEnemy;
import com.example.cs2340a_team31.model.enemyFactoryPattern.*;
import com.example.cs2340a_team31.model.weapons.DiamondWeapon;
import com.example.cs2340a_team31.model.weapons.GoldWeapon;
import com.example.cs2340a_team31.model.weapons.NetheriteWeapon;
import com.example.cs2340a_team31.model.weapons.RedstoneWeapon;
import com.example.cs2340a_team31.model.weapons.StoneWeapon;
import com.example.cs2340a_team31.viewmodels.GameViewModel;

import org.junit.Test;

import java.util.ArrayList;

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
        assertEquals(100,player.getShield(),0);
        assertEquals(30,player.getAttackDamage(),0);

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
    //#3 Thomas Wang
    @Test
    public void testInitializationAttackPowerUpDecorator() {
        AttackPowerUp attackPow = new AttackPowerUp(player, 100,100,100);
        player = Player.getPlayer();
        player.setHealth(100);
        assertEquals("AttackPowerUp", attackPow.getType());
        assertEquals(100, attackPow.getBuff(),0);
        assertEquals(100, attackPow.getWidth(),0);
        assertEquals(100, attackPow.getHeight(),0);

    }
    //#4 thomas Wang
    @Test
    public void testInitializationShieldPowerUpDecorator() {
        ShieldPowerUp Pow = new ShieldPowerUp(player, 100,100,100);
        player = Player.getPlayer();
        player.setHealth(100);
        assertEquals("ShieldPowerUp", Pow.getType());
        assertEquals(100, Pow.getBuff(),0);
        assertEquals(100, Pow.getWidth(),0);
        assertEquals(100, Pow.getHeight(),0);

    }

    //#5 Tran Ha
    @Test
    public void testHealthPowOnPlayer() {
        player = Player.getPlayer();
        player.setHealth(100);
        HealthPowerUp healthPow = new HealthPowerUp(player, 100,100,100);
        healthPow.applyPowerUp();
        assertEquals("HealthPowerUp", healthPow.getType());
        assertEquals(100, healthPow.getBuff(),0);
        assertEquals(100, healthPow.getWidth(),0);
        assertEquals(100, healthPow.getHeight(),0);

        assertEquals(10000,player.getHealth(),0);

    }
    //#6 Tran Ha
    @Test
    public void testAttackPowOnPlayer() {
        player = Player.getPlayer();
        player.setHealth(100);
        AttackPowerUp attackPow = new AttackPowerUp(player, 100,100,100);
        attackPow.applyPowerUp();
        assertEquals("AttackPowerUp", attackPow.getType());
        assertEquals(100, attackPow.getBuff(),0);
        assertEquals(100, attackPow.getWidth(),0);
        assertEquals(100, attackPow.getHeight(),0);

        assertEquals(500,player.getAttackDamage(),0);
    }
    //#7 Hoangyen Nguyen
    @Test
    public void testShieldPowOnPlayer() {
        player = Player.getPlayer();
        player.setHealth(100);
        ShieldPowerUp shieldPow = new ShieldPowerUp(player, 100,100,100);
        shieldPow.applyPowerUp();
        assertEquals("ShieldPowerUp", shieldPow.getType());
        assertEquals(100, shieldPow.getBuff(),0);
        assertEquals(100, shieldPow.getWidth(),0);
        assertEquals(100, shieldPow.getHeight(),0);

        assertEquals(100,player.getShield(),0);
    }
    //#8 Hoangyen Nguyen
    @Test
    public void testStoneWeapon() {
        player = Player.getPlayer();
        player.setHealth(100);
        GameViewModel view = new GameViewModel();
        StoneWeapon weapon = new StoneWeapon();
        view.setWeapon(weapon);
        view.setDifficulty(2);
        view.setPlayerAttackDamage();


        assertEquals(30,player.getAttackDamage(),0);
    }
    // #9 Shahbin Hossain
    @Test
    public void testGoldWeapon() {
        player = Player.getPlayer();
        player.setHealth(100);
        GameViewModel view = new GameViewModel();
        GoldWeapon weapon = new GoldWeapon();
        view.setWeapon(weapon);
        view.setDifficulty(2);
        view.setPlayerAttackDamage();


        assertEquals(40,player.getAttackDamage(),0);
    }

    // #10 Shahbin Hossain
    @Test
    public void testDiamondWeapon() {
        player = Player.getPlayer();
        player.setHealth(100);
        GameViewModel view = new GameViewModel();
        DiamondWeapon weapon = new DiamondWeapon();
        view.setWeapon(weapon);
        view.setDifficulty(2);
        view.setPlayerAttackDamage();


        assertEquals(200,player.getAttackDamage(),0);
    }

    //#11 Joshua Johnson
    @Test
    public void testNetheriteWeapon() {
        player = Player.getPlayer();
        player.setHealth(100);
        GameViewModel view = new GameViewModel();
        NetheriteWeapon weapon = new NetheriteWeapon();
        view.setWeapon(weapon);
        view.setDifficulty(2);
        view.setPlayerAttackDamage();


        assertEquals(500,player.getAttackDamage(),0);
    }

    //#12 Joshua Johnson
    @Test
    public void testRedstoneWeapon() {
        player = Player.getPlayer();
        player.setHealth(100);
        GameViewModel view = new GameViewModel();
        RedstoneWeapon weapon = new RedstoneWeapon();
        view.setWeapon(weapon);
        view.setDifficulty(2);
        view.setPlayerAttackDamage();


        assertEquals(100,player.getAttackDamage(),0);
    }


}

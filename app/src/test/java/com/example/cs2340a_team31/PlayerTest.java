package com.example.cs2340a_team31;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340a_team31.model.Player;

import java.util.NoSuchElementException;

public class PlayerTest {

    private Player player;


    // #1 Shahbin Hossain
    @Test
    public void testInitialization() {
        player = Player.getPlayer();

        assertEquals(0.0,player.getX(),0);
        assertEquals(0.0,player.getY(),0);
        assertEquals(25.0,player.getMovementSpeedX(),0);
    }

    // #2 Shahbin Hossain
    @Test
    public void testPlayerXAndY() {
        player = Player.getPlayer();

        player.setX(5.0);
        assertEquals(5.0, player.getX(),0);
        player.setX(0.0);

        player.setY(23.0);
        assertEquals(23.0,player.getY(),0);
        player.setY(0.0);

        player.setScore(43);
        assertEquals(43,player.getScore(),0);

    }

    // #3 Kenny Nguyen
    @Test
    public void testPlayerScore() {
        player = Player.getPlayer();

        player.setScore(43);
        assertEquals(43,player.getScore(),0);
    }

    // #4 Kenny Nguyen
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyPlayerName() {
        String name = "       ";
        player = Player.getPlayer();

        // check that name is not valid
        assertFalse(player.validateName(name));

        // set name to empty space
        player.setName(name);
    }

    // #5 Joshua Johnson
    @Test
    public void testValidName() {
        String name = "Spaceman";
        player = Player.getPlayer();

        // check that name is valid
        assertTrue(player.validateName(name));

        // set name
        player.setName(name);

        // see if names are equal
        assertEquals(name, player.getName());
    }

    // #6 Joshua Johnson
    @Test
    public void testNameTrim() {
        player = Player.getPlayer();

        player.setName("         name");
        assertEquals("name", player.getName());

        player.setName("     name      ");
        assertEquals("name", player.getName());

        player.setName("name      ");
        assertEquals("name", player.getName());

        player.setName("     n    a    m    e      ");
        assertEquals("name", player.getName());
    }

    // #7 Tran Ha
    @Test
    public void testDifficultyEasy() {
        player = Player.getPlayer();

        player.setDifficulty("Easy");

        assertEquals(100.0, player.getHealth(),0);
        assertEquals(100,player.getScore(),0);
    }

    // #8 Tran Ha
    @Test
    public void testDifficultyMedium() {
        player = Player.getPlayer();

        player.setDifficulty("Medium");

        assertEquals(80.0, player.getHealth(),0);
        assertEquals(200,player.getScore(),0);
    }

    // #9 Hoangyen Nguyen
    @Test
    public void testDifficultyHard() {
        player = Player.getPlayer();

        player.setDifficulty("Hard");

        assertEquals(60.0, player.getHealth(),0);
        assertEquals(300,player.getScore(),0);
    }

    // #10 Hoangyen Nguyen
    @Test(expected = NoSuchElementException.class)
    public void testNotGivenDifficulty() {
        player = Player.getPlayer();

        player.setDifficulty("this isn't a difficulty");
    }

}
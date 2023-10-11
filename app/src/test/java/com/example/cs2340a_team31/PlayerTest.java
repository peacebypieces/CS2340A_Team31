package com.example.cs2340a_team31;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340a_team31.model.Player;

public class PlayerTest {

    private Player player;

    @Test
    public void testInitialization() {
        player = Player.getPlayer();

        assertEquals(0.0,player.getX(),0);
        assertEquals(0.0,player.getY(),0);
        assertEquals(5.0,player.getMovementSpeed(),0);
    }

    @Test
    public void testSetterAndGetterMethods() {
        player = Player.getPlayer();

        player.setX(5.0);
        assertEquals(5.0, player.getX(),0);

        player.setY(23.0);
        assertEquals(23.0,player.getY(),0);

        player.setScore(43);
        assertEquals(43,player.getScore(),0);

        player.setName("IAmAName");
        assertEquals("IAmAName",player.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyPlayerName() {
        String name = "       ";
        player = Player.getPlayer();

        // check that name is not valid
        assertFalse(player.validateName(name));

        // set name to empty space
        player.setName(name);
    }

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

}
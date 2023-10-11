package com.example.cs2340a_team31;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340a_team31.model.Player;
import com.example.cs2340a_team31.views.GameActivity;
import com.example.cs2340a_team31.views.GameConfigActivity;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private Player player;

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyPlayerName() {
        String name = " ";
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

        // set name to empty space
        player.setName(name);
        
        // see if names are equal
        assertEquals(name, player.getName());
    }

}
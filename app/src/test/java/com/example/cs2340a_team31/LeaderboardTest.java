package com.example.cs2340a_team31;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.example.cs2340a_team31.model.LeaderBoard;
import com.example.cs2340a_team31.model.LeaderBoardPlayer;

import org.junit.Before;
import org.junit.Test;

public class LeaderboardTest {

    private LeaderBoard testLB;


    // #11 Thomas Wang
    @Test
    public void testInitialization() {
        testLB = LeaderBoard.getleaderboard();

        assertNotNull(testLB.getPlayerSize());
    }

    // #12 Thomas Wang
    @Test
    public void testAddIntoLeaderBoard() {
        testLB = LeaderBoard.getleaderboard();

        LeaderBoardPlayer player = new LeaderBoardPlayer("Josh",
                "11/10/2023 11:53PM",
                200);

        testLB.add(player);

        assertEquals(1,testLB.getPlayerSize());
        assertEquals(player,testLB.getPlayers()[0]);

    }

}

package com.example.cs2340a_team31;
import java.util.Arrays;

public class LeaderBoard {
    private volatile static LeaderBoard leaderboard;

    private LeaderBoardPlayer[] players;

    private LeaderBoard() {
        players = new LeaderBoardPlayer[10];
    }

    public static LeaderBoard getleaderboard() {
        if (leaderboard == null) {
            synchronized (LeaderBoard.class) {
                if (leaderboard == null) {
                    leaderboard = new LeaderBoard();
                }
            }
        }
        return leaderboard;
    }




}

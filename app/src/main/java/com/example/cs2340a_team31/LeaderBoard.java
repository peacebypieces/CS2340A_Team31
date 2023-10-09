package com.example.cs2340a_team31;

public class LeaderBoard {
    private volatile static LeaderBoard leaderboard;

    private Player[] players;

    private LeaderBoard() {}


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

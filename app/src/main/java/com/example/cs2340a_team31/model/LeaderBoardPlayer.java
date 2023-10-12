package com.example.cs2340a_team31.model;

public class LeaderBoardPlayer {
    private String name;
    private String dateTime;
    private int score;

    public LeaderBoardPlayer(String name, String dateTime, int score) {
        this.name = name;
        this.dateTime = dateTime;
        this.score = score;
    }


    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    /*
    @Override
    public int compareTo(LeaderBoardPlayer leaderBoardPlayer) {
        return (leaderBoardPlayer.getScore());
    }

     */
}

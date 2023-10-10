package com.example.cs2340a_team31;

import org.w3c.dom.CDATASection;

public class LeaderBoardPlayer {
    private String name;
    private String date_time;
    private int score;

    public LeaderBoardPlayer(String name, String date_time, int score) {
        this.name = name;
        this.date_time = date_time;
        this.score = score;
    }


    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate_time() {
        return date_time;
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

package com.example.cs2340a_team31.viewmodels;

import com.example.cs2340a_team31.model.observers.PlayerObserver;


public class PlayerView implements PlayerObserver {
    private double x;
    private double y;

    // TODO fix to include given imageView
    public PlayerView() {
        this.x = 0;
        this.y = 0;

    }

    public void onPlayerPositionChanged(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}

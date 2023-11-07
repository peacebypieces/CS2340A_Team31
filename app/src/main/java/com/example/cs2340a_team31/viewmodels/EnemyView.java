package com.example.cs2340a_team31.viewmodels;

import com.example.cs2340a_team31.model.Player;
import com.example.cs2340a_team31.model.observers.EnemyObserver;
import com.example.cs2340a_team31.model.observers.PlayerObserver;


public class EnemyView implements EnemyObserver {
    private double x;
    private double y;

    // TODO fix to include given imageView
    public EnemyView() {
        this.x = 0;
        this.y = 0;

    }

    public void updatePlayerPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void checkCollision (Player player) {
        // TODO add whatever is supposed to be here
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}

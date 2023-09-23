package com.example.cs2340a_team31;

public class PlayerView {
    private float x, y;

    public PlayerView(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void updatePosition(float newX, float newY) {
        x = newX;
        y = newY;
    }
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}

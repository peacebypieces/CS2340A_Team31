package com.example.cs2340a_team31;

import android.content.Context;
import android.view.View;

public class PlayerView extends View {
    private float x;
    private float y;

    // TODO fix to include given imageView
    public PlayerView(Context context, float x, float y) {
        super(context);
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

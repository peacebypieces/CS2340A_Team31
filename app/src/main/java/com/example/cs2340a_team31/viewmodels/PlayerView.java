package com.example.cs2340a_team31.viewmodels;

import android.content.Context;

import androidx.appcompat.widget.AppCompatImageView;


public class PlayerView extends AppCompatImageView {
    private float x;
    private float y;

    // TODO fix to include given imageView
    public PlayerView(Context context, float x, float y, String selectedCharacter) {
        super(context);
        this.x = x;
        this.y = y;

    }

    public void updatePosition(float newX, float newY) {
        this.setX(newX);
        this.setY(newY);
    }
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}

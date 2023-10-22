package com.example.cs2340a_team31.viewmodels;

import android.content.Context;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.cs2340a_team31.model.PlayerObserver;


public class PlayerView implements PlayerObserver {
    private float x;
    private float y;

    // TODO fix to include given imageView
    public PlayerView() {
        this.x = 0;
        this.y = 0;

    }

    public void onPlayerPositionChanged(float x, float y) {
        this.x = x;
        this.y =y;
    }
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}

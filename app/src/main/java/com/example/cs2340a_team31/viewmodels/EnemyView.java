package com.example.cs2340a_team31.viewmodels;

import android.content.Context;
import androidx.appcompat.widget.AppCompatImageView;


public class EnemyView extends AppCompatImageView {
    private float x;
    private float y;
    public EnemyView(Context context) {
        super(context);
        this.x = 0;
        this.y = 0;
    }

    public void updatePlayerPosition(float x, float y) {
        this.setX(x);
        this.setY(y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}

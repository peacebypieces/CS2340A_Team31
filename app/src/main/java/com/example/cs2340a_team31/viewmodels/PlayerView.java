package com.example.cs2340a_team31.viewmodels;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.cs2340a_team31.R;
import com.example.cs2340a_team31.model.observers.PlayerObserver;


public class PlayerView extends AppCompatImageView implements PlayerObserver {
    private float x;
    private float y;

    public PlayerView(Context context) {
        super(context);
        this.x = 0;
        this.y = 0;
    }

    public void onPlayerPositionChanged(float x, float y) {
        this.setX(x);
        this.setY(y);
        //invalidate();
    }
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}

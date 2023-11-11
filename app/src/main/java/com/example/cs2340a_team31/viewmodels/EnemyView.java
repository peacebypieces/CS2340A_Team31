package com.example.cs2340a_team31.viewmodels;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.cs2340a_team31.R;
import com.example.cs2340a_team31.model.Player;
import com.example.cs2340a_team31.model.enemyFactoryPattern.Enemy;
import com.example.cs2340a_team31.model.observers.EnemyObserver;
import com.example.cs2340a_team31.model.observers.PlayerObserver;


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

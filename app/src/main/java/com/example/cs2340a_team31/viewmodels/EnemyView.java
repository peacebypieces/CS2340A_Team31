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


public class EnemyView extends AppCompatImageView implements EnemyObserver {
    private float x;
    private float y;

    // TODO fix to include given imageView
    public EnemyView(Context context) {
        super(context);
        this.x = 0;
        this.y = 0;
    }

    @Override
    public void updatePlayerPosition(double playerX, double playerY) {
        this.setX(x);
        this.setY(y);
    }

    public void checkCollision(Player player) {
        // todo add collision
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}

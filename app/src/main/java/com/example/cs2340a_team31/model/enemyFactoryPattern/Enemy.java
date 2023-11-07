package com.example.cs2340a_team31.model.enemyFactoryPattern;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.cs2340a_team31.model.observers.EnemyObserver;

public abstract class Enemy implements EnemyObserver {

    double movementSpeed;

    String direction;
    double damage;
    Drawable sprite;
    double health;

    boolean alive;

    double x;
    double y;

    double width;
    double height;

    void move() {
        if (!alive) {
            return;
        }
        switch (direction) {
            case "UP":
                this.y -= movementSpeed;
                break;
            case "DOWN":
                this.y += movementSpeed;
                break;
            case "RIGHT":
                this.x += movementSpeed;
                break;
            case "LEFT":
                this.x -= movementSpeed;
                break;
        }
    }

    void setDirection(String direction) {
        this.direction = direction;
    }

    void setSize(double w, double h) {
        width = w;
        height = h;
    }

    void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    void kill() {
        this.alive = false;
    }

    boolean isAlive() {
        return alive;
    }




}


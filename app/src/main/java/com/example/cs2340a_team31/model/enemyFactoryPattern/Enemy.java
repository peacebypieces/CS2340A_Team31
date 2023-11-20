package com.example.cs2340a_team31.model.enemyFactoryPattern;

import android.graphics.RectF;

import com.example.cs2340a_team31.model.Player;
import com.example.cs2340a_team31.model.observers.EnemyObserver;

public abstract class Enemy implements EnemyObserver {
    private int movementCounter = 0;
    private double movementSpeed;

    private String type;

    private String direction;
    private double damage;
    private double health;

    private boolean alive;

    private double x;
    private double y;

    private double width;
    private double height;

    @Override
    public void move() {
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
        default:
            break;
        }

        movementCounter++;

        if (movementCounter == 20) {
            movementCounter = 0;
            switch (direction) {
            case "UP":
                this.direction = "DOWN";
                break;
            case "DOWN":
                this.direction = "UP";
                break;
            case "RIGHT":
                this.direction = "LEFT";
                break;
            case "LEFT":
                this.direction = "RIGHT";
                break;
            default:
                break;
            }
        }

    }

    @Override
    public void checkCollision(Player player) {
        double playerX = player.getX();
        double playerY = player.getY();
        double playerWidth = player.getWidth();
        double playerHeight = player.getHeight();
        double enemyX = getX();
        double enemyY = getY();
        double enemyWidth = getWidth();
        double enemyHeight = getHeight();

        /*
        Creates a rectangle around dot, and checks for an intersection between player rect and
        dot rect. Intersection = collision.
         */
        RectF playerBounds = new RectF((float) playerX, (float) playerY,
                (float) (playerX + playerWidth), (float) (playerY + playerHeight));
        RectF enemyBounds = new RectF((float) enemyX, (float) enemyY,
                (float) (enemyX + enemyWidth), (float) (enemyY + enemyHeight));
        if (playerBounds.intersect(enemyBounds) && isAlive()) {
            player.notifyCollision(damage);
        }
    }

    public void takeDamage(double damage) {
        health -= damage;
        System.out.println("Enemy HP: " + health);
    }

    void setSize(double w, double h) {
        width = w;
        height = h;
    }

    void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void kill() {
        this.alive = false;
    }

    boolean isAlive() {
        return alive;
    }

    public String getType() {
        return type;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDamage() {
        return damage;
    }

    public double getHealth() {
        return health;
    }
}


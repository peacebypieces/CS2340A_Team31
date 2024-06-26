package com.example.cs2340a_team31.model.enemyFactoryPattern;

import android.graphics.RectF;

import com.example.cs2340a_team31.model.Player;
import com.example.cs2340a_team31.model.observers.EnemyObserver;

/**
 * The abstract class Enemy represents the base class for various enemy types in the game.
 * It includes common properties and methods that all enemies share.
 */
public abstract class Enemy implements EnemyObserver {
    private int movementCounter = 0;

    private int movementCounterCap = 20;
    private double movementSpeed;

    private String type;

    private String direction;
    private double damage;
    private double health;

    private boolean alive;

    private double enemyPoint;

    private double x;
    private double y;

    private double width;
    private double height;
    /**
     * Moves the enemy in the specified direction based on its movement speed.
     * If the enemy is not alive, no movement occurs.
     */
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

        if (movementCounter == movementCounterCap) {
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

    /**
     * Checks for collision between the enemy and the player.
     * If a collision occurs and the enemy is alive, it notifies the player of the collision.
     *
     * @param player The player object to check collision with.
     */

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

    /**
     * Reduces the enemy's health based on the amount of damage taken.
     * Prints the updated enemy's health to the console.
     *
     * @param damage The amount of damage to be inflicted on the enemy.
     */
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

    public void setMovementCounterCap(int movementCounterCap) {
        this.movementCounterCap = movementCounterCap;
    }

    public void kill() {
        this.alive = false;
    }

    public boolean isAlive() {
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

    public void setEnemyPoint(double enemyPoint) {
        this.enemyPoint = enemyPoint;
    }

    public double getEnemyPoint() {
        return enemyPoint;
    }
}


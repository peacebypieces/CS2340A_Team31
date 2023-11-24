package com.example.cs2340a_team31.model;

import android.graphics.RectF;

import com.example.cs2340a_team31.model.decorator.PowerUps;
import com.example.cs2340a_team31.model.enemyFactoryPattern.Enemy;
import com.example.cs2340a_team31.model.observers.EnemyObserver;
import com.example.cs2340a_team31.model.observers.PlayerObserver;
import com.example.cs2340a_team31.model.observers.PlayerSubject;
import com.example.cs2340a_team31.model.strategypattern.MovementStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Player implements PlayerSubject {
    private MovementStrategy movementStrategy;

    private List<PlayerObserver> observers = new ArrayList<>();
    private List<EnemyObserver> enemyObservers = new ArrayList<>();



    private double x;
    private double y;

    private double width;

    private double height;
    private double movementSpeedX;
    private double movementSpeedY;

    private double health;

    private double attackDamage;
    private static Player player;

    private String name;
    private String dateTime;
    private int score;
    private String difficulty;



    /*
     * @param x x-coordinate of player (default 0.0)
     * @param y y-coordinate of player (default 0.0)
     * @param movementSpeed movement speed of player (default 5.0)
     */
    private Player() {
        this.x = 0.0;
        this.y = 0.0;
        this.width = 0;
        this.height = 0;
        this.movementSpeedX = 25.0;
        this.movementSpeedY = 25.0;
        this.attackDamage = 5.0;
    }

    /*
     * Name: getPlayer()
     * @return the instance of the player
     */
    public static Player getPlayer() {
        if (player == null) {
            synchronized (Player.class) {
                if (player == null) {
                    player = new Player();
                }
            }
        }
        return player;
    }

    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
    }

    public void move(float x) {
        if (movementStrategy != null) {
            movementStrategy.move(x);
        }
    }


    public double getMovementSpeedX() {
        return movementSpeedX;
    }
    public double getMovementSpeedY() {
        return movementSpeedY;
    }

    public void setHealth(double health) {
        this.health = health;
    }
    public double getHealth() {
        return this.health;
    }

    public void setAttackDamage(double attackDamage) {
        this.attackDamage = attackDamage;
    }
    public double getAttackDamage() {
        return attackDamage;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(double w, double h) {
        this.width = w;
        this.height = h;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double y) {
        this.y = y;
    }

    public void setMovementSpeed(double movementSpeedX, double movementSpeedY) {
        this.movementSpeedX = movementSpeedX;
        this.movementSpeedY = movementSpeedY;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double x) {
        this.x = x;
    }

    public void setName(String name) {
        if (validateName(name)) {
            this.name = name.replaceAll("\\s", "");
        } else {
            throw new IllegalArgumentException("Name cannot be blank");
        }
    }

    public String getName() {
        return this.name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public void setDateTime(String date) {
        this.dateTime = date;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public boolean validateName(String name) {
        name = name.trim();

        return !name.isEmpty() && name != null;
    }

    public void setDifficulty(String name) {
        if (name.equals("Easy")) {
            this.health = 100;
            this.score = 100;
        } else if (name.equals("Medium")) {
            this.health = 200;
            this.score = 200;
        } else if (name.equals("Hard")) {
            this.health = 300;
            this.score = 300;
        } else {
            throw new NoSuchElementException("Difficulty wasn't given");
        }
    }

    public boolean isCollided(Enemy enemy) {
        double playerX = getX();
        double playerY = getY();
        double playerWidth = getWidth();
        double playerHeight = getHeight();
        double enemyX = enemy.getX();
        double enemyY = enemy.getY();
        double enemyWidth = enemy.getWidth();
        double enemyHeight = enemy.getHeight();

        /*
        Creates a rectangle around dot, and checks for an intersection between player rect and
        dot rect. Intersection = collision.
         */
        RectF playerBounds = new RectF((float) playerX, (float) playerY,
                (float) (playerX + playerWidth), (float) (playerY + playerHeight));
        RectF enemyBounds = new RectF((float) enemyX, (float) enemyY,
                (float) (enemyX + enemyWidth), (float) (enemyY + enemyHeight));
        if (playerBounds.intersect(enemyBounds)) {
            return true;
        }
        return false;
    }



    // Other player-related methods and logic here...

    @Override
    public void addObserver(PlayerObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(PlayerObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (PlayerObserver observer : observers) {
            observer.onPlayerPositionChanged((float) x, (float) y);
        }
    }

    public void addEnemyObserver(EnemyObserver observer) {
        enemyObservers.add(observer);
    }

    public void removeEnemyObservers() {
        enemyObservers.clear();
    }

    public void notifyEnemies() {
        for (EnemyObserver observer : enemyObservers) {
            observer.move();
            observer.checkCollision(player);
        }
    }

    public void notifyCollision(double damage) {
        health -= damage;
        System.out.println("Player HP: " + health);
    }

    public void attack(Enemy enemy) {
        enemy.takeDamage(attackDamage);
    }


}

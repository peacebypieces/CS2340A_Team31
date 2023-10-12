package com.example.cs2340a_team31.model;

import java.util.NoSuchElementException;

public class Player {

    private double x;
    private double y;
    private double movementSpeed;

    private double health;
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
        this.movementSpeed = 5.0;
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

    public void moveUp() {
        setY(Math.max(0, getY() - movementSpeed));
    }

    public void moveDown() {
        setY(Math.min(getY() + movementSpeed, 720));
    }

    public void moveLeft() {
        setX(Math.max(0, getX() - movementSpeed));
    }

    public void moveRight() {
        setX(Math.min(getX() + movementSpeed, 720));
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public void setHealth(double health) {
        this.health = health;
    }
    public double getHealth() {
        return this.health;
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
            this.health = 80;
            this.score = 200;
        } else if (name.equals("Hard")) {
            this.health = 60;
            this.score = 300;
        } else {
            throw new NoSuchElementException("Difficulty wasn't given");
        }
    }
}

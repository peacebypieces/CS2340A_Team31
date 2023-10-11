package com.example.cs2340a_team31.model;

public class Player {

    private double x;
    private double y;
    private double movementSpeed;

    private double health;
    private static Player player;

    private String name;
    private String date_time;
    private int score;

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
        if(validateName(name)){
            this.name = name;
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

    public void setDate_time(String date) {
        this.date_time = date;
    }

    public String getDate_time() {
        return this.date_time;
    }

    public boolean validateName(String name) {
        name = name.trim();

        if(name.isEmpty()){
            return false;
        } else {
            return true;
        }

    }
}

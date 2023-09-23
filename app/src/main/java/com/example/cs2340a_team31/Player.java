package com.example.cs2340a_team31;

public class Player {

    private double x;
    private double y;
    private double movementSpeed;

    private double health;
    private static Player player;

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
    public static Player getPlayer(){
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
}

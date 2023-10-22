package com.example.cs2340a_team31.model;

public class Door {
    private double x;
    private double y;

    private double width;

    private double height;

    /*
     * @param x x-coordinate of player (default 0.0)
     * @param y y-coordinate of player (default 0.0)
     * @param movementSpeed movement speed of player (default 5.0)
     */
    public Door() {
        this.x = 0.0;
        this.y = 0.0;
        this.width = 0;
        this.height = 0;
    }

    /*
     * Name: getPlayer()
     * @return the instance of the player
     */
    public Door(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
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

    public double getWidth() {
        return width;
    }

    public void setWidth(double x) {
        this.x = x;
    }
}

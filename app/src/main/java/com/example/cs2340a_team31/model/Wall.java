package com.example.cs2340a_team31.model;

public class Wall {
    private double x;
    private double y;

    private double width;

    private double height;

    public Wall(double x, double y, double w, double h) {
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

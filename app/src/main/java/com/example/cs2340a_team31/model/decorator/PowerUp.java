package com.example.cs2340a_team31.model.decorator;

import android.graphics.RectF;

import com.example.cs2340a_team31.model.Player;


public abstract class PowerUp implements PowerUps {
    // abstract class
    private Player player;
    private double x;
    private double y;

    private double width;

    private double height;

    private double buff;

    private String type;

    /**
     * Get the type of the power-up.
     *
     * @return The type of the power-up.
     */
    private boolean pickedUp;

    public String getType() { return type; }
    public Boolean getStatus() { return pickedUp; }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * Get the buff value associated with the power-up.
     *
     * @return The buff value.
     */
    public double getBuff() {
        return buff;
    }


    public Player getPlayer() {
        return player;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Set the status of the power-up (picked up or not).
     *
     * @param pickedUp True if the power-up is picked up, otherwise false.
     */
    public void setStatus(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
    /**
     * Set the buff value for the power-up.
     *
     * @param buff The buff value to set.
     */
    public void setBuff(double buff) {
        this.buff = buff;
    }
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void setSize(double width, double height) {
        this.height = height;
        this.width = width;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Check if the player has collided with the power-up.
     *
     * @return True if the player has collided with the power-up, otherwise false.
     */

    public boolean isCollidedWithPowerUp() {
        double playerX = player.getX();
        double playerY = player.getY();
        double playerWidth = getWidth();
        double playerHeight = getHeight();
        double powerupX = getX();
        double powerupY = getY();
        double powerUpWidth = getWidth();
        double powerUpHeight = getHeight();
        RectF playerBounds = new RectF((float) playerX, (float) playerY,
                (float) (playerX + playerWidth), (float) (playerY + playerHeight));
        RectF powerupBounds = new RectF((float) powerupX, (float) powerupY,
                (float) (powerupX + powerUpWidth), (float) (powerupY + powerUpHeight));
        if (playerBounds.intersect(powerupBounds)) {
            return true;
        }
        return false;
    }

}

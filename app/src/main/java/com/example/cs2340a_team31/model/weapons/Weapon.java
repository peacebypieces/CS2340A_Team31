package com.example.cs2340a_team31.model.weapons;

public abstract class Weapon {

    private double x;
    private double y;
    private int damage;
    private String weaponType;

    public int getDamage() {
        return damage;
    }

    public String getWeapon() {
        return weaponType;
    }

    public void setWeaponType(String type) {
        weaponType = type;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

package com.example.cs2340a_team31.model.weapons;

public abstract class Weapon {
    int damage;
    String weaponType;

    public int getDamage() {
        return damage;
    }

    public String getWeapon() {
        return weaponType;
    }

    public void setWeapon_type(String type) {
        weaponType = type;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}

package com.example.cs2340a_team31.model.weapons;

public abstract class Weapon {
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
}

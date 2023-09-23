package com.example.cs2340a_team31;

public class Player {
    private float x,y;

    public float health;

    public Player(String difficulty){

        this.x = 0;

        this.y = 0;

        switch(difficulty){
            case "easy":
                this.health = 120;
                break;
            case "medium":
                this.health = 100;
                break;
            case "hard":
                this.health = 80;
                break;
        }


    }

    public Player(float x, float y, String difficulty){

        this.x = x;

        this.y = y;

        switch(difficulty){
            case "easy":
                this.health = 120;
                break;
            case "medium":
                this.health = 100;
                break;
            case "hard":
                this.health = 80;
                break;
        }


    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public boolean isDead(){
        return health <= 0;
    }
}

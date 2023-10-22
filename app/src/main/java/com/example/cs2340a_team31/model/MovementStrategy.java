package com.example.cs2340a_team31.model;

public interface MovementStrategy {
        double x = 0;
        double y = 0;
        double movementSpeed = 0;
        double health = 0;

        void moveUp();

        void moveDown();

        void moveLeft();

        void moveRight();

        double getMovementSpeed();


    }


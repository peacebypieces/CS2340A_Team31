package com.example.cs2340a_team31.viewmodels;

import android.graphics.RectF;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;

import androidx.lifecycle.ViewModel;

import com.example.cs2340a_team31.model.Door;
import com.example.cs2340a_team31.model.Player;
import com.example.cs2340a_team31.model.Room;
import com.example.cs2340a_team31.model.Wall;
import com.example.cs2340a_team31.model.enemyFactoryPattern.Enemy;
import com.example.cs2340a_team31.model.enemyFactoryPattern.EnemyFactory;
import com.example.cs2340a_team31.model.enemyFactoryPattern.SpaceEnemyFactory;
import com.example.cs2340a_team31.model.strategypattern.MoveDownStrategy;
import com.example.cs2340a_team31.model.strategypattern.MoveLeftStrategy;
import com.example.cs2340a_team31.model.strategypattern.MoveRightStrategy;
import com.example.cs2340a_team31.model.strategypattern.MoveUpStrategy;

import java.util.ArrayList;
import java.util.Calendar;

public class GameViewModel extends ViewModel {

    private Player player;

    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;
    private String playername;

    private double difficulty;

    private double enemyHealth;

    private  EnemyFactory enemyFactory;
    private Room currentRoom;
    private Door door;

    private int currentRoomNum = 1;

    private boolean roomChanged = false;
    private int scoreValue;

    private double screenWidth;
    private double screenHeight;
    private double widthRatio;
    private double heightRatio;

    public GameViewModel() {
        // Initialize player, room, and door
        scoreValue = 0; // Change this to the initial score
        player = Player.getPlayer();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        // Perform initialization tasks here

    }

    public void onKeyDown(int keyCode, KeyEvent event) {
        // Handle key events, move player, and check collisions

        float num = 0;

        // Moves player based on key
        switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_LEFT:
            if (checkWallCollision(-player.getMovementSpeedX(), 0)) {
                break;
            }
            player.setMovementStrategy(new MoveLeftStrategy());
            player.move(num);
            break;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            if (checkWallCollision(player.getMovementSpeedX(), 0)) {
                break;
            }
            player.setMovementStrategy(new MoveRightStrategy());
            num = (float) (screenWidth - player.getWidth());
            player.move(num);
            break;
        case KeyEvent.KEYCODE_DPAD_DOWN:
            if (checkWallCollision(0, player.getMovementSpeedY())) {
                break;
            }
            player.setMovementStrategy(new MoveDownStrategy());
            num = (float) (screenHeight - player.getHeight());
            player.move(num);
            break;
        case KeyEvent.KEYCODE_DPAD_UP:
            if (checkWallCollision(0, -player.getMovementSpeedY())) {
                break;
            }
            player.setMovementStrategy(new MoveUpStrategy());
            player.move(num);
            break;
        default:
            break;
        }

        // Notifies observers of player movement
        player.notifyObservers();

        // check collisions here
        checkDoorCollision();
    }

    public void calculateRatio(double screenWidth, double screenHeight) {
        // Calculate width and height ratio based on the screen size
        // Update widthRatio and heightRatio accordingly

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        this.widthRatio = screenWidth / 32.0;
        this.heightRatio = screenHeight / 18.0;


        player.setPosition(widthRatio * 27, heightRatio * 14);
        player.setSize(widthRatio, heightRatio);
        player.setMovementSpeed(widthRatio / 2, heightRatio / 2);

        setDoorLocation(1, 8, false);
        enemyFactory = new SpaceEnemyFactory();

        currentRoom = room1();
    }

    public Room room1() {
        player.removeEnemyObservers();

        // Create rooms and add walls to them
        Wall wall;

        // Room 1
        Room firstRoom = new Room();

        // 2 verticals
        wall = new Wall(widthRatio * 9, heightRatio * 1, widthRatio * 1, heightRatio * 5);
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 18, heightRatio * 1, widthRatio * 1, heightRatio * 5);
        firstRoom.addWall(wall);
        // bathroom
        wall = new Wall(widthRatio * 22, heightRatio * 4, widthRatio * 6, heightRatio * 1); //top
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 22, heightRatio * 4, widthRatio * 1, heightRatio * 5); //left
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 22, heightRatio * 8, widthRatio * 3, heightRatio * 1); //bottom
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 27, heightRatio * 4, widthRatio * 1, heightRatio * 5); // right
        firstRoom.addWall(wall);
        //Closet
        wall = new Wall(widthRatio * 25, heightRatio * 10, widthRatio * 6, heightRatio * 1);
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 25, heightRatio * 10, widthRatio * 1, heightRatio * 4);
        firstRoom.addWall(wall);
        // 5 electricity
        wall = new Wall(widthRatio * 2, heightRatio * 13, widthRatio * 2, heightRatio * 2);
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 6, heightRatio * 13, widthRatio * 2, heightRatio * 2);
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 10, heightRatio * 13, widthRatio * 2, heightRatio * 2);
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 6, heightRatio * 8, widthRatio * 2, heightRatio * 2);
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 10, heightRatio * 8, widthRatio * 2, heightRatio * 2);
        firstRoom.addWall(wall);
        // 4 edge walls
        wall = new Wall(widthRatio * 0, heightRatio * 0, widthRatio * 32, heightRatio * 1);
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 0, heightRatio * 16, widthRatio * 32, heightRatio * 1);
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 0, heightRatio * 0, widthRatio * 1, heightRatio * 18);
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 31, heightRatio * 0, widthRatio * 1, heightRatio * 18);
        firstRoom.addWall(wall);

        // Adds the 3 enemies in the room
        enemy1 = enemyFactory.spawnEnemy("mice", 12F * widthRatio,
                9F * heightRatio, difficulty, widthRatio,
                heightRatio, "RIGHT", 60);
        firstRoom.addEnemy(enemy1);
        player.addEnemyObserver(enemy1);

        enemy2 = enemyFactory.spawnEnemy("mice", 12F * widthRatio,
                2F * heightRatio, difficulty, widthRatio,
                heightRatio, "DOWN", 40);
        firstRoom.addEnemy(enemy2);
        player.addEnemyObserver(enemy2);

        enemy3 = enemyFactory.spawnEnemy("rat", 2F * widthRatio,
                2F * heightRatio, difficulty, widthRatio,
                heightRatio, "DOWN", 30);
        firstRoom.addEnemy(enemy3);
        player.addEnemyObserver(enemy3);

        return firstRoom;
    }

    public Room room2() {
        player.removeEnemyObservers();

        Wall wall;

        // Room 2
        Room secondRoom = new Room();
        // First 3 vertical
        wall = new Wall(widthRatio * 4, heightRatio * 1, widthRatio * 1, heightRatio * 3);
        secondRoom.addWall(wall);
        wall = new Wall(widthRatio * 12, heightRatio * 1, widthRatio * 1, heightRatio * 4);
        secondRoom.addWall(wall);
        wall = new Wall(widthRatio * 20, heightRatio * 1, widthRatio * 1, heightRatio * 7);
        secondRoom.addWall(wall);
        //2 horizontal
        wall = new Wall(widthRatio * 1, heightRatio * 6, widthRatio * 4, heightRatio * 1);
        secondRoom.addWall(wall);
        wall = new Wall(widthRatio * 4, heightRatio * 8, widthRatio * 9, heightRatio * 1);
        secondRoom.addWall(wall);
        // other verticals
        wall = new Wall(widthRatio * 4, heightRatio * 7, widthRatio * 1, heightRatio * 7);
        secondRoom.addWall(wall);
        wall = new Wall(widthRatio * 12, heightRatio * 7, widthRatio * 1, heightRatio * 5);
        secondRoom.addWall(wall);
        wall = new Wall(widthRatio * 16, heightRatio * 8, widthRatio * 1, heightRatio * 9);
        secondRoom.addWall(wall);
        wall = new Wall(widthRatio * 8, heightRatio * 12, widthRatio * 1, heightRatio * 4);
        secondRoom.addWall(wall);
        wall = new Wall(widthRatio * 27, heightRatio * 10, widthRatio * 1, heightRatio * 7);
        secondRoom.addWall(wall);
        // 4 edge walls
        wall = new Wall(widthRatio * 0, heightRatio * 0, widthRatio * 32, heightRatio * 1);
        secondRoom.addWall(wall);
        wall = new Wall(widthRatio * 0, heightRatio * 16, widthRatio * 32, heightRatio * 1);
        secondRoom.addWall(wall);
        wall = new Wall(widthRatio * 0, heightRatio * 0, widthRatio * 1, heightRatio * 18);
        secondRoom.addWall(wall);
        wall = new Wall(widthRatio * 31, heightRatio * 0, widthRatio * 1, heightRatio * 18);
        secondRoom.addWall(wall);

        // Adds the 3 enemies in the room
        enemy1 = enemyFactory.spawnEnemy("rat", 13F * widthRatio,
                2F * heightRatio, difficulty, widthRatio,
                heightRatio, "DOWN", 40);
        secondRoom.addEnemy(enemy1);
        player.addEnemyObserver(enemy1);

        enemy2 = enemyFactory.spawnEnemy("rat", 18F * widthRatio,
                2F * heightRatio, difficulty, widthRatio,
                heightRatio, "DOWN", 40);
        secondRoom.addEnemy(enemy2);
        player.addEnemyObserver(enemy2);

        enemy3 = enemyFactory.spawnEnemy("dog", 2F * widthRatio,
                5F * heightRatio, difficulty, widthRatio,
                heightRatio, "RIGHT", 40);
        secondRoom.addEnemy(enemy3);
        player.addEnemyObserver(enemy3);

        return secondRoom;
    }

    public Room room3() {
        player.removeEnemyObservers();

        Wall wall;

        // Room 3
        Room thirdRoom = new Room();

        // 4 ships
        wall = new Wall(widthRatio * 1, heightRatio * 3, widthRatio * 6, heightRatio * 4);
        thirdRoom.addWall(wall);
        wall = new Wall(widthRatio * 12, heightRatio * 3, widthRatio * 6, heightRatio * 4);
        thirdRoom.addWall(wall);
        wall = new Wall(widthRatio * 3, heightRatio * 10, widthRatio * 6, heightRatio * 4);
        thirdRoom.addWall(wall);
        wall = new Wall(widthRatio * 20, heightRatio * 10, widthRatio * 6, heightRatio * 4);
        thirdRoom.addWall(wall);
        // 4 edge walls
        wall = new Wall(widthRatio * 0, heightRatio * 0, widthRatio * 32, heightRatio * 1);
        thirdRoom.addWall(wall);
        wall = new Wall(widthRatio * 0, heightRatio * 16, widthRatio * 32, heightRatio * 1);
        thirdRoom.addWall(wall);
        wall = new Wall(widthRatio * 0, heightRatio * 0, widthRatio * 1, heightRatio * 18);
        thirdRoom.addWall(wall);
        wall = new Wall(widthRatio * 31, heightRatio * 0, widthRatio * 1, heightRatio * 18);
        thirdRoom.addWall(wall);

        // Adds the 3 enemies in the room
        enemy1 = enemyFactory.spawnEnemy("rat", 9F * widthRatio,
                11F * heightRatio, difficulty, widthRatio,
                heightRatio, "RIGHT", 30);
        thirdRoom.addEnemy(enemy1);
        player.addEnemyObserver(enemy1);

        enemy2 = enemyFactory.spawnEnemy("dog", 27F * widthRatio,
                2F * heightRatio, difficulty, widthRatio,
                heightRatio, "DOWN", 40);
        thirdRoom.addEnemy(enemy2);
        player.addEnemyObserver(enemy2);

        enemy3 = enemyFactory.spawnEnemy("dog", 2F * widthRatio,
                7F * heightRatio, difficulty, widthRatio,
                heightRatio, "RIGHT", 75);
        thirdRoom.addEnemy(enemy3);
        player.addEnemyObserver(enemy3);

        return thirdRoom;
    }

    public Room room4() {
        player.removeEnemyObservers();

        Wall wall;

        // Room 4
        Room fourthRoom = new Room();

        // 4 edge walls
        wall = new Wall(widthRatio * 0, heightRatio * 0, widthRatio * 32, heightRatio * 1);
        fourthRoom.addWall(wall);
        wall = new Wall(widthRatio * 0, heightRatio * 16, widthRatio * 32, heightRatio * 1);
        fourthRoom.addWall(wall);
        wall = new Wall(widthRatio * 0, heightRatio * 0, widthRatio * 1, heightRatio * 18);
        fourthRoom.addWall(wall);
        wall = new Wall(widthRatio * 31, heightRatio * 0, widthRatio * 1, heightRatio * 18);
        fourthRoom.addWall(wall);

        // Adds the 3 enemies in the room
        enemy1 = enemyFactory.spawnEnemy("dog", 4F * widthRatio,
                1F * heightRatio, difficulty, widthRatio,
                heightRatio, "DOWN", 50);
        fourthRoom.addEnemy(enemy1);
        player.addEnemyObserver(enemy1);

        enemy2 = enemyFactory.spawnEnemy("dog", 12F * widthRatio,
                16F * heightRatio, difficulty, widthRatio,
                heightRatio, "UP", 50);
        fourthRoom.addEnemy(enemy2);
        player.addEnemyObserver(enemy2);

        enemy3 = enemyFactory.spawnEnemy("dawg", 26F * widthRatio,
                5F * heightRatio, difficulty, widthRatio,
                heightRatio, "LEFT", 40);
        fourthRoom.addEnemy(enemy3);
        player.addEnemyObserver(enemy3);

        return fourthRoom;
    }

    public void checkDoorCollision() {
        // Check collision with the door and transition to the next room
        double playerX = player.getX();
        double playerY = player.getY();
        double playerWidth = player.getWidth();
        double playerHeight = player.getHeight();
        double doorX = door.getX();
        double doorY = door.getY();
        double doorWidth = door.getWidth();
        double doorHeight = door.getHeight();

        /*
        Creates a rectangle around dot, and checks for an intersection between player rect and
        dot rect. Intersection = collision.
         */
        RectF playerBounds = new RectF((float) playerX, (float) playerY,
                (float) (playerX + playerWidth), (float) (playerY + playerHeight));
        RectF doorBounds = new RectF((float) doorX, (float) doorY,
                (float) (doorX + doorWidth), (float) (doorY + doorHeight));

        if (playerBounds.intersect(doorBounds)) {
            switch (currentRoomNum) {
            case 1:
                setPlayerLocation(30, 8);
                setDoorLocation(22, 15, true);
                currentRoomNum++;
                currentRoom = room2();
                roomChanged = true;
                break;
            case 2:
                setPlayerLocation(22, 2);
                setDoorLocation(30, 11, false);
                currentRoomNum++;
                currentRoom = room3();
                roomChanged = true;
                break;
            case 3:
                currentRoomNum++;
                currentRoom = room4();
                setPlayerLocation(2, 11);
                setDoorLocation(28, 13, false);
                roomChanged = true;
                break;
            default:
                currentRoomNum++;
                roomChanged = true;
                setPlayerData();
            }
        }
    }

    public boolean checkWallCollision(double directionX, double directionY) {
        for (Wall wall : currentRoom.getWalls()) {
            // Check collision with walls and handle it
            double playerX = player.getX() + directionX;
            double playerY = player.getY() + directionY;
            double playerWidth = player.getWidth();
            double playerHeight = player.getHeight();
            double wallX = wall.getX();
            double wallY = wall.getY();
            double wallWidth = wall.getWidth();
            double wallHeight = wall.getHeight();

            /*
            Creates a rectangle around dot, and checks for an intersection between player rect and
            dot rect. Intersection = collision.
             */
            RectF playerBounds = new RectF((float) playerX, (float) playerY,
                    (float) (playerX + playerWidth), (float) (playerY + playerHeight));
            RectF wallBounds = new RectF((float) wallX, (float) wallY,
                    (float) (wallX + wallWidth), (float) (wallY + wallHeight));
            if (playerBounds.intersect(wallBounds)) {
                return true;
            }
        }
        return false;
    }

    public void setPlayerData() {
        String currentTime = Calendar.getInstance().getTime().toString();
        player = Player.getPlayer();
        player.setMovementSpeed(0, 0);
        player.setName(playername);
        player.setScore(scoreValue);
        player.setDateTime(currentTime);

    }

    public void setPlayerLocation(double x, double y) {
        // Set the player's location
        player = Player.getPlayer();
        player.setPosition(widthRatio * x, heightRatio * y);
    }

    public void setDoorLocation(double x, double y, boolean landscape) {
        // Set the door's location
        if (landscape) {
            door = new Door(widthRatio * x, heightRatio * y, widthRatio * 2, heightRatio);
        } else {
            door = new Door(widthRatio * x, heightRatio * y, widthRatio, 2 * heightRatio);
        }
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public void setEnemyHealth(double enemyHealth) {
        this.enemyHealth = enemyHealth;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public Player getPlayer() {
        return  Player.getPlayer();
    }

    public int getCurrentRoomNum() {
        return currentRoomNum;
    }

    public double getWidthRatio() {
        return widthRatio;
    }

    public double getHeightRatio() {
        return heightRatio;
    }

    public ArrayList<Enemy> getEnemy() {
        return currentRoom.getEnemies();
    }

    public boolean isRoomChanged() {
        return roomChanged;
    }

    public void setRoomChanged() {
        this.roomChanged = false;
    }

    public void setPlayerHealth(double startHealth) {
        player.setHealth(startHealth);
    }
}

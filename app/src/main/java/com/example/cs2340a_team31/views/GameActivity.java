package com.example.cs2340a_team31.views;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import com.example.cs2340a_team31.model.*;
import com.example.cs2340a_team31.model.strategypattern.MoveDownStrategy;
import com.example.cs2340a_team31.model.strategypattern.MoveLeftStrategy;
import com.example.cs2340a_team31.model.strategypattern.MoveRightStrategy;
import com.example.cs2340a_team31.model.strategypattern.MoveUpStrategy;
import com.example.cs2340a_team31.viewmodels.*;
import com.example.cs2340a_team31.R;

public class GameActivity extends AppCompatActivity {
    //private PlayerView playerView;

    private ImageView playerView;

    private Door door;
    private Player player;

    private ConstraintLayout gameLayout;

    private View gameView;

    private int screenWidth;
    private int screenHeight;
    private Timer scoreTimer;
    private int currentRoomNum = 1;

    private int[] scoreValue;
    private String playername;

    private double widthRatio;
    private double heightRatio;

    private ArrayList<Room> rooms = new ArrayList<Room>();
    private Room currentRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        gameLayout = findViewById(R.id.gameLayout);
        gameLayout.setBackgroundResource(R.drawable.room1);
        gameView = findViewById(R.id.gameView);
        screenWidth = gameView.getResources().getDisplayMetrics().widthPixels;
        screenHeight = gameView.getResources().getDisplayMetrics().heightPixels;
        Log.d("My app:", "Height: " + screenHeight);
        Log.d("My app:", "Width: " + screenWidth);



        TextView playerName = findViewById(R.id.playerName);
        TextView playerHealth = findViewById(R.id.playerHealthDisplay);
        TextView enemyDamage = findViewById(R.id.enemyDamageDisplay);
        TextView score = findViewById(R.id.scoreDisplay);

        playerView = findViewById(R.id.playerView);


        // Retrieves saved variables from config screen
        playername = getIntent().getStringExtra("PLAYER_NAME");
        int startHealth  = getIntent().getIntExtra("STARTING_HEALTH", 100);
        int enemydamage = getIntent().getIntExtra("ENEMY_DAMAGE", 20);
        scoreValue = new int[]{getIntent().getIntExtra("SCORE", 100)};


        String selectedCharacter = getIntent().getStringExtra("SELECTED_CHARACTER");

        // Updates components on game screen
        playerName.append(playername);
        playerHealth.append(" " + startHealth);
        enemyDamage.append(" " + enemydamage);
        switch (selectedCharacter) {
        case "char1":
            playerView.setImageDrawable(getResources().
                    getDrawable(R.drawable.astrokitty_green, getApplicationContext().getTheme()));
            break;
        case "char2":
            playerView.setImageDrawable(getResources().
                    getDrawable(R.drawable.astrokitty_blue, getApplicationContext().getTheme()));
            break;
        default:
            playerView.setImageDrawable(getResources().
                    getDrawable(R.drawable.astrokitty_pink, getApplicationContext().getTheme()));
        }

        //gameLayout.addView(playerView);

        //sets player position and updates playerView
        player = Player.getPlayer();
        player.setPosition(widthRatio, heightRatio);
        player.setSize(playerView.getDrawable().getIntrinsicWidth() * 0.75,
                playerView.getDrawable().getIntrinsicHeight() * 0.75);
        playerView.setX((float) player.getX());
        playerView.setY((float) player.getY());

        calculateRatio();
        makeRooms();
        currentRoom = rooms.get(0);

        setDoorLocation(22, 16, true);

        // score timer that counts down
        scoreTimer = new Timer();
        scoreTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (scoreValue[0] > 0) {
                            scoreValue[0]--;
                        }

                        score.setText("Score: " + scoreValue[0]);
                    }
                });
            }
        }, 0, 1000); // Check every second
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        player = Player.getPlayer();

        float num = 0;

        switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_LEFT:
            player.setMovementStrategy(new MoveLeftStrategy());
            break;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            player.setMovementStrategy(new MoveRightStrategy());
            num = (float) (screenWidth - player.getWidth());
            break;
        case KeyEvent.KEYCODE_DPAD_DOWN:
            player.setMovementStrategy(new MoveDownStrategy());
            num = (float) (screenHeight - player.getHeight());
            break;
        case KeyEvent.KEYCODE_DPAD_UP:
            player.setMovementStrategy(new MoveUpStrategy());
            break;
        default:
            break;
        }

        player.move(num);

        for (Wall wall:currentRoom.getWalls()) {
            if (checkWallCollision(wall)) {
                num = 0;

                switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    player.setMovementStrategy(new MoveRightStrategy());
                    num = (float) (screenWidth - player.getWidth());
                    break;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    player.setMovementStrategy(new MoveLeftStrategy());
                    break;
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    player.setMovementStrategy(new MoveUpStrategy());
                    break;
                case KeyEvent.KEYCODE_DPAD_UP:
                    player.setMovementStrategy(new MoveDownStrategy());
                    num = (float) (screenHeight - player.getHeight());
                    break;
                default:
                    break;
                }

                player.move(num);
            }
        }




        Log.d("My app:", "X: " + player.getX());
        Log.d("My app:", "Y: " + player.getY());
        Log.d("My app:", "doorX: " + door.getX());
        Log.d("My app:", "doorY: " + door.getY());
        Log.d("My app:", "doorW: " + door.getWidth());
        Log.d("My app:", "doorH: " + door.getHeight());

        PlayerView playerview = new PlayerView();
        player.addObserver(playerview); // Tracks the observer
        playerView.setX((float) player.getX());
        playerView.setY((float) player.getY());

        player.notifyObservers();

        checkDoorCollision();
        // check collisions here
        return true;
    }

    private void checkDoorCollision() {
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
        Rect playerBounds = new Rect((int) playerX, (int) playerY,
                (int) (playerX + playerWidth), (int) (playerY + playerHeight));
        Rect doorBounds = new Rect((int) doorX, (int) doorY,
                (int) (doorX + doorWidth), (int) (doorY + doorHeight));

        if (playerBounds.intersect(doorBounds)) {
            switch (currentRoomNum) {
            case 1:
                gameLayout.setBackgroundResource(R.drawable.room2);
                setPlayerLocation(22, 2);
                setDoorLocation(30, 11, false);
                currentRoomNum++;
                currentRoom = rooms.get(1);
                break;
            case 2:
                gameLayout.setBackgroundResource(R.drawable.room3);
                setPlayerLocation(2, 11);
                setDoorLocation(17, 2, true);
                currentRoomNum++;
                currentRoom = rooms.get(2);
                break;
            case 3:
                gameLayout.setBackgroundResource(R.drawable.room4);
                currentRoomNum++;
                currentRoom = rooms.get(3);
                setPlayerLocation(17, 14);
                setDoorLocation(28, 16, true);
                break;
            default:
                Intent intent = new Intent(GameActivity.this, GameEndActivity.class);
                startActivity(intent);
                String currentTime = Calendar.getInstance().getTime().toString();
                player = Player.getPlayer();
                player.setName(playername);
                player.setScore(scoreValue[0]);
                player.setDateTime(currentTime);


                finish();
            }
        }
    }

    private boolean checkWallCollision(Wall wall) {
        double playerX = player.getX();
        double playerY = player.getY();
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
        Rect playerBounds = new Rect((int) playerX, (int) playerY,
                (int) (playerX + playerWidth), (int) (playerY + playerHeight));
        Rect doorBounds = new Rect((int) wallX, (int) wallY,
                (int) (wallX + wallWidth), (int) (wallY + wallHeight));

        return playerBounds.intersect(doorBounds);
    }

    public void setPlayerLocation(double x, double y) {
        player = Player.getPlayer();
        player.setPosition(widthRatio * x, heightRatio * y);
        playerView.setX((float) player.getX());
        playerView.setY((float) player.getY());
    }

    public void setDoorLocation(double x, double y, boolean landscape) {
        if (landscape) {
            door = new Door(widthRatio * x, heightRatio * y, widthRatio * 2, heightRatio);
        } else {
            door = new Door(widthRatio * x, heightRatio * y, widthRatio, 2 * heightRatio);
        }
    }

    public void calculateRatio() {
        Drawable backgroundDrawable = gameLayout.getBackground();
        int originalWidth = backgroundDrawable.getIntrinsicWidth();
        int originalHeight = backgroundDrawable.getIntrinsicHeight();

        //widthRatio = (double) screenWidth / originalWidth;
        //heightRatio = (double) screenHeight / originalHeight;

        this.widthRatio = (double) screenWidth / 32.0;
        this.heightRatio = (double) screenHeight / 18.0;

        Log.d("Layout Ratios", "Width Ratio: " + widthRatio);
        Log.d("Layout Ratios", "Height Ratio: " + heightRatio);

    }

    public void makeRooms() {
        Wall wall;
        // Room 1
        Room firstRoom = new Room();
        // First 3 vertical
        wall = new Wall(widthRatio * 4, heightRatio * 1, widthRatio * 0, heightRatio * 2);
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 12, heightRatio * 1, widthRatio * 0, heightRatio * 3);
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 20, heightRatio * 1, widthRatio * 0, heightRatio * 7);
        firstRoom.addWall(wall);
        //2 horizontal
        wall = new Wall(widthRatio * 1, heightRatio * 7, widthRatio * 3, heightRatio * 0);
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 4, heightRatio * 9, widthRatio * 8, heightRatio * 0);
        firstRoom.addWall(wall);
        // other verticals
        wall = new Wall(widthRatio * 4, heightRatio * 7, widthRatio * 0, heightRatio * 6);
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 12, heightRatio * 8, widthRatio * 0, heightRatio * 4);
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 16, heightRatio * 8, widthRatio * 0, heightRatio * 9);
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 8, heightRatio * 13, widthRatio * 0, heightRatio * 4);
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 27, heightRatio * 10, widthRatio * 0, heightRatio * 7);
        firstRoom.addWall(wall);
        // 4 edge walls
        wall = new Wall(widthRatio * 0, heightRatio * 0, widthRatio * 31, heightRatio * 0);
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 0, heightRatio * 17, widthRatio * 31, heightRatio * 0);
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 0, heightRatio * 0, widthRatio * 0, heightRatio * 17);
        firstRoom.addWall(wall);
        wall = new Wall(widthRatio * 31, heightRatio * 0, widthRatio * 0, heightRatio * 17);
        firstRoom.addWall(wall);

        rooms.add(firstRoom);

        Room secondRoom = new Room();

        // 4 ships
        wall = new Wall(widthRatio * 1, heightRatio * 3, widthRatio * 5, heightRatio * 4);
        secondRoom.addWall(wall);
        wall = new Wall(widthRatio * 12, heightRatio * 3, widthRatio * 5, heightRatio * 4);
        secondRoom.addWall(wall);
        wall = new Wall(widthRatio * 3, heightRatio * 10, widthRatio * 5, heightRatio * 4);
        secondRoom.addWall(wall);
        wall = new Wall(widthRatio * 20, heightRatio * 10, widthRatio * 5, heightRatio * 4);
        secondRoom.addWall(wall);
        // 4 edge walls
        wall = new Wall(widthRatio * 0, heightRatio * 0, widthRatio * 31, heightRatio * 0);
        secondRoom.addWall(wall);
        wall = new Wall(widthRatio * 0, heightRatio * 17, widthRatio * 31, heightRatio * 0);
        secondRoom.addWall(wall);
        wall = new Wall(widthRatio * 0, heightRatio * 0, widthRatio * 0, heightRatio * 17);
        secondRoom.addWall(wall);
        wall = new Wall(widthRatio * 31, heightRatio * 0, widthRatio * 0, heightRatio * 17);
        secondRoom.addWall(wall);

        rooms.add(secondRoom);

        Room thirdRoom = new Room();

        // 4 edge walls
        wall = new Wall(widthRatio * 0, heightRatio * 0, widthRatio * 31, heightRatio * 0);
        thirdRoom.addWall(wall);
        wall = new Wall(widthRatio * 0, heightRatio * 17, widthRatio * 31, heightRatio * 0);
        thirdRoom.addWall(wall);
        wall = new Wall(widthRatio * 0, heightRatio * 0, widthRatio * 0, heightRatio * 17);
        thirdRoom.addWall(wall);
        wall = new Wall(widthRatio * 31, heightRatio * 0, widthRatio * 0, heightRatio * 17);
        thirdRoom.addWall(wall);

        rooms.add(thirdRoom);

        Room fourthRoom = new Room();

        // 2 verticals
        wall = new Wall(widthRatio * 9, heightRatio * 1, widthRatio * 0, heightRatio * 4);
        fourthRoom.addWall(wall);
        wall = new Wall(widthRatio * 18, heightRatio * 1, widthRatio * 0, heightRatio * 4);
        fourthRoom.addWall(wall);
        // bathroom
        wall = new Wall(widthRatio * 22, heightRatio * 4, widthRatio * 5, heightRatio * 0);
        fourthRoom.addWall(wall);
        wall = new Wall(widthRatio * 22, heightRatio * 4, widthRatio * 0, heightRatio * 4);
        fourthRoom.addWall(wall);
        wall = new Wall(widthRatio * 22, heightRatio * 8, widthRatio * 1, heightRatio * 0);
        fourthRoom.addWall(wall);
        wall = new Wall(widthRatio * 27, heightRatio * 4, widthRatio * 0, heightRatio * 4);
        fourthRoom.addWall(wall);
        //Closet
        wall = new Wall(widthRatio * 25, heightRatio * 11, widthRatio * 5, heightRatio * 0);
        fourthRoom.addWall(wall);
        wall = new Wall(widthRatio * 25, heightRatio * 11, widthRatio * 0, heightRatio * 3);
        fourthRoom.addWall(wall);
        // 5 electricity
        wall = new Wall(widthRatio * 2, heightRatio * 14, widthRatio * 1, heightRatio * 1);
        fourthRoom.addWall(wall);
        wall = new Wall(widthRatio * 6, heightRatio * 14, widthRatio * 1, heightRatio * 1);
        fourthRoom.addWall(wall);
        wall = new Wall(widthRatio * 10, heightRatio * 14, widthRatio * 1, heightRatio * 1);
        fourthRoom.addWall(wall);
        wall = new Wall(widthRatio * 6, heightRatio * 9, widthRatio * 1, heightRatio * 1);
        fourthRoom.addWall(wall);
        wall = new Wall(widthRatio * 10, heightRatio * 9, widthRatio * 1, heightRatio * 1);
        fourthRoom.addWall(wall);
        // 4 edge walls
        wall = new Wall(widthRatio * 0, heightRatio * 0, widthRatio * 31, heightRatio * 0);
        fourthRoom.addWall(wall);
        wall = new Wall(widthRatio * 0, heightRatio * 17, widthRatio * 31, heightRatio * 0);
        fourthRoom.addWall(wall);
        wall = new Wall(widthRatio * 0, heightRatio * 0, widthRatio * 0, heightRatio * 17);
        fourthRoom.addWall(wall);
        wall = new Wall(widthRatio * 31, heightRatio * 0, widthRatio * 0, heightRatio * 17);
        fourthRoom.addWall(wall);

        rooms.add(fourthRoom);
    }

}

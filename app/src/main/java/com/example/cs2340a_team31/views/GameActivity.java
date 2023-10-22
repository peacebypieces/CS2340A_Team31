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

    private int roomNum = 4;
    private int currentRoom = 1;

    private int[] scoreValue;
    private String playername;

    private double widthRatio;
    private double heightRatio;

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
        player.setPosition(screenWidth / 2, screenHeight / 2);
        player.setSize(playerView.getDrawable().getIntrinsicWidth(),
                playerView.getDrawable().getIntrinsicHeight());
        playerView.setX((float) player.getX());
        playerView.setY((float) player.getY());

        calculateRatio();

        setDoorLocation(22, 17, true);

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

        Log.d("My app:", "X: " + player.getX());
        Log.d("My app:", "Y: " + player.getY());
        Log.d("My app:", "doorX: " + door.getX());
        Log.d("My app:", "doorY: " + door.getY());
        Log.d("My app:", "doorW: " + door.getWidth());
        Log.d("My app:", "doorH: " + door.getHeight());

        playerView.setX((float) player.getX());
        playerView.setY((float) player.getY());

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
            switch (currentRoom) {
            case 1:
                gameLayout.setBackgroundResource(R.drawable.room2);
                setPlayerLocation(22, 2);
                setDoorLocation(31, 11, false);
                currentRoom++;
                break;
            case 2:
                gameLayout.setBackgroundResource(R.drawable.room3);
                setPlayerLocation(2, 11);
                setDoorLocation(17, 2, true);
                currentRoom++;
                break;
            case 3:
                gameLayout.setBackgroundResource(R.drawable.room4);
                currentRoom++;
                setPlayerLocation(17, 16);
                setDoorLocation(17, 2, true);
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

        this.widthRatio = (double) screenWidth / 32;
        this.heightRatio = (double) screenHeight / 18;

        Log.d("Layout Ratios", "Width Ratio: " + widthRatio);
        Log.d("Layout Ratios", "Height Ratio: " + heightRatio);

    }

}

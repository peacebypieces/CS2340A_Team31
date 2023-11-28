package com.example.cs2340a_team31.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.cs2340a_team31.R;
import com.example.cs2340a_team31.model.Player;
import com.example.cs2340a_team31.model.decorator.PowerUp;
import com.example.cs2340a_team31.model.enemyFactoryPattern.Enemy;
import com.example.cs2340a_team31.viewmodels.EnemyView;
import com.example.cs2340a_team31.viewmodels.GameViewModel;
import com.example.cs2340a_team31.viewmodels.PlayerView;
import com.example.cs2340a_team31.viewmodels.PowerUpView;
import com.example.cs2340a_team31.viewmodels.WeaponView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private GameViewModel viewModel;

    private ArrayList<PowerUpView> powerUpViews;

    private PlayerView playerView;

    private WeaponView weaponView;

    private ArrayList<EnemyView> enemyViews;

    private ConstraintLayout gameLayout;

    private Timer gameTimer;

    private volatile boolean keepRunning = true;

    private boolean isGreenCat;
    private boolean isBlueCat;
    private boolean isPinkCat;
    private int[] playerFrames = {R.drawable.astrokitty_green, R.drawable.astrokitty_green2, R.drawable.astrokitty_green3};
    private int[] playerLeftFrames = {R.drawable.astrokitty_greenleft1, R.drawable.astrokitty_greenleft2, R.drawable.astrokitty_greenleft3};

    private int[] playerBlueFrames = {R.drawable.astrokitty_blue, R.drawable.astrokitty_blue2, R.drawable.astrokitty_blue3};
    private int[] playerBlueLeftFrames = {R.drawable.astrokitty_blueleft1, R.drawable.astrokitty_blueleft2, R.drawable.astrokitty_blueleft3};

    private int[] playerPinkFrames = {R.drawable.astrokitty_pink, R.drawable.astrokitty_pink2, R.drawable.astrokitty_pink3};
    private int[] playerPinkLeftFrames = {R.drawable.astrokitty_pinkleft1, R.drawable.astrokitty_pinkleft2, R.drawable.astrokitty_pinkleft3};

    private int currentFrameIndex = 0;
    private Handler handler;
    private final int FRAME_DELAY = 200;
    private boolean isAnimating = false; // To keep track of animation status
    private boolean isMovingRight = false;
    private boolean isMovingLeft = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);


        // Initialize the ViewModel
        viewModel = new GameViewModel();
        enemyViews = new ArrayList<>();
        powerUpViews = new ArrayList<>();

        // Initialize UI components and set listeners
        gameLayout = findViewById(R.id.gameLayout);
        gameLayout.setBackgroundResource(R.drawable.room1);
        View gameView = findViewById(R.id.gameView);
        double screenWidth = gameView.getResources().getDisplayMetrics().widthPixels;
        double screenHeight = gameView.getResources().getDisplayMetrics().heightPixels;

        // Add weapon views to screen
        weaponView = new WeaponView(this);
        gameLayout.addView(weaponView);
        weaponView.setScaleX(-1);
        weaponView.setVisibility(View.VISIBLE);

        // Add enemy views to screen
        for (int i = 0; i < 3; i++) {
            EnemyView enemyView = new EnemyView(this);
            enemyViews.add(enemyView);
            gameLayout.addView(enemyView);
        }

        //add powerUps views per screen
        for (int i = 0; i < 3; i++) {
            PowerUpView powerUpView = new PowerUpView(this);
            powerUpViews.add(powerUpView);
            gameLayout.addView(powerUpView);
        }

        // Add player view to screen
        playerView = new PlayerView(this);
        gameLayout.addView(playerView);

        // player variable is set to player in player class, observer is added
        Player player = Player.getPlayer();
        player.addObserver(playerView);

        setTextViews();

        viewModel.calculateRatio(screenWidth, screenHeight);
        changeRoomBackground();

        updateEnemyViews();



        player.notifyEnemies();

        // makes enemies show up in first room
        updateEnemyViews();
        setEnemyLocation();
        setWeaponLocation();

        // get ratio of tiles idk
        int newWidth = (int) viewModel.getWidthRatio(); // in pixels
        int newHeight = (int) viewModel.getHeightRatio(); // in pixels

        // Set new dimensions for the ImageView
        playerView.getLayoutParams().width = newWidth;
        playerView.getLayoutParams().height = newHeight;

        // Apply scaling to the image within the ImageView
        playerView.setScaleType(AppCompatImageView.
                ScaleType.FIT_XY); // Scale image to fill the ImageView
        playerView.requestLayout(); // Apply the changes to the ImageView

        Button attack = findViewById(R.id.attack);
        attack.setOnClickListener(v -> {
            KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SPACE);
            onKeyDown(KeyEvent.KEYCODE_SPACE, event);
        });

        ImageButton upArrow = findViewById(R.id.upArrow);
        upArrow.setOnClickListener(v -> {
            KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_UP);
            onKeyDown(KeyEvent.KEYCODE_DPAD_UP, event);
        });

        ImageButton downArrow = findViewById(R.id.downArrow);
        downArrow.setOnClickListener(v -> {
            KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_DOWN);
            onKeyDown(KeyEvent.KEYCODE_DPAD_DOWN, event);
        });

        ImageButton leftArrow = findViewById(R.id.leftArrow);
        leftArrow.setOnClickListener(v -> {
            KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_LEFT);
            onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, event);
        });

        ImageButton rightArrow = findViewById(R.id.rightArrow);
        rightArrow.setOnClickListener(v -> {
            KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_RIGHT);
            onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, event);
        });

        attack.bringToFront();
        upArrow.bringToFront();
        downArrow.bringToFront();
        rightArrow.bringToFront();
        leftArrow.bringToFront();

        setCharacter();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Forward key events to the ViewModel
        viewModel.onKeyDown(keyCode, event);
        setPlayerView();

        // checks if room is changed
        if (viewModel.isRoomChanged()) {
            changeRoomBackground();
            viewModel.setRoomChanged();
        }
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                setPlayerView();
                stopMoveLeft();
                moveRight();
                setPlayerView();
                return true;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                setPlayerView();
                stopMoveRight();
                moveLeft();
                setPlayerView();
                return true;
            // Handle other key events as needed

        }
        TextView enemyDamage = findViewById(R.id.enemyDamageDisplay);
        enemyDamage.setText("Player Damage: " + ((int) viewModel.getPlayer().getAttackDamage()));

        if (keyCode == KeyEvent.KEYCODE_SPACE) {
            TextView enemyhealth = findViewById(R.id.enemyHealthDisplay);
            Player player = viewModel.getPlayer();
            List<Enemy> enemies = viewModel.getEnemy();
            for (int i = 0; i < enemies.size(); i++) {
                EnemyView enemyView = enemyViews.get(i);
                Enemy enemy = enemies.get(i);
                if (enemy.isAlive()) {
                    if (player.isCollided(enemy)) {
                  //      weaponView.setVisibility(View.VISIBLE);
                        player.attack(enemy);
                        if (enemy.getHealth() <= 0) {
                            enemy.kill();
                            viewModel.setScoreValue((int) (viewModel.getScoreValue()
                                    + enemy.getEnemyPoint()));
                            enemyView.setVisibility(View.INVISIBLE);
                        }
                        enemyhealth.setText("Enemy HP: " + ((int) enemy.getHealth()));
                        break;
                    } else {
                      //  weaponView.setVisibility(View.INVISIBLE);
                    }
                }
            }

        }

        // this powerup does stuff
        ArrayList<PowerUp> powers = viewModel.getPowerUps();
        for (int i = 0; i < powerUpViews.size(); i++) {
            if (powers.get(i).getStatus()) {
                powerUpViews.get(i).setVisibility(View.INVISIBLE);
            }
        }
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        viewModel.onActivityCreated(savedInstanceState);
        setPlayerView();
    }

    private void setCharacter() {

        String selectedCharacter = getIntent().getStringExtra("SELECTED_CHARACTER");
        switch (selectedCharacter) {
        case "char1":
            playerView.setImageDrawable(getResources().
                    getDrawable(R.drawable.astrokitty_green, getApplicationContext().getTheme()));
            isGreenCat = true;
            break;
        case "char2":
            playerView.setImageDrawable(getResources().
                    getDrawable(R.drawable.astrokitty_blue, getApplicationContext().getTheme()));
            isBlueCat = true;
            break;
        default:
            playerView.setImageDrawable(getResources().
                    getDrawable(R.drawable.astrokitty_pink, getApplicationContext().getTheme()));
            isPinkCat = true;
        }
        setPlayerView();
    }

    public void scalePowerUps(AppCompatImageView powerUpImg) {
        int newWidth = (int) viewModel.getWidthRatio(); // in pixels
        int newHeight = (int) viewModel.getHeightRatio(); // in pixels
        // Set new dimensions for the ImageView
        powerUpImg.getLayoutParams().width = newWidth;
        powerUpImg.getLayoutParams().height = newHeight;
        // Apply scaling to the image within the ImageView
        powerUpImg.setScaleType(AppCompatImageView.ScaleType.FIT_XY);
        // Scale image to fill the ImageView
        powerUpImg.requestLayout(); // Apply the changes to the ImageView
    }

    public void scaleWeapon(AppCompatImageView weaponImg) {
        int newWidth = (int) viewModel.getWidthRatio(); // in pixels
        int newHeight = (int) viewModel.getHeightRatio(); // in pixels
        // Set new dimensions for the ImageView
        weaponImg.getLayoutParams().width = newWidth;
        weaponImg.getLayoutParams().height = newHeight;
        // Apply scaling to the image within the ImageView
        weaponImg.setScaleType(AppCompatImageView.ScaleType.FIT_XY);
        // Scale image to fill the ImageView
        weaponImg.requestLayout(); // Apply the changes to the ImageView
    }

    private void setTextViews() {
        TextView playerName = findViewById(R.id.playerName);
        TextView playerHealth = findViewById(R.id.playerHealthDisplay);

        TextView score = findViewById(R.id.scoreDisplay);


        String playername = getIntent().getStringExtra("PLAYER_NAME");
        double startHealth  = getIntent().getDoubleExtra("STARTING_HEALTH", 100);
        double difficulty = getIntent().getDoubleExtra("ENEMY_DAMAGE", 20);
        int scoreValue = getIntent().getIntExtra("SCORE", 100);

        viewModel.setPlayername(playername);
        viewModel.setPlayerHealth(startHealth);
        viewModel.setScoreValue(scoreValue);
        viewModel.setDifficulty(difficulty);

        // Updates components on game screen
        playerName.append(playername);
        playerHealth.append(" " + ((int) startHealth));


        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (keepRunning) {

                            // Check if health is 0 and go to losing end screen if so
                            if (Player.getPlayer().getHealth() <= 0) {
                                viewModel.setPlayerData();
                                keepRunning = false;
                                Intent intent = new Intent(GameActivity.this,
                                        GameEndActivity.class);
                                intent.putExtra("LOST", true);
                                startActivity(intent);
                                finish();
                            }

                            // Notifies enemies
                            Player player = Player.getPlayer();
                            player.notifyEnemies();

                            setEnemyLocation();
                            setWeaponLocation();

                            playerHealth.setText("Health:" + ((int) player.getHealth()));


                            score.setText("Score: " + viewModel.getScoreValue());
                        }
                    }
                });
            }
        }, 0, 100); // Check every second
    }

    private void setPlayerView() {
        Player player = Player.getPlayer();
        player.notifyObservers();
    }

    private void changeRoomBackground() {
        int currentRoomNum = viewModel.getCurrentRoomNum();
        List<Enemy> enemies = viewModel.getEnemy();
        switch (currentRoomNum) {
        case 1:
            break;
        case 2:
            gameLayout.setBackgroundResource(R.drawable.room2);
            break;
        case 3:
            gameLayout.setBackgroundResource(R.drawable.room3);
            break;
        case 4:
            gameLayout.setBackgroundResource(R.drawable.room4);
            break;
        default:
            Intent intent = new Intent(GameActivity.this,
                    GameEndActivity.class);
            intent.putExtra("LOST", false);
            startActivity(intent);
            finish();
        }
        updateEnemyViews();
        for (int i = 0; i < enemies.size(); i++) {
            EnemyView enemyView = enemyViews.get(i);
            enemyView.setVisibility(View.VISIBLE);
        }

        ArrayList<PowerUp> powerUps = viewModel.getPowerUps();
        for (int i = 0; i < powerUpViews.size(); i++) {
            powerUpViews.get(i).setVisibility(View.VISIBLE);
            powerUps.get(i).setStatus(false);
        }

        TextView enemyDamage = findViewById(R.id.enemyDamageDisplay);
        enemyDamage.setText("Player Damage: " + ((int) viewModel.getPlayer().getAttackDamage()));

    }

    public void scaleEnemies(AppCompatImageView enemyimg, Enemy enemy) {

        int newWidth = (int) enemy.getWidth(); // in pixels
        int newHeight = (int) enemy.getHeight(); // in pixels

        // Set new dimensions for the ImageView
        enemyimg.getLayoutParams().width = newWidth;
        enemyimg.getLayoutParams().height = newHeight;

        // Apply scaling to the image within the ImageView
        enemyimg.setScaleType(AppCompatImageView.ScaleType.FIT_XY);

        // Scale image to fill the ImageView
        enemyimg.requestLayout(); // Apply the changes to the ImageView
    }

    public void setEnemyLocation() {
        ArrayList<Enemy> enemies = viewModel.getEnemy();

        for (int i = 0; i < enemyViews.size(); i++) {
            EnemyView enemyView = enemyViews.get(i);
            Enemy enemy = enemies.get(i);
            enemyView.updatePlayerPosition((float) enemy.getX(), (float) enemy.getY());
        }
    }

    public void setWeaponLocation() {
        Player player = viewModel.getPlayer();
        weaponView.set((float) player.getX(), (float) player.getY());
    }

    public void setPowerUpLocations() {
        ArrayList<PowerUp> powerUps = viewModel.getPowerUps();

        for (int i = 0; i < powerUps.size(); i++) {
            PowerUpView powerUpView = powerUpViews.get(i);
            PowerUp powerUp = powerUps.get(i);
            powerUpView.updatePlayerPosition((float) powerUp.getX(), (float) powerUp.getY());
        }
    }

    /* get enemies array from view model
     *
     */
    private void updateEnemyViews() {
        ArrayList<PowerUp> powerUps = viewModel.getPowerUps();
        for (int i = 0; i < powerUps.size(); i++) {
            PowerUpView powerUpView = powerUpViews.get(i);
            scalePowerUps(powerUpView);
            PowerUp powerUp = powerUps.get(i);
            String type = powerUp.getType();
            switch (type) {
            case "AttackPowerUp":
                powerUpView.setImageDrawable(getResources().
                        getDrawable(R.drawable.strength_potion,
                                getApplicationContext().getTheme()));
                break;
            case "HealthPowerUp":
                powerUpView.setImageDrawable(getResources().
                        getDrawable(R.drawable.health_potion,
                                getApplicationContext().getTheme()));
                break;
            case "ShieldPowerUp":
                powerUpView.setImageDrawable(getResources().
                        getDrawable(R.drawable.shield_potion,
                                getApplicationContext().getTheme()));
                break;
            default:
                powerUpView.setImageDrawable(getResources().
                        getDrawable(R.drawable.ic_launcher_background,
                                getApplicationContext().getTheme()));
                break;
            }
        }


        String weaponType = viewModel.getWeapon().getWeapon();
        scaleWeapon(weaponView);

        switch (weaponType) {
        case "wood":
            weaponView.setImageResource(R.drawable.wood_sword);
            break;
        case "stone":
            weaponView.setImageResource(R.drawable.stone_sword);
            break;
        case "gold":
            weaponView.setImageResource(R.drawable.gold_sword);
            break;
        case "iron":
            weaponView.setImageResource(R.drawable.iron_sword);
            break;
        case "emerald":
            weaponView.setImageResource(R.drawable.emerald_sword);
            break;
        case "redstone":
            weaponView.setImageResource(R.drawable.redstone_sword);
            break;
        case "diamond":
            weaponView.setImageResource(R.drawable.diamond_sword);
            break;
        case "netherite":
            weaponView.setImageResource(R.drawable.netherite_sword);
            break;
        default:
            break;
        }

        ArrayList<Enemy> enemies = viewModel.getEnemy();

        for (int i = 0; i < enemies.size(); i++) {
            EnemyView enemyView = enemyViews.get(i);
            Enemy enemy = enemies.get(i);
            String type = enemy.getType();

            switch (type) {
            case "mice":
                enemyView.setImageDrawable(getResources().
                        getDrawable(R.drawable.fox,
                                getApplicationContext().getTheme()));
                scaleEnemies(enemyView, enemy);
                break;

            case "dawg":
                enemyView.setImageDrawable(getResources().
                        getDrawable(R.drawable.thwg,
                                getApplicationContext().getTheme()));
                scaleEnemies(enemyView, enemy);
                break;

            case "rat":
                enemyView.setImageDrawable(getResources().
                        getDrawable(R.drawable.rat2,
                                getApplicationContext().getTheme()));
                scaleEnemies(enemyView, enemy);
                break;

            case "dog":
                enemyView.setImageDrawable(getResources().
                        getDrawable(R.drawable.woof,
                                getApplicationContext().getTheme()));
                scaleEnemies(enemyView, enemy);
                break;
            default:
                break;
            }
        }
        setPowerUpLocations();
        setEnemyLocation();
        setWeaponLocation();
    }

    // Other UI-related methods

    private void startPlayerAnimation() {
        int[] playerRightFrames = null;
        if (isGreenCat) {
            playerRightFrames = playerFrames;
        } else if (isBlueCat) {
            playerRightFrames = playerBlueFrames;
        } else {
            playerRightFrames = playerPinkFrames;
        }

        int[] finalPlayerRightFrames = playerRightFrames;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Set the player's view to the next frame in the animation sequence
                playerView.setImageDrawable(getResources().getDrawable(finalPlayerRightFrames[currentFrameIndex], getApplicationContext().getTheme()));

                // Increment the frame index, looping back to the first frame if at the end
                currentFrameIndex = (currentFrameIndex + 1) % playerFrames.length;

                // Repeat the animation by calling this method recursively with a delay
                startPlayerAnimation();
            }
        }, FRAME_DELAY);
    }
    private void startPlayerLeftAnimation() {
        int[] playerLeftFrames1 = null;
        if (isGreenCat) {
            playerLeftFrames1 = playerLeftFrames;
        } else if (isBlueCat) {
            playerLeftFrames1 = playerBlueLeftFrames;
        } else {
            playerLeftFrames1 = playerPinkLeftFrames;
        }

        int[] finalPlayerLeftFrames = playerLeftFrames1;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Set the player's view to the next frame in the animation sequence
                playerView.setImageDrawable(getResources().getDrawable(finalPlayerLeftFrames[currentFrameIndex], getApplicationContext().getTheme()));

                // Increment the frame index, looping back to the first frame if at the end
                currentFrameIndex = (currentFrameIndex + 1) % playerFrames.length;

                // Repeat the animation by calling this method recursively with a delay
                startPlayerLeftAnimation();
            }
        }, FRAME_DELAY);
    }
    // Method to start the player's movement animation loop
    private void startPlayerLeftMovementAnimation() {
        handler = new Handler();
        startPlayerLeftAnimation();
    }

    // Method to start the player's movement animation loop
    private void startPlayerMovementAnimation() {
        handler = new Handler();
        startPlayerAnimation();
    }

    // Method to stop the player's movement animation loop
    private void stopPlayerMovementAnimation() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }
    private void stopPlayerLeftMovementAnimation() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }
    private void moveRight() {
        // Start the animation loop if it's not already running
        if (!isAnimating) {
            isAnimating = true;
            isMovingRight = true; // Set the flag for right movement
            startPlayerMovementAnimation();
        }
    }
    private void moveLeft() {
        // Start the animation loop if it's not already running
        if (!isAnimating) {
            isAnimating = true;
            isMovingLeft = true; // Set the flag for right movement
            startPlayerLeftMovementAnimation();
        }
    }
    private void stopMoveRight() {
        // Stop the animation loop if it's related to right movement
        if (isMovingRight) {
            isMovingRight = false;
            isAnimating = false;
            stopPlayerMovementAnimation();
        }
    }
    private void stopMoveLeft() {
        // Stop the animation loop if it's related to right movement
        if (isMovingLeft) {
            isMovingLeft = false;
            isAnimating = false;
            stopPlayerLeftMovementAnimation();
        }
    }
}


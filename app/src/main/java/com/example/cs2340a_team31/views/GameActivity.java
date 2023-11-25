package com.example.cs2340a_team31.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private GameViewModel viewModel;

    private ArrayList<PowerUpView> powerUpViews;

    private PlayerView playerView;

    private ImageView pickedWeapon;

    private ArrayList<EnemyView> enemyViews;

    private ConstraintLayout gameLayout;

    private Timer gameTimer;

    private volatile boolean keepRunning = true;

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

        pickedWeapon = findViewById(R.id.pickedWeapon);

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

        if (keyCode == KeyEvent.KEYCODE_SPACE) {
            TextView enemyhealth = findViewById(R.id.enemyHealthDisplay);
            Player player = viewModel.getPlayer();
            List<Enemy> enemies = viewModel.getEnemy();
            for (int i = 0; i < enemies.size(); i++) {
                EnemyView enemyView = enemyViews.get(i);
                Enemy enemy = enemies.get(i);
                if (enemy.isAlive()) {
                    if (player.isCollided(enemy)) {
                        player.attack(enemy);
                        if (enemy.getHealth() <= 0) {
                            enemy.kill();
                            viewModel.setScoreValue((int) (viewModel.getScoreValue()
                                    + enemy.getEnemyPoint()));
                            enemyView.setVisibility(View.INVISIBLE);
                        }
                        enemyhealth.setText("Enemy HP: " + enemy.getHealth());
                        break;
                    }
                }
            }
        }
        ArrayList<PowerUp> powers = viewModel.getPowerUps();
        for (int i = 0; i < powerUpViews.size(); i++) {
            if (powers.get(i).getStatus() == true) {
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
            break;
        case "char2":
            playerView.setImageDrawable(getResources().
                    getDrawable(R.drawable.astrokitty_blue, getApplicationContext().getTheme()));
            break;
        default:
            playerView.setImageDrawable(getResources().
                    getDrawable(R.drawable.astrokitty_pink, getApplicationContext().getTheme()));
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
        playerHealth.append(" " + startHealth);


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

                            playerHealth.setText("Health:" + player.getHealth());


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
                            getDrawable(R.drawable.shield_potion,
                                    getApplicationContext().getTheme()));
                    break;
                case "SpeedPowerUp":
                    powerUpView.setImageDrawable(getResources().
                            getDrawable(R.drawable.speed_potion,
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

        switch (weaponType) {
        case "wood":
            pickedWeapon.setImageResource(R.drawable.wood_sword);
            break;
        case "stone":
            pickedWeapon.setImageResource(R.drawable.stone_sword);
            break;
        case "gold":
            pickedWeapon.setImageResource(R.drawable.gold_sword);
            break;
        case "iron":
            pickedWeapon.setImageResource(R.drawable.iron_sword);
            break;
        case "emerald":
            pickedWeapon.setImageResource(R.drawable.emerald_sword);
            break;
        case "redstone":
            pickedWeapon.setImageResource(R.drawable.redstone_sword);
            break;
        case "diamond":
            pickedWeapon.setImageResource(R.drawable.diamond_sword);
            break;
        case "netherite":
            pickedWeapon.setImageResource(R.drawable.netherite_sword);
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
    }

    // Other UI-related methods
}


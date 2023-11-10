package com.example.cs2340a_team31.views;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.cs2340a_team31.R;
import com.example.cs2340a_team31.model.Player;
import com.example.cs2340a_team31.model.enemyFactoryPattern.Enemy;
import com.example.cs2340a_team31.model.observers.EnemyObserver;
import com.example.cs2340a_team31.viewmodels.EnemyView;
import com.example.cs2340a_team31.viewmodels.GameViewModel;
import com.example.cs2340a_team31.viewmodels.PlayerView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private GameViewModel viewModel;

    private PlayerView playerView;
    private EnemyView enemyView;
    private EnemyView enemy2View;

    //private ImageView playerView;

    private ArrayList<ImageView> enemyViews;

    private ConstraintLayout gameLayout;

    private Timer scoreTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);

        // Initialize the ViewModel
        viewModel = new GameViewModel(getApplicationContext());

        // Initialize UI components and set listeners
        gameLayout = findViewById(R.id.gameLayout);
        gameLayout.setBackgroundResource(R.drawable.room1);
        View gameView = findViewById(R.id.gameView);
        double screenWidth = gameView.getResources().getDisplayMetrics().widthPixels;
        double screenHeight = gameView.getResources().getDisplayMetrics().heightPixels;

        viewModel.calculateRatio(screenWidth, screenHeight);

        setTextViews();

        //playerView = findViewById(R.id.playerView);

        // Add player view to screen
        playerView = new PlayerView(this);
        gameLayout.addView(playerView);

        // Add enemy view to screen
        enemyView = new EnemyView(this);
        gameLayout.addView(enemyView);

        // add enemy 2 view to screen
        enemy2View = new EnemyView(this);
        gameLayout.addView(enemy2View);

        // makes enemies show up in first room
        updateEnemyViews();

        // player variable is set to player in player class, observer is added
        Player player = Player.getPlayer();
        player.addObserver(playerView);

        // get ratio of tiles idk
        int newWidth = (int) viewModel.getWidthRatio(); // in pixels
        int newHeight = (int) viewModel.getHeightRatio(); // in pixels

        // Set new dimensions for the ImageView
        playerView.getLayoutParams().width = newWidth;
        playerView.getLayoutParams().height = newHeight;

        // Apply scaling to the image within the ImageView
        playerView.setScaleType(AppCompatImageView.ScaleType.FIT_XY); // Scale image to fill the ImageView
        playerView.requestLayout(); // Apply the changes to the ImageView

        setCharacter();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Forward key events to the ViewModel
        viewModel.onKeyDown(keyCode, event);
        setPlayerView();
        checkRoomBackground();
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

    private void setTextViews() {
        TextView playerName = findViewById(R.id.playerName);
        TextView playerHealth = findViewById(R.id.playerHealthDisplay);
        TextView enemyDamage = findViewById(R.id.enemyDamageDisplay);
        TextView score = findViewById(R.id.scoreDisplay);

        String playername = getIntent().getStringExtra("PLAYER_NAME");
        int startHealth  = getIntent().getIntExtra("STARTING_HEALTH", 100);
        int enemydamage = getIntent().getIntExtra("ENEMY_DAMAGE", 20);
        int scoreValue = getIntent().getIntExtra("SCORE", 100);

        viewModel.setPlayername(playername);
        viewModel.setScoreValue(scoreValue);

        // Updates components on game screen
        playerName.append(playername);
        playerHealth.append(" " + startHealth);
        enemyDamage.append(" " + enemydamage);

        scoreTimer = new Timer();
        scoreTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (viewModel.getScoreValue() > 0) {
                            viewModel.setScoreValue(viewModel.getScoreValue() - 1);
                        }

                        score.setText("Score: " + viewModel.getScoreValue());
                    }
                });
            }
        }, 0, 1000); // Check every second
    }

    private void setPlayerView() {
        Player player = viewModel.getPlayer();
        player.notifyObservers();
        player.notifyEnemies();
    }

    private void checkRoomBackground() {
        int currentRoomNum = viewModel.getCurrentRoomNum();
        switch (currentRoomNum) {
        case 1:
            updateEnemyViews();
            break;
        case 2:
            gameLayout.setBackgroundResource(R.drawable.room2);
            updateEnemyViews();
            break;
        case 3:
            gameLayout.setBackgroundResource(R.drawable.room3);
            updateEnemyViews();
            break;
        case 4:
            gameLayout.setBackgroundResource(R.drawable.room4);
            updateEnemyViews();
            break;
        default:
            Intent intent = new Intent(GameActivity.this, GameEndActivity.class);
            startActivity(intent);
            finish();
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

        enemyView.updatePlayerPosition(enemies.get(0).getX(), enemies.get(0).getY());
        enemy2View.updatePlayerPosition(enemies.get(1).getX(), enemies.get(1).getY());
    }

    /* get enemies array from view model
     *
     */
    private void updateEnemyViews() {

        ArrayList<Enemy> enemies = viewModel.getEnemy();

        String enemy1 = enemies.get(0).getType();
        String enemy2 = enemies.get(1).getType();

        switch (enemy1) {
            case "mice":
                enemyView.setImageDrawable(getResources().
                        getDrawable(R.drawable.fox,
                                getApplicationContext().getTheme()));
                scaleEnemies(enemyView, enemies.get(0));
                break;

            case "dawg":
                enemyView.setImageDrawable(getResources().
                        getDrawable(R.drawable.thwg,
                                getApplicationContext().getTheme()));
                scaleEnemies(enemy2View, enemies.get(0));
                break;

            case "rat":
                enemyView.setImageDrawable(getResources().
                        getDrawable(R.drawable.rat2,
                                getApplicationContext().getTheme()));
                scaleEnemies(enemyView, enemies.get(0));
                break;

            case "dog":
                enemyView.setImageDrawable(getResources().
                        getDrawable(R.drawable.woof,
                                getApplicationContext().getTheme()));
                scaleEnemies(enemyView, enemies.get(0));
                break;
        }

        switch (enemy2) {
            case "mice":
                enemy2View.setImageDrawable(getResources().
                        getDrawable(R.drawable.fox,
                                getApplicationContext().getTheme()));
                scaleEnemies(enemy2View, enemies.get(1));
                break;

            case "dawg":
                enemy2View.setImageDrawable(getResources().
                        getDrawable(R.drawable.thwg,
                                getApplicationContext().getTheme()));
                scaleEnemies(enemy2View, enemies.get(1));
                break;

            case "rat":
                enemy2View.setImageDrawable(getResources().
                        getDrawable(R.drawable.rat2,
                                getApplicationContext().getTheme()));
                scaleEnemies(enemy2View, enemies.get(1));
                break;

            case "dog":
                enemy2View.setImageDrawable(getResources().
                        getDrawable(R.drawable.woof,
                                getApplicationContext().getTheme()));
                scaleEnemies(enemy2View, enemies.get(1));
                break;
        }

        setEnemyLocation();

    }

    // Other UI-related methods
}


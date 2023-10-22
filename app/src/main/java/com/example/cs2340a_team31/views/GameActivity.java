package com.example.cs2340a_team31.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import com.example.cs2340a_team31.model.*;
import com.example.cs2340a_team31.strategypattern.MoveDownStrategy;
import com.example.cs2340a_team31.strategypattern.MoveLeftStrategy;
import com.example.cs2340a_team31.strategypattern.MoveRightStrategy;
import com.example.cs2340a_team31.strategypattern.MoveUpStrategy;
import com.example.cs2340a_team31.viewmodels.*;
import com.example.cs2340a_team31.R;

public class GameActivity extends AppCompatActivity {
    private PlayerView playerView;
    private Player player;

    private ConstraintLayout gameLayout;

    private int screenWidth;
    private int screenHeight;
    private Timer scoreTimer;

    private int roomNum = 4;
    private int currentRoom = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        gameLayout = findViewById(R.id.gameLayout);
        gameLayout.setBackgroundResource(R.drawable.room1);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;

        Button endButton = findViewById(R.id.endbutton);
        TextView playerName = findViewById(R.id.playerName);
        TextView playerHealth = findViewById(R.id.playerHealthDisplay);
        TextView enemyDamage = findViewById(R.id.enemyDamageDisplay);
        TextView score = findViewById(R.id.scoreDisplay);

        playerView = new PlayerView(this,(float) 200, (float) 200);
        gameLayout.addView(playerView);

        // Retrieves saved variables from config screen
        String playername = getIntent().getStringExtra("PLAYER_NAME");
        int startHealth  = getIntent().getIntExtra("STARTING_HEALTH", 100);
        int enemydamage = getIntent().getIntExtra("ENEMY_DAMAGE", 20);
        final int[] scoreValue = {getIntent().getIntExtra("SCORE", 100)};

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


        endButton.setOnClickListener(v -> {
            switch (currentRoom) {
            case 1:
                gameLayout.setBackgroundResource(R.drawable.room2);
                currentRoom++;
                break;
            case 2:
                gameLayout.setBackgroundResource(R.drawable.room3);
                currentRoom++;
                break;
            case 3:
                gameLayout.setBackgroundResource(R.drawable.room4);
                currentRoom++;
                endButton.setText("End Game");
                break;
            default:
                Intent intent = new Intent(GameActivity.this, GameEndActivity.class);
                startActivity(intent);
                String currentTime = Calendar.getInstance().getTime().toString();
                Player player = Player.getPlayer();
                player.setName(playername);
                player.setScore(scoreValue[0]);
                player.setDateTime(currentTime);


                finish();
            }
        });

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

        switch(keyCode){
            case KeyEvent.KEYCODE_DPAD_LEFT:
                player.setMovementStrategy(new MoveLeftStrategy());
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                player.setMovementStrategy(new MoveRightStrategy());
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                player.setMovementStrategy(new MoveDownStrategy());
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                player.setMovementStrategy(new MoveUpStrategy());
                break;
        }

        player.move();
        playerView.updatePosition((float) player.getX(),(float) player.getY());
        // check collisions here
        return true;
    }

}

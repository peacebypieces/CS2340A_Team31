package com.example.cs2340a_team31;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class GameActivity extends AppCompatActivity {
    private PlayerView playerView;
    private Player player;

    private ConstraintLayout gameLayout;

    private int screenWidth;
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        gameLayout = findViewById(R.id.gameLayout);
        //screenWidth = getResources().getDisplayMetrics().widthPixels;
        //screenHeight = getResources().getDisplayMetrics().heightPixels;

        Button endButton = findViewById(R.id.endbutton);
        TextView playerName = findViewById(R.id.playerName);
        TextView playerHealth = findViewById(R.id.playerHealthDisplay);
        TextView enemyDamage = findViewById(R.id.enemyDamageDisplay);
        //TODO Change this to use playerView variable instead
        ImageView playerIcon = findViewById(R.id.playerCharacter);
        Button restartButton = findViewById(R.id.restartBtn);

        // Retrieves saved variables from config screen
        String playername = getIntent().getStringExtra("PLAYER_NAME");
        int startHealth  = getIntent().getIntExtra("STARTING_HEALTH", 100);
        int enemydamage = getIntent().getIntExtra("ENEMY_DAMAGE", 20);
        String selectedCharacter = getIntent().getStringExtra("SELECTED_CHARACTER");

        // Updates components on game screen
        playerName.append(playername);
        playerHealth.append("" + startHealth);
        enemyDamage.append("" + enemydamage);
        switch (selectedCharacter) {
        case "char1":
            playerIcon.setImageDrawable(getResources().
                    getDrawable(R.drawable.char1, getApplicationContext().getTheme()));
            break;
        case "char2":
            playerIcon.setImageDrawable(getResources().
                    getDrawable(R.drawable.char2, getApplicationContext().getTheme()));
            break;
        default:
            playerIcon.setImageDrawable(getResources().
                    getDrawable(R.drawable.char3, getApplicationContext().getTheme()));
        }


        endButton.setOnClickListener(v -> {
            Intent intent = new Intent(GameActivity.this, GameEndActivity.class);
            startActivity(intent);
            finish();
        });

    }
}

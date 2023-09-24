package com.example.cs2340a_team31;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class GameActivity extends AppCompatActivity {
    private PlayerView playerView;
    private Player player;

    ConstraintLayout gameLayout;

    int screenWidth;
    int screenHeight;

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
        ImageView playerIcon = findViewById(R.id.playerCharacter);


        String playername = getIntent().getStringExtra("PLAYER_NAME");
        double startHealth  = getIntent().getDoubleExtra("STARTING_HEALTH", 100.0);
        double enemydamage = getIntent().getDoubleExtra("ENEMY_DAMAGE", 20.0);
        String selectedCharacter = getIntent().getStringExtra("SELECTED_CHARACTER");

        playerName.append(playername);
        playerHealth.append("" + startHealth);
        enemyDamage.append("" + enemydamage);
        switch (selectedCharacter){
            case "char1":
                playerIcon.setImageDrawable(getResources().getDrawable(R.drawable.char1, getApplicationContext().getTheme()));
                break;
            case "char2":
                playerIcon.setImageDrawable(getResources().getDrawable(R.drawable.char2, getApplicationContext().getTheme()));
                break;
            default:
                playerIcon.setImageDrawable(getResources().getDrawable(R.drawable.char3, getApplicationContext().getTheme()));
        }


        endButton.setOnClickListener(v -> {
                Intent intent = new Intent(GameActivity.this, GameEndActivity.class);
                startActivity(intent);
                finish();
        });
    }
}

package com.example.cs2340a_team31;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    private PlayerView playerView;
    private Player player;

    int screenWidth;
    int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;

        Button endButton = findViewById(R.id.endbutton);
        TextView playerName = findViewById(R.id.playerName);
        TextView playerHealth = findViewById(R.id.playerHealthDisplay);
        TextView enemyDamage = findViewById(R.id.enemyDamageDisplay);


        String playername = getIntent().getStringExtra("PLAYER_NAME");
        double startHealth  = getIntent().getDoubleExtra("STARTING_HEALTH", 100.0);
        double enemydamage = getIntent().getDoubleExtra("ENEMY_DAMAGE", 20.0);
        String selectedCharacter = getIntent().getStringExtra("SELECTED_CHARACTER");

        playerName.append(playername);
        playerHealth.append("" + startHealth);
        enemyDamage.append("" + enemydamage);

        endButton.setOnClickListener(v -> {
                Intent intent = new Intent(GameActivity.this, GameEndActivity.class);
                startActivity(intent);
                finish();
        });
    }
}

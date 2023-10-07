package com.example.cs2340a_team31;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class GameConfigActivity extends AppCompatActivity {

    private EditText playerNameEditText;
    private RadioGroup difficultyRadioGroup;
    private RadioGroup characterRadioGroup;
    private int startingHealth = 0;
    private int enemyDamage = 0;
    private int score = 0;
    private String selectedCharacter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_config);

        // Retrieves components from screen
        playerNameEditText = findViewById(R.id.nameInput);
        Button startButton = findViewById(R.id.startbutton2);
        difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
        characterRadioGroup = findViewById(R.id.characterRadioGroup);

        // Defaults health and enemy damage
        this.startingHealth = 100;
        this.enemyDamage = 20;

        // easy mode score
        score = 100;

        startButton.setOnClickListener(v -> {
            String playerName = playerNameEditText.getText().toString().trim();

            if (!playerName.isEmpty()) {
                // updates difficulty if needed
                if (difficultyRadioGroup.getCheckedRadioButtonId() == R.id.radioMedium) {
                    startingHealth = 80;
                    enemyDamage = 25;
                    score = 200;
                } else if (difficultyRadioGroup.getCheckedRadioButtonId() == R.id.radioHard) {
                    startingHealth = 60;
                    enemyDamage = 30;
                    score = 300;
                }

                // sets selected character
                if (characterRadioGroup.getCheckedRadioButtonId() == R.id.char1) {
                    selectedCharacter = "char1";
                } else if (characterRadioGroup.getCheckedRadioButtonId() == R.id.char2) {
                    selectedCharacter = "char2";
                } else {
                    selectedCharacter = "char3";
                }

                Intent intent = new Intent(GameConfigActivity.this, GameActivity.class);
                // Saves variables for game screen
                intent.putExtra("PLAYER_NAME", playerName);
                intent.putExtra("STARTING_HEALTH", startingHealth);
                intent.putExtra("ENEMY_DAMAGE", enemyDamage);
                intent.putExtra("SELECTED_CHARACTER", selectedCharacter);
                intent.putExtra("SCORE", score);
                startActivity(intent);
            } else {
                // Player's name is empty or only contains whitespaces
                Toast.makeText(GameConfigActivity.this, "Please enter a valid name",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package com.example.cs2340a_team31;

import androidx.appcompat.app.AppCompatActivity;
// import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class GameConfig extends AppCompatActivity {

    private EditText playerNameEditText;
    private RadioGroup difficultyRadioGroup;
    private RadioGroup characterRadioGroup;
    private int startingHealth = 0;
    private int enemyDamage = 0;
    private String selectedCharacter = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_config);

        playerNameEditText = findViewById(R.id.editTextText);
        Button startButton = findViewById(R.id.button);
        difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
        characterRadioGroup = findViewById(R.id.characterRadioGroup);
        this.startingHealth = 100;
        this.enemyDamage = 20;
        this.selectedCharacter = "Character 1";

        ImageView imageView = findViewById(R.id.imageView);
        imageView.bringToFront();
        startButton.setOnClickListener(v -> {
            String playerName = playerNameEditText.getText().toString().trim();

            int selectedRadioButtonId = difficultyRadioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

            int selectedCharacterId = characterRadioGroup.getCheckedRadioButtonId();
            RadioButton selectedCharacterRadioButton = findViewById(selectedCharacterId);

            if (!playerName.isEmpty()) {
                if (selectedRadioButton.getId() == R.id.radioMedium) {
                    startingHealth = 80;
                    enemyDamage = 25;
                } else if (selectedRadioButton.getId() == R.id.radioHard) {
                    startingHealth = 60;
                    enemyDamage = 30;
                }
                selectedCharacter = selectedCharacterRadioButton.getText().toString();
                // Intent intent = new Intent(GameConfig.this, YourGameActivity.class);
                // intent.putExtra("PLAYER_NAME", playerName);
                // intent.putExtra("STARTING_HEALTH", startingHealth);
                // intent.putExtra("ENEMY_DAMAGE", enemyDamage);
                // intent.putExtra("SELECTED_CHARACTER", selectedCharacter);
                // startActivity(intent);
            } else {
                // Player's name is empty or only contains whitespaces
                Toast.makeText(GameConfig.this, "Please enter a valid name", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
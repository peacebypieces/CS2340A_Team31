package com.example.cs2340a_team31.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.cs2340a_team31.R;
import com.example.cs2340a_team31.viewmodels.GameConfigViewModel;

public class GameConfigActivity extends AppCompatActivity {

    private EditText playerNameEditText;
    private RadioGroup difficultyRadioGroup;
    private RadioGroup characterRadioGroup;
    private GameConfigViewModel viewModel;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_config);

        viewModel = new ViewModelProvider(this).get(GameConfigViewModel.class);

        playerNameEditText = findViewById(R.id.nameInput);
        Button startButton = findViewById(R.id.startbutton2);
        difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
        characterRadioGroup = findViewById(R.id.characterRadioGroup);

        mediaPlayer = MediaPlayer.create(this, R.raw.background_music1);
        mediaPlayer.start();

        startButton.setOnClickListener(v -> {
            String playerName = playerNameEditText.getText().toString().trim();

            if (!playerName.isEmpty()) {
                // Handle UI interactions and pass data to the ViewModel
                viewModel.handleStartButtonClick(playerName,
                        difficultyRadioGroup.getCheckedRadioButtonId(),
                        characterRadioGroup.getCheckedRadioButtonId());

                // Start the game activity
                Intent intent = new Intent(GameConfigActivity.this, GameActivity.class);

                intent.putExtra("PLAYER_NAME", playerName);
                intent.putExtra("STARTING_HEALTH", viewModel.getStartingHealth());
                intent.putExtra("ENEMY_DAMAGE", viewModel.getEnemyDamage());
                intent.putExtra("SELECTED_CHARACTER", viewModel.getSelectedCharacter());
                intent.putExtra("SCORE", viewModel.getScore());

                startActivity(intent);
                finish();
            } else {
                // Player's name is empty or only contains whitespaces
                Toast.makeText(GameConfigActivity.this, "Please enter a valid name",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}

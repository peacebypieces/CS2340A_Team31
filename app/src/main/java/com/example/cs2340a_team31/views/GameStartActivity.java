package com.example.cs2340a_team31.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.cs2340a_team31.R;

public class GameStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        Button startButton = findViewById(R.id.startbutton);
        Button exitButton = findViewById(R.id.exitbutton);

        // insert next screen
        startButton.setOnClickListener(v -> {
            Intent game = new Intent(GameStartActivity.this, GameConfigActivity.class);
            startActivity(game);
            finish();
        });

        exitButton.setOnClickListener(v -> {
            Intent exit = new Intent(Intent.ACTION_MAIN);
            exit.addCategory(Intent.CATEGORY_HOME);
            exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(exit);
        });
    }
}
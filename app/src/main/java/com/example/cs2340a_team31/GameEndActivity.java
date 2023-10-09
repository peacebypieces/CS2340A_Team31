package com.example.cs2340a_team31;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameEndActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.end_screen);
            TextView currentScore =  findViewById(R.id.currentScore);
            TextView leaderBoard1 = findViewById(R.id.leaderBoard1);
            TextView leaderBoard2 = findViewById(R.id.leaderBoard2);
            TextView leaderBoard3 = findViewById(R.id.leaderBoard3);
            TextView leaderBoard4 = findViewById(R.id.leaderBoard4);
            TextView leaderBoard5 = findViewById(R.id.leaderBoard5);




            Button restartButton = findViewById(R.id.restartBtn);




            restartButton.setOnClickListener(v -> {
                Intent intent = new Intent(GameEndActivity.this, GameStartActivity.class);
                startActivity(intent);
                finish();
            });
        }


}



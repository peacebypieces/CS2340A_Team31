package com.example.cs2340a_team31.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340a_team31.R;
import java.util.LinkedList;

import com.example.cs2340a_team31.model.*;
public class GameEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_screen);

        TextView[] textViews = new TextView[5];

        textViews[0] = findViewById(R.id.leaderBoard1);
        textViews[1] = findViewById(R.id.leaderBoard2);
        textViews[2] = findViewById(R.id.leaderBoard3);
        textViews[3] = findViewById(R.id.leaderBoard4);
        textViews[4] = findViewById(R.id.leaderBoard5);


        //singletons
        Player player = Player.getPlayer();
        LeaderBoard leaderBoard = LeaderBoard.getleaderboard();

        //creating new LeaderBoardplayer
        LeaderBoardPlayer lbPlayer = new LeaderBoardPlayer(player.getName(),
                player.getDateTime(), player.getScore());

        //add the current game to the leaderboard array to be able to sort
        leaderBoard.add(lbPlayer);

        //adding current game to the current textview
        TextView currentScore = findViewById(R.id.currentScore);
        currentScore.setText("Current: " + "Name:" + lbPlayer.getName() + ", "
                                         + "Time:" + lbPlayer.getDateTime() + ", "
                                         + "Score:" + lbPlayer.getScore());

        LinkedList<LeaderBoardPlayer> players = LeaderBoard.getleaderboard().getPlayers();

        //populating the play game data into textviews
        for (int i = 0; i < leaderBoard.getPlayerSize(); i++) {
            lbPlayer = players.get(i);
            textViews[i].setText((i  + 1) + ") " + "Name:" + lbPlayer.getName() + ", "
                    + "Time:" + lbPlayer.getDateTime() + ", "
                    + "Score:" + lbPlayer.getScore());
        }

        Button restartButton = findViewById(R.id.restartBtn);
        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(GameEndActivity.this, GameStartActivity.class);
            startActivity(intent);
            finish();
        });
    }


}



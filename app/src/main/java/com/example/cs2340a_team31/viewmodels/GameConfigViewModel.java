package com.example.cs2340a_team31.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.cs2340a_team31.R;

public class GameConfigViewModel extends ViewModel {
    private String playerName;
    private double startingHealth = 100;
    private double enemyDamage = 1;
    private int score = 100;
    private String selectedCharacter;

    public void handleStartButtonClick(String playerName,
                                       double selectedDifficulty,
                                       double selectedCharacterID) {
        this.playerName = playerName;

        // Handle selected difficulty and set relevant parameters
        if (selectedDifficulty == R.id.radioMedium) {
            startingHealth = 150;
            enemyDamage = 1.5;
            score = 200;
        } else if (selectedDifficulty == R.id.radioHard) {
            startingHealth = 200;
            enemyDamage = 2;
            score = 300;
        }

        // Handle selected character and set the character's identifier
        if (selectedCharacterID == R.id.char1) {
            selectedCharacter = "char1";
        } else if (selectedCharacterID == R.id.char2) {
            selectedCharacter = "char2";
        } else {
            selectedCharacter = "char3";
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public double getStartingHealth() {
        return startingHealth;
    }

    public double getEnemyDamage() {
        return enemyDamage;
    }

    public int getScore() {
        return score;
    }

    public String getSelectedCharacter() {
        return selectedCharacter;
    }
}
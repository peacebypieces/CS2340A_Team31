package com.example.cs2340a_team31.model;
import java.util.LinkedList;

import android.util.Log;

public class LeaderBoard {
    private static volatile LeaderBoard leaderboard;

    private LinkedList<LeaderBoardPlayer> players;

    private LeaderBoard() {
        players = new LinkedList<>();
    }

    public static LeaderBoard getleaderboard() {
        if (leaderboard == null) {
            synchronized (LeaderBoard.class) {
                if (leaderboard == null) {
                    leaderboard = new LeaderBoard();
                }
            }
        }
        return leaderboard;
    }

    public void add(LeaderBoardPlayer player) {
        players.add(player);
        sort();
        removeDuplicates();
        if (players.size() > 5) {
            shorten();
        }
    }

    public void sort() {
        int n = players.size();

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (players.get(j).getScore() > players.get(maxIndex).getScore()) {
                    maxIndex = j;
                }
            }

            // Swap the found minimum element with the first
            // element
            LeaderBoardPlayer temp = players.get(maxIndex);
            players.set(maxIndex, players.get(i));
            players.set(i, temp);
        }
    }

    public void removeDuplicates() {

        // Create a new ArrayList
        LinkedList<LeaderBoardPlayer> newList = new LinkedList<>();

        // Traverse through the first list
        for (LeaderBoardPlayer element : players) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        players = newList;
    }

    public void shorten() {
        LinkedList<LeaderBoardPlayer> newPlayers = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            newPlayers.add(players.removeFirst());
        }
        players = newPlayers;
    }

    public LinkedList<LeaderBoardPlayer> getPlayers() {
        removeDuplicates();
        return players;
    }

    public void debugPlayers() {
        Log.d("array", players.toString());
    }

    public void debugPlayersScores() {
        Log.d("array", " " + players.getLast().getScore());
    }

    public int getPlayerSize() {
        return players.size();
    }



}

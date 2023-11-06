package com.example.cs2340a_team31.model.observers;

import com.example.cs2340a_team31.model.observers.PlayerObserver;

public interface PlayerSubject {
    void addObserver(PlayerObserver observer);
    void removeObserver(PlayerObserver observer);
    void notifyObservers();
}


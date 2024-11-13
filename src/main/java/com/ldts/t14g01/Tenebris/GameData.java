package com.ldts.t14g01.Tenebris;

import com.ldts.t14g01.Tenebris.utils.Difficulty;

public class GameData {
    private Difficulty difficulty;
    private int currentLevel;
    private int currentArena;

    // used when creating a New Game
    public GameData(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.currentLevel = 1;
        this.currentArena = 1;
    }

    // used when Loading an already existing Save
    public GameData(Difficulty difficulty, int level, int arena) {
        this.difficulty = difficulty;
        this.currentLevel = level;
        this.currentArena = arena;
    }

    //getters
    public int getCurrentLevel() {
        return this.currentLevel;
    }

    public int getCurrentArena() {
        return this.currentArena;
    }

    //setters
    public void setCurrentLevel(int level) {
        this.currentLevel = level;
    }

    public void setCurrentArena(int arena) {
        this.currentArena = arena;
    }

}

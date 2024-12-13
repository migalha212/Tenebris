package com.ldts.t14g01.Tenebris.savedata;

import com.ldts.t14g01.Tenebris.utils.Difficulty;

import java.util.ArrayList;
import java.util.List;

public class SaveData {
    private final Difficulty difficulty;
    private int level;

    public SaveData(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.level = 1;
    }

    private SaveData(Difficulty difficulty, int level) {
        this.difficulty = difficulty;
        this.level = level;
    }

    public void save() {
        // ToDo : Implement save function
    }

    public List<SaveData> loadSaves() {
        List<SaveData> saves = new ArrayList<>();

        // ToDo : Implement load function
        return saves;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getLevel() {
        return level;
    }

    public void increaseLevel() {
        this.level++;
    }
}

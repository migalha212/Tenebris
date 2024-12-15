package com.ldts.t14g01.Tenebris.savedata;

import com.ldts.t14g01.Tenebris.utils.Difficulty;

import java.util.Objects;

public class SaveData {
    private final Difficulty difficulty;
    private int level;

    protected SaveData(Difficulty difficulty, int level) {
        this.difficulty = difficulty;
        this.level = level;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getLevel() {
        return level;
    }

    public void increaseLevel() {
        this.level++;
        SaveDataManager.getInstance().triggerUpdate();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SaveData saveData = (SaveData) o;
        return level == saveData.level && difficulty == saveData.difficulty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(difficulty, level);
    }
}

package com.ldts.t14g01.Tenebris.savedata;

import com.ldts.t14g01.Tenebris.utils.Difficulty;

import java.util.Objects;

public class SaveData {
    private static final int MAX_LEVEL = 5;

    private final Difficulty difficulty;
    private int level;
    private boolean lastOpened;

    protected SaveData(Difficulty difficulty, int level, boolean lastOpened) {
        this.difficulty = difficulty;
        this.level = level;
        this.lastOpened = lastOpened;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getLevel() {
        return level;
    }

    public boolean isLastOpened() {
        return this.lastOpened;
    }

    public void setLastOpened() {
        this.lastOpened = true;
    }

    public void removeLastOpened() {
        this.lastOpened = false;
    }

    public void increaseLevel() {
        if (this.level != MAX_LEVEL) this.level++;
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

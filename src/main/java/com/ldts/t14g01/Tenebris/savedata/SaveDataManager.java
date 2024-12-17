package com.ldts.t14g01.Tenebris.savedata;

import com.ldts.t14g01.Tenebris.utils.Difficulty;

public interface SaveDataManager {
    static SaveDataManager getInstance() {
        return FileSystemSaveDataManager.getInstance();
    }

    SaveData createNewSave(Difficulty difficulty);

    void triggerUpdate();

    int getSaveCount();

    SaveData getSave(int number);

    void markAsLastOpen(SaveData saveData);

    SaveData getLastOpen();

    void deleteSave(SaveData saveData);
}

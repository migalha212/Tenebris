package com.ldts.t14g01.Tenebris.savedata;

import com.ldts.t14g01.Tenebris.utils.Difficulty;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileSystemSaveDataManager implements SaveDataManager {
    private static final String SAVE_FILE = "src/main/resources/saves/save.csv";
    private static FileSystemSaveDataManager instance;

    private final List<SaveData> saves;

    private FileSystemSaveDataManager() {
        this.saves = new ArrayList<>();
        this.loadFromFile();
    }

    protected static FileSystemSaveDataManager getInstance() {
        if (instance == null) instance = new FileSystemSaveDataManager();
        return instance;
    }

    private void loadFromFile() {
        this.saves.clear();

        File saveFile = new File(SAVE_FILE);
        try {
            if (!saveFile.exists()) return;

            Scanner x = new Scanner(saveFile);
            x.useDelimiter("[;\\r\\n]");
            while (x.hasNext()) {
                String difficulty1 = x.next();

                Difficulty difficulty = Difficulty.valueOf(difficulty1);
                int level = Integer.parseInt(x.next());

                saves.add(new SaveData(difficulty, level));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SaveData createNewSave(Difficulty difficulty) {
        SaveData newSave = new SaveData(difficulty, 1);
        this.saves.add(newSave);
        this.triggerUpdate();
        return newSave;
    }

    @Override
    public void triggerUpdate() {
        // Save new Save List
        try (FileOutputStream fos = new FileOutputStream(SAVE_FILE, false)) {
            // The 'false' flag ensures overwriting; no append mode
            StringBuilder content = new StringBuilder();

            for (SaveData save : this.saves) {
                content.append(save.getDifficulty().name())
                        .append(";")
                        .append(save.getLevel())
                        .append("\n");
            }

            byte[] contentBytes = content.toString().getBytes();
            fos.write(contentBytes);
        } catch (IOException e) {
            System.err.println("Error While Saving to File: " + e.getMessage());
        }
    }

    @Override
    public int getSaveCount() {
        return this.saves.size();
    }

    @Override
    public SaveData getSave(int number) {
        if (number < 1 || number > this.getSaveCount())
            throw new RuntimeException("Trying to get a Save that doesn't exist");
        return this.saves.get(number - 1);
    }

    @Override
    public void deleteSave(SaveData saveData) {
        assert (saveData != null);

        this.saves.remove(saveData);
        this.triggerUpdate();
    }
}

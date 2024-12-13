package com.ldts.t14g01.Tenebris.savedata;

import com.ldts.t14g01.Tenebris.utils.Difficulty;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaveData {
    private final Difficulty difficulty;
    private int level;
    private int saveNum = 0;
    private static List<SaveData> saves = SaveData.loadSaves();

    public SaveData(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.level = 1;
    }

    //used to make a dummy SaveData that when the game is closed wont be saved
    public SaveData() {
        this.level = 0;
    }

    private SaveData(Difficulty difficulty, int level) {
        this.difficulty = difficulty;
        this.level = level;
    }

    public SaveData(Difficulty difficulty, int level, int saveNum) {
        this.difficulty = difficulty;
        this.level = level;
        this.saveNum = saveNum;
    }

    public void save() {
        // ToDo : Implement save function
        // if the level is 0, it means this was a dummy savefile and will thus not be saved
        String savePath = "src/main/resources/saves/save.csv";
        if (this.level == 0) return;
        File saveFile = new File(savePath);

        try {
            if (this.saveNum != 0) {
                updateSave(saveFile, this.saveNum, savePath);
            } else {
                newSave(saveFile);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not open Save file");
        }
        SaveData.updateSaves();
    }

    // Used for updating an already existing save that was loaded
    public void updateSave(File saveFile, int saveNum, String savePath) throws IOException {
        File temp = new File("src/main/resources/saves/temp.csv");

        FileWriter fw = new FileWriter(temp, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        Scanner x = new Scanner(saveFile);
        x.useDelimiter("[;\n]");

        int i = 1;
        while (x.hasNextLine()) {
            if (i == saveNum) {
                pw.print(x.next() + ";" + this.level + '\n');
                x.nextLine();
            } else {
                i++;
                pw.print(x.nextLine() + '\n');
            }
        }
        x.close();
        pw.flush();
        pw.close();
        saveFile.delete();
        File dump = new File(savePath);
        temp.renameTo(dump);
    }

    // Used to create a new save record
    public void newSave(File saveFile) throws IOException {
        FileWriter fw = new FileWriter(saveFile, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        pw.print(this.difficulty.name() + ';' + this.level + '\n');
        pw.flush();
        pw.close();
    }

    private static List<SaveData> loadSaves() {
        List<SaveData> saves = new ArrayList<>();

        // ToDo : Implement load function
        File saveFile = new File("src/main/resources/saves/save.csv");
        try {
            if (!saveFile.exists()) {
                return null;
            }
            int i = 1;
            Scanner x = new Scanner(saveFile);
            x.useDelimiter("[;\\r\\n]");
            while (x.hasNext()) {
                String difficulty1 = x.next();
                Difficulty diff = Difficulty.valueOf(difficulty1); //Difficulty.valueOf(x.next());
                int lv = Integer.parseInt(x.next());
                saves.add(new SaveData(diff, lv, i));
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public static boolean hasSaves() {
        File saveFile = new File("src/main/resources/saves/save.csv");
        return saveFile.exists() && SaveData.getSaves() != null;
    }

    public static List<SaveData> getSaves() {
        return saves;
    }

    public static void updateSaves() {
        SaveData.saves = loadSaves();
    }
}

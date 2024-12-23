package com.ldts.t14g01.Tenebris.savedata;

import com.ldts.t14g01.Tenebris.utils.Difficulty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSystemSaveDataManagerTest {
    private static final String TEST_SAVE_FILE_NAME = "src/test/resources/saves/save.csv";

    @Test
    void Test1() {
        Assertions.assertNotNull(FileSystemSaveDataManager.getInstance());
        Assertions.assertNotNull(SaveDataManager.getInstance());

        SaveDataManager saveDataManager = SaveDataManager.getInstance();
        Assertions.assertSame(FileSystemSaveDataManager.getInstance(), saveDataManager);
    }

    @Test
    void Test2() {
        String fileContent;
        SaveData saveData;
        FileSystemSaveDataManager saveDataManager = FileSystemSaveDataManager.getInstance();
        saveDataManager.setFileName(TEST_SAVE_FILE_NAME);

        // Check Init Status
        Assertions.assertNull(saveDataManager.getLastOpen());
        Assertions.assertEquals(0, saveDataManager.getSaveCount());

        // Create 1
        saveData = saveDataManager.createNewSave(Difficulty.Easy);
        Assertions.assertEquals(Difficulty.Easy, saveData.getDifficulty());
        Assertions.assertEquals(1, saveData.getLevel());

        // Check File
        try {
            fileContent = Files.readString(Path.of(TEST_SAVE_FILE_NAME));
            Assertions.assertEquals("Easy;1;N\n", fileContent);
        } catch (IOException e) {
            Assertions.fail();
            return;
        }

        // Create 2
        saveData = saveDataManager.createNewSave(Difficulty.Champion, 3);
        Assertions.assertEquals(Difficulty.Champion, saveData.getDifficulty());
        Assertions.assertEquals(3, saveData.getLevel());

        // Check File
        try {
            fileContent = Files.readString(Path.of(TEST_SAVE_FILE_NAME));
            Assertions.assertEquals("Easy;1;N\nChampion;3;N\n", fileContent);
        } catch (IOException e) {
            Assertions.fail();
            return;
        }

        // Create 3
        saveData = saveDataManager.createNewSave(Difficulty.Normal, 5);
        Assertions.assertEquals(Difficulty.Normal, saveData.getDifficulty());
        Assertions.assertEquals(5, saveData.getLevel());

        // Check File
        try {
            fileContent = Files.readString(Path.of(TEST_SAVE_FILE_NAME));
            Assertions.assertEquals("Easy;1;N\nChampion;3;N\nNormal;5;N\n", fileContent);
        } catch (IOException e) {
            Assertions.fail();
            return;
        }

        // Check Saves
        saveData = saveDataManager.getSave(1);
        Assertions.assertEquals(Difficulty.Easy, saveData.getDifficulty());
        Assertions.assertEquals(1, saveData.getLevel());

        saveData = saveDataManager.getSave(2);
        Assertions.assertEquals(Difficulty.Champion, saveData.getDifficulty());
        Assertions.assertEquals(3, saveData.getLevel());

        saveData = saveDataManager.getSave(3);
        Assertions.assertEquals(Difficulty.Normal, saveData.getDifficulty());
        Assertions.assertEquals(5, saveData.getLevel());

        // Mark as Second as Last Open
        saveData = saveDataManager.getSave(2);
        saveDataManager.markAsLastOpen(saveData);
        Assertions.assertEquals(saveData, saveDataManager.getLastOpen());
        Assertions.assertTrue(saveData.isLastOpened());

        // Check File
        try {
            fileContent = Files.readString(Path.of(TEST_SAVE_FILE_NAME));
            Assertions.assertEquals("Easy;1;N\nChampion;3;Y\nNormal;5;N\n", fileContent);
        } catch (IOException e) {
            Assertions.fail();
            return;
        }

        // Mark as First as Last Open
        saveData = saveDataManager.getSave(1);
        saveDataManager.markAsLastOpen(saveData);
        Assertions.assertEquals(saveData, saveDataManager.getLastOpen());
        Assertions.assertTrue(saveData.isLastOpened());

        saveData = saveDataManager.getSave(2);
        Assertions.assertFalse(saveData.isLastOpened());

        // Check File
        try {
            fileContent = Files.readString(Path.of(TEST_SAVE_FILE_NAME));
            Assertions.assertEquals("Easy;1;Y\nChampion;3;N\nNormal;5;N\n", fileContent);
        } catch (IOException e) {
            Assertions.fail();
            return;
        }

        // Reload File
        saveDataManager.setFileName(TEST_SAVE_FILE_NAME);
        Assertions.assertEquals(3, saveDataManager.getSaveCount());
        saveData = saveDataManager.getSave(1);
        Assertions.assertEquals(saveData, saveDataManager.getLastOpen());
        Assertions.assertTrue(saveData.isLastOpened());

        // Delete file
        saveDataManager.deleteSave(saveData);
        Assertions.assertEquals(2, saveDataManager.getSaveCount());
        Assertions.assertNull(saveDataManager.getLastOpen());

        // Check File
        try {
            fileContent = Files.readString(Path.of(TEST_SAVE_FILE_NAME));
            Assertions.assertEquals("Champion;3;N\nNormal;5;N\n", fileContent);
        } catch (IOException e) {
            Assertions.fail();
        }
    }

    @Test
    void Test3() {
        FileSystemSaveDataManager saveDataManager = FileSystemSaveDataManager.getInstance();
        saveDataManager.setFileName(TEST_SAVE_FILE_NAME);

        Assertions.assertThrows(RuntimeException.class, () -> saveDataManager.getSave(0));
        Assertions.assertThrows(RuntimeException.class, () -> saveDataManager.getSave(1));
    }

    @Test
    void Test4() {
        FileSystemSaveDataManager saveDataManager = FileSystemSaveDataManager.getInstance();
        saveDataManager.setFileName(TEST_SAVE_FILE_NAME);
        Assertions.assertThrows(AssertionError.class, () -> saveDataManager.deleteSave(null));
    }

    @Test
    void Test5() {
        FileSystemSaveDataManager saveDataManager = FileSystemSaveDataManager.getInstance();
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> saveDataManager.setFileName("src/test/resources/saves/save2.csv"));
        Assertions.assertEquals("File Not Found.", exception.getMessage());
    }

    @AfterEach
    void Cleanup() {
        FileSystemSaveDataManager.getInstance().setFileName(null);
        try {
            FileOutputStream fos = new FileOutputStream(TEST_SAVE_FILE_NAME, false);
            byte[] contentBytes = "".getBytes();
            fos.write(contentBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

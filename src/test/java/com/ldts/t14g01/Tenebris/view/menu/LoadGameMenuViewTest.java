package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.gui.LanternaGUI;
import com.ldts.t14g01.Tenebris.model.menu.LoadGameMenu;
import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.savedata.SaveDataManager;
import com.ldts.t14g01.Tenebris.utils.Difficulty;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.EnumSet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class LoadGameMenuViewTest {
    @Provide
    Arbitrary<Difficulty> difficultyValues() {
        return Arbitraries.of(EnumSet.allOf(Difficulty.class));
    }

    @Test
    void viewTest1() throws Exception {
        try (MockedStatic<GUI> guiMockedStatic = mockStatic(GUI.class)) {
            // Insure GUI returns the mock
            LanternaGUI mockedGUI = mock(LanternaGUI.class);
            guiMockedStatic.when(GUI::getGUI).thenReturn(mockedGUI);

            // Insure SaveDataManager returns the mock
            try (MockedStatic<SaveDataManager> saveDataManagerMockedStatic = mockStatic(SaveDataManager.class)) {
                SaveDataManager mockedSaveDataManager = Mockito.mock(SaveDataManager.class);
                saveDataManagerMockedStatic.when(SaveDataManager::getInstance).thenReturn(mockedSaveDataManager);

                // Mock Model
                LoadGameMenu mockedModel = Mockito.mock(LoadGameMenu.class);
                LoadGameMenuView view = new LoadGameMenuView(mockedModel);

                Mockito.when(mockedSaveDataManager.getSaveCount()).thenReturn(0);
                view.drawElements();
                Mockito.verify(mockedGUI, Mockito.times(1)).drawLoadGameMenu(0, 0, 0, null);
            }
        }
    }

    @Property
    void viewTest2(
            @ForAll @IntRange(min = 1, max = 1000) int numberOfSaves,
            @ForAll @IntRange(min = 1, max = 1000) int selectedSave,
            @ForAll @IntRange(min = 1, max = SaveData.MAX_LEVEL) int selectedLevel,
            @ForAll("difficultyValues") Difficulty selectedDifficulty
    ) throws IOException {
        try (MockedStatic<GUI> guiMockedStatic = mockStatic(GUI.class)) {
            // Insure GUI returns the mock
            LanternaGUI mockedGUI = mock(LanternaGUI.class);
            guiMockedStatic.when(GUI::getGUI).thenReturn(mockedGUI);

            // Insure SaveDataManager returns the mock
            try (MockedStatic<SaveDataManager> saveDataManagerMockedStatic = mockStatic(SaveDataManager.class)) {
                SaveDataManager mockedSaveDataManager = Mockito.mock(SaveDataManager.class);
                saveDataManagerMockedStatic.when(SaveDataManager::getInstance).thenReturn(mockedSaveDataManager);
                Mockito.when(mockedSaveDataManager.getSaveCount()).thenReturn(numberOfSaves);

                // Mock SaveData
                SaveData mockedSaveData = Mockito.mock(SaveData.class);
                Mockito.when(mockedSaveData.getLevel()).thenReturn(selectedLevel);
                Mockito.when(mockedSaveData.getDifficulty()).thenReturn(selectedDifficulty);
                Mockito.when(mockedSaveDataManager.getSave(selectedSave)).thenReturn(mockedSaveData);

                // Mock Model
                LoadGameMenu mockedModel = Mockito.mock(LoadGameMenu.class);
                Mockito.when(mockedModel.getSelectedOption()).thenReturn(selectedSave - 1);
                LoadGameMenuView view = new LoadGameMenuView(mockedModel);

                view.drawElements();
                Mockito.verify(mockedGUI, Mockito.times(1)).drawLoadGameMenu(numberOfSaves, selectedSave, selectedLevel, selectedDifficulty);
            }
        }
    }
}

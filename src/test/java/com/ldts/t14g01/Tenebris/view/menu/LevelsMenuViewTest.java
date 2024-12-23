package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.gui.LanternaGUI;
import com.ldts.t14g01.Tenebris.model.menu.LevelsMenu;
import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.savedata.SaveDataManager;
import com.ldts.t14g01.Tenebris.utils.Difficulty;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.EnumSet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class LevelsMenuViewTest {
    @Provide
    Arbitrary<Difficulty> difficultyValues() {
        return Arbitraries.of(EnumSet.allOf(Difficulty.class));
    }

    @Property
    void viewTest(@ForAll @IntRange(min = 1, max = SaveData.MAX_LEVEL) int max_level, @ForAll("difficultyValues") Difficulty difficulty) throws Exception {
        try (MockedStatic<GUI> guiMockedStatic = mockStatic(GUI.class)) {
            // Insure GUI returns the mock
            LanternaGUI mockedGUI = mock(LanternaGUI.class);
            guiMockedStatic.when(GUI::getGUI).thenReturn(mockedGUI);

            // Insure SaveDataManager returns the mock
            try (MockedStatic<SaveDataManager> saveDataManagerMockedStatic = mockStatic(SaveDataManager.class)) {
                SaveDataManager mockedSaveDataManager = mock(SaveDataManager.class);
                saveDataManagerMockedStatic.when(SaveDataManager::getInstance).thenReturn(mockedSaveDataManager);

                // Mock SaveData
                SaveData mockedSaveData = Mockito.mock(SaveData.class);
                Mockito.when(mockedSaveData.getLevel()).thenReturn(max_level);
                Mockito.when(mockedSaveData.getDifficulty()).thenReturn(difficulty);
                Mockito.when(mockedSaveDataManager.getLastOpen()).thenReturn(mockedSaveData);

                // Mock Model
                LevelsMenu mockedModel = Mockito.mock(LevelsMenu.class);

                LevelsMenuView view = new LevelsMenuView(mockedModel);
                for (int selected_level = 0; selected_level < max_level; selected_level++) {
                    Mockito.when(mockedModel.getSelectedOption()).thenReturn(selected_level);
                    view.drawElements();
                    Mockito.verify(mockedGUI).drawLevelsMenu(max_level, selected_level + 1, difficulty);
                }
            }
        }
    }
}

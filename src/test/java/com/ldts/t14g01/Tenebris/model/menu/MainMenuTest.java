package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.savedata.SaveDataManager;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class MainMenuTest {
    private MainMenu mainMenu;
    private SaveDataProvider saveDataProvider;

    @Test
    void MenuTest1() {
        // "Fresh installation", no save files, and no saveData currently loaded
        this.saveDataProvider = Mockito.mock(SaveDataProvider.class);
        Mockito.when(this.saveDataProvider.getSaveData()).thenReturn(null);

        try (MockedStatic<SaveDataManager> mockedSaveDataManager = mockStatic(SaveDataManager.class)) {
            // Insure SavaDataManager returns the mock
            SaveDataManager mockSaveDataManager = mock(SaveDataManager.class);
            mockedSaveDataManager.when(SaveDataManager::getInstance).thenReturn(mockSaveDataManager);

            // Make the save count be 2
            Mockito.when(mockSaveDataManager.getSaveCount()).thenReturn(2);

            // Create fresh menu and check Option count
            this.mainMenu = new MainMenu(this.saveDataProvider);
            Assertions.assertEquals(6, mainMenu.getOptions().size());

            // Make the save count be 0
            Mockito.when(mockSaveDataManager.getSaveCount()).thenReturn(0);

            // Create fresh menu and check Option count
            this.mainMenu = new MainMenu(this.saveDataProvider);
            Assertions.assertEquals(4, mainMenu.getOptions().size());
        }
    }

    @Test
    void MenuTest2() {
        this.saveDataProvider = Mockito.mock(SaveDataProvider.class);
        Mockito.when(this.saveDataProvider.getSaveData()).thenReturn(Mockito.mock(SaveData.class));
        this.mainMenu = new MainMenu(this.saveDataProvider);

        try (MockedStatic<SaveDataManager> mockedSaveDataManager = mockStatic(SaveDataManager.class)) {
            // Insure SavaDataManager returns the mock
            SaveDataManager mockSaveDataManager = mock(SaveDataManager.class);
            mockedSaveDataManager.when(SaveDataManager::getInstance).thenReturn(mockSaveDataManager);

            // Make the save count be 2
            Mockito.when(mockSaveDataManager.getSaveCount()).thenReturn(2);

            // Create fresh menu and check Option count
            this.mainMenu = new MainMenu(this.saveDataProvider);
            Assertions.assertEquals(7, mainMenu.getOptions().size());

            // Make the save count be 0
            Mockito.when(mockSaveDataManager.getSaveCount()).thenReturn(0);

            // Create fresh menu and check Option count
            this.mainMenu = new MainMenu(this.saveDataProvider);
            Assertions.assertEquals(5, mainMenu.getOptions().size());
        }
    }

    @Test
    void GetTests() {
        // "Fresh installation", no save files, and no saveData currently loaded
        this.saveDataProvider = Mockito.mock(SaveDataProvider.class);
        Mockito.when(this.saveDataProvider.getSaveData()).thenReturn(null);

        this.mainMenu = new MainMenu(this.saveDataProvider);

        Assertions.assertEquals(this.mainMenu, this.mainMenu.getView().getModel());
        Assertions.assertEquals(this.mainMenu, this.mainMenu.getController().getModel());
    }
}

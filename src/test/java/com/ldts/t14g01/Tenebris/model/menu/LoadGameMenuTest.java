package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.savedata.SaveDataManager;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class LoadGameMenuTest {
    private LoadGameMenu loadGameMenu;
    private SaveDataManager mockSaveDataManager;

    @BeforeEach
    void setUp() {
        try (MockedStatic<SaveDataManager> mockedSaveDataManager = mockStatic(SaveDataManager.class)) {
            // Insure SavaDataManager returns the mock
            this.mockSaveDataManager = mock(SaveDataManager.class);
            mockedSaveDataManager.when(SaveDataManager::getInstance).thenReturn(mockSaveDataManager);
            Mockito.when(mockSaveDataManager.getSaveCount()).thenReturn(0);
            this.loadGameMenu = new LoadGameMenu();
        }
    }

    @Test
    void MenuTest1() {
        Assertions.assertEquals(SaveDataManager.getInstance().getSaveCount(), this.loadGameMenu.getOptions().size());
        this.loadGameMenu.selectedOption = SaveDataManager.getInstance().getSaveCount();
        this.loadGameMenu.updateOptions();
        Assertions.assertEquals(SaveDataManager.getInstance().getSaveCount() - 1, this.loadGameMenu.selectedOption);
    }

    @Property(tries = 100)
    void MenuProperty1(@ForAll @IntRange(min = 1, max = 1000) int a) {
        try (MockedStatic<SoundManager> soundManagerMockedStatic = mockStatic(SoundManager.class)) {
            // Insure GUI returns the mock
            SoundManager mockedSoundManager = mock(SoundManager.class);
            soundManagerMockedStatic.when(SoundManager::getInstance).thenReturn(mockedSoundManager);

            try (MockedStatic<SaveDataManager> mockedSaveDataManager = mockStatic(SaveDataManager.class)) {
                // Insure SavaDataManager returns the mock
                this.mockSaveDataManager = mock(SaveDataManager.class);
                mockedSaveDataManager.when(SaveDataManager::getInstance).thenReturn(mockSaveDataManager);
                Mockito.when(this.mockSaveDataManager.getSaveCount()).thenReturn(a);

                this.loadGameMenu = new LoadGameMenu();
                Assertions.assertEquals(SaveDataManager.getInstance().getSaveCount(), this.loadGameMenu.getOptions().size());
                this.loadGameMenu.updateOptions();
                Assertions.assertEquals(SaveDataManager.getInstance().getSaveCount(), this.loadGameMenu.getOptions().size());

                this.loadGameMenu.moveUp();
                Assertions.assertEquals(SaveDataManager.getInstance().getSaveCount() - 1, this.loadGameMenu.selectedOption);

                this.loadGameMenu.options.removeLast();
                Assertions.assertEquals(SaveDataManager.getInstance().getSaveCount() - 1, this.loadGameMenu.selectedOption);
            }
        }
    }

    @Test
    void GetTests() {
        Assertions.assertEquals(this.loadGameMenu, this.loadGameMenu.getView().getModel());
        Assertions.assertEquals(this.loadGameMenu, this.loadGameMenu.getController().getModel());
    }
}

package com.ldts.t14g01.Tenebris.controller.menu;

import com.ldts.t14g01.Tenebris.Tenebris;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.Arena;
import com.ldts.t14g01.Tenebris.model.arena.ArenaBuilder;
import com.ldts.t14g01.Tenebris.model.menu.LoadGameMenu;
import com.ldts.t14g01.Tenebris.model.menu.MainMenu;
import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.savedata.SaveDataManager;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import com.ldts.t14g01.Tenebris.state.ArenaState;
import com.ldts.t14g01.Tenebris.state.MenuState;
import com.ldts.t14g01.Tenebris.state.State;
import com.ldts.t14g01.Tenebris.state.StateChanger;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;

public class LoadGameMenuControllerTest {
    private LoadGameMenuController controller;
    private LoadGameMenu model;
    private StateChanger stateChanger;
    private SoundManager soundManager;
    private GUI gui;


    @BeforeEach
    public void setUp() {
        this.model = Mockito.mock(LoadGameMenu.class);
        this.controller = new LoadGameMenuController(model);
        this.stateChanger = Mockito.mock(StateChanger.class);
        this.soundManager = Mockito.mock(SoundManager.class);
        this.gui = Mockito.mock(GUI.class);
    }

    @Test
    void tickQuitTest() throws Exception {
        try (MockedStatic<GUI> mockedGUI = Mockito.mockStatic(GUI.class); MockedStatic<SoundManager> mockedSoundManager = Mockito.mockStatic(SoundManager.class)) {
            mockedGUI.when(GUI::getGUI).thenReturn(this.gui);
            mockedSoundManager.when(SoundManager::getInstance).thenReturn(this.soundManager);

            Mockito.when(this.gui.getAction()).thenReturn(Action.QUIT);

            this.controller.tick(this.stateChanger, Tenebris.getInstance());

            Mockito.verify(this.stateChanger, Mockito.times(1)).setState(null);
        }
    }

    @Test
    void tickEscTest() throws Exception {
        try (MockedStatic<GUI> mockedGUI = Mockito.mockStatic(GUI.class); MockedStatic<SoundManager> mockedSoundManager = Mockito.mockStatic(SoundManager.class)) {
            mockedGUI.when(GUI::getGUI).thenReturn(this.gui);
            mockedSoundManager.when(SoundManager::getInstance).thenReturn(this.soundManager);

            Mockito.when(this.gui.getAction()).thenReturn(Action.ESC);

            Mockito.doAnswer(invocationOnMock -> {
                Assertions.assertInstanceOf(MainMenu.class, ((MenuState) invocationOnMock.getArgument(0)).getModel());
                return null;
            }).when(this.stateChanger).setState(Mockito.any(MenuState.class));

            this.controller.tick(stateChanger, Tenebris.getInstance());

            Mockito.verify(soundManager, Mockito.times(1)).playSFX(SoundManager.SFX.MENU_GO_BACK);
            Mockito.verify(stateChanger, Mockito.times(1)).setState(Mockito.any(MenuState.class));
        }
    }

    @Test
    void lookTest() throws Exception {
        try (MockedStatic<GUI> mockedGUI = Mockito.mockStatic(GUI.class); MockedStatic<SoundManager> mockedSoundManager = Mockito.mockStatic(SoundManager.class)) {
            mockedGUI.when(GUI::getGUI).thenReturn(this.gui);
            mockedSoundManager.when(SoundManager::getInstance).thenReturn(this.soundManager);

            Mockito.when(this.gui.getAction()).thenReturn(Action.LOOK_UP);

            this.controller.tick(stateChanger, Tenebris.getInstance());

            Mockito.verify(this.model, Mockito.times(1)).moveDown();

            Mockito.when(this.gui.getAction()).thenReturn(Action.LOOK_DOWN);

            this.controller.tick(stateChanger, Tenebris.getInstance());

            Mockito.verify(this.model, Mockito.times(1)).moveUp();
            Mockito.verify(stateChanger, Mockito.never()).setState(Mockito.any(State.class));
        }
    }

    @Test
    void moveRightTest1() throws Exception {
        try (MockedStatic<GUI> mockedGUI = Mockito.mockStatic(GUI.class);
             MockedStatic<SoundManager> mockedSoundManager = Mockito.mockStatic(SoundManager.class);
             MockedStatic<SaveDataManager> mockedSaveDataManager = Mockito.mockStatic(SaveDataManager.class)) {

            SaveDataManager mockSaveDataManager = Mockito.mock(SaveDataManager.class);

            mockedGUI.when(GUI::getGUI).thenReturn(this.gui);
            mockedSoundManager.when(SoundManager::getInstance).thenReturn(this.soundManager);
            mockedSaveDataManager.when(SaveDataManager::getInstance).thenReturn(mockSaveDataManager);

            Mockito.when(mockSaveDataManager.getSaveCount()).thenReturn(0);

            Mockito.when(this.gui.getAction()).thenReturn(Action.MOVE_RIGHT);

            this.controller.tick(stateChanger, Tenebris.getInstance());

            Mockito.verify(this.soundManager, Mockito.times(1)).playSFX(SoundManager.SFX.MENU_GO_BACK);
            Mockito.verify(mockSaveDataManager, Mockito.never()).deleteSave(Mockito.any(SaveData.class));
        }
    }

    @Test
    void moveRightTest2() throws Exception {
        try (MockedStatic<GUI> mockedGUI = Mockito.mockStatic(GUI.class);
             MockedStatic<SoundManager> mockedSoundManager = Mockito.mockStatic(SoundManager.class);
             MockedStatic<SaveDataManager> mockedSaveDataManager = Mockito.mockStatic(SaveDataManager.class)) {

            SaveDataManager mockSaveDataManager = Mockito.mock(SaveDataManager.class);
            SaveDataProvider mockSaveDataProvider = Mockito.mock(SaveDataProvider.class);
            SaveData mockSaveData = Mockito.mock(SaveData.class);

            mockedGUI.when(GUI::getGUI).thenReturn(this.gui);
            mockedSoundManager.when(SoundManager::getInstance).thenReturn(this.soundManager);
            mockedSaveDataManager.when(SaveDataManager::getInstance).thenReturn(mockSaveDataManager);

            Mockito.when(this.model.getSelectedOption()).thenReturn(-1);
            Mockito.when(mockSaveDataManager.getSaveCount()).thenReturn(3);
            Mockito.when(mockSaveDataManager.getSave(0)).thenReturn(mockSaveData);
            Mockito.when(mockSaveDataProvider.getSaveData()).thenReturn(mockSaveData);

            Mockito.when(this.gui.getAction()).thenReturn(Action.MOVE_RIGHT);

            this.controller.tick(stateChanger, mockSaveDataProvider);

            Mockito.verify(this.soundManager, Mockito.times(1)).playSFX(SoundManager.SFX.MENU_GO_BACK);
            Mockito.verify(mockSaveDataProvider, Mockito.times(1)).setSaveData(null);
            Mockito.verify(mockSaveDataManager, Mockito.times(1)).deleteSave(mockSaveData);
        }
    }

    @Property
    void tickExecTest1(@ForAll @IntRange(min = -1, max = 2) int selectedOption) throws IOException, InterruptedException {
        try (MockedStatic<GUI> mockedGUI = Mockito.mockStatic(GUI.class);
             MockedStatic<SoundManager> mockedSoundManager = Mockito.mockStatic(SoundManager.class);
             MockedStatic<SaveDataManager> mockedSaveDataManager = Mockito.mockStatic(SaveDataManager.class);
             MockedStatic<ArenaBuilder> arenaBuilderMockedStatic = Mockito.mockStatic(ArenaBuilder.class)) {

            LoadGameMenu mockModel = Mockito.mock(LoadGameMenu.class);
            LoadGameMenuController controller1 = new LoadGameMenuController(mockModel);
            SaveDataManager mockSaveDataManager = Mockito.mock(SaveDataManager.class);
            SaveDataProvider mockSaveDataProvider = Mockito.mock(SaveDataProvider.class);
            SaveData mockSaveData = Mockito.mock(SaveData.class);
            GUI mockGUI = Mockito.mock(GUI.class);
            SoundManager mockSound = Mockito.mock(SoundManager.class);
            StateChanger mockStateChanger = Mockito.mock(StateChanger.class);

            mockedGUI.when(GUI::getGUI).thenReturn(mockGUI);
            mockedSoundManager.when(SoundManager::getInstance).thenReturn(mockSound);
            mockedSaveDataManager.when(SaveDataManager::getInstance).thenReturn(mockSaveDataManager);
            arenaBuilderMockedStatic.when(() -> ArenaBuilder.build(Mockito.any(SaveData.class))).thenReturn(new Arena());

            Mockito.when(mockModel.getSelectedOption()).thenReturn(selectedOption);
            Mockito.when(mockSaveDataManager.getSaveCount()).thenReturn(3);
            Mockito.when(mockGUI.getAction()).thenReturn(Action.EXEC);
            Mockito.when(mockSaveDataManager.getSave(selectedOption + 1)).thenReturn(mockSaveData);

            controller1.tick(mockStateChanger, mockSaveDataProvider);

            Mockito.verify(mockSaveDataProvider, Mockito.times(1)).setSaveData(mockSaveData);
            Mockito.verify(mockStateChanger, Mockito.times(1)).setState(Mockito.any(ArenaState.class));
            Mockito.verify(mockStateChanger, Mockito.never()).setState(Mockito.any(MenuState.class));
        }
    }

    @Test
    void tickExecTest2() throws IOException, InterruptedException {
        try (MockedStatic<GUI> mockedGUI = Mockito.mockStatic(GUI.class);
             MockedStatic<SoundManager> mockedSoundManager = Mockito.mockStatic(SoundManager.class);
             MockedStatic<SaveDataManager> mockedSaveDataManager = Mockito.mockStatic(SaveDataManager.class)) {


            SaveDataManager mockSaveDataManager = Mockito.mock(SaveDataManager.class);
            SaveDataProvider mockSaveDataProvider = Mockito.mock(SaveDataProvider.class);
            SaveData mockSaveData = Mockito.mock(SaveData.class);


            mockedGUI.when(GUI::getGUI).thenReturn(this.gui);
            mockedSoundManager.when(SoundManager::getInstance).thenReturn(this.soundManager);
            mockedSaveDataManager.when(SaveDataManager::getInstance).thenReturn(mockSaveDataManager);

            Mockito.when(mockSaveDataManager.getSaveCount()).thenReturn(0);
            Mockito.when(this.gui.getAction()).thenReturn(Action.EXEC);

            Mockito.doAnswer(invocationOnMock -> {
                Assertions.assertInstanceOf(MainMenu.class, ((MenuState) invocationOnMock.getArgument(0)).getModel());
                return null;
            }).when(this.stateChanger).setState(Mockito.any(MenuState.class));

            this.controller.tick(this.stateChanger, mockSaveDataProvider);

            Mockito.verify(this.stateChanger, Mockito.times(1)).setState(Mockito.any(MenuState.class));
            Mockito.verify(this.stateChanger, Mockito.never()).setState(Mockito.any(ArenaState.class));
        }
    }
}

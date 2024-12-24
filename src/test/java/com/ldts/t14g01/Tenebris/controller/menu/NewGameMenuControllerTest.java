package com.ldts.t14g01.Tenebris.controller.menu;

import com.ldts.t14g01.Tenebris.Tenebris;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.Arena;
import com.ldts.t14g01.Tenebris.model.arena.ArenaBuilder;
import com.ldts.t14g01.Tenebris.model.menu.NewGameMenu;
import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.savedata.SaveDataManager;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import com.ldts.t14g01.Tenebris.state.ArenaState;
import com.ldts.t14g01.Tenebris.state.MenuState;
import com.ldts.t14g01.Tenebris.state.State;
import com.ldts.t14g01.Tenebris.state.StateChanger;
import com.ldts.t14g01.Tenebris.utils.Difficulty;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewGameMenuControllerTest {
    private NewGameMenuController controller;
    private NewGameMenu model;
    private StateChanger stateChanger;
    private SoundManager soundManager;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        this.model = Mockito.mock(NewGameMenu.class);
        this.controller = new NewGameMenuController(model);
        this.stateChanger = Mockito.mock(StateChanger.class);
        this.soundManager = Mockito.mock(SoundManager.class);
        this.gui = Mockito.mock(GUI.class);
    }

    @Test
    void quitTest() throws IOException {
        this.controller.quit(this.stateChanger);
        Mockito.verify(this.stateChanger, Mockito.times(1)).setState(null);
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

            Mockito.verify(this.model, Mockito.times(1)).moveUp();

            Mockito.when(this.gui.getAction()).thenReturn(Action.LOOK_DOWN);

            this.controller.tick(stateChanger, Tenebris.getInstance());

            Mockito.verify(this.model, Mockito.times(1)).moveDown();
            Mockito.verify(stateChanger, Mockito.never()).setState(Mockito.any(State.class));
        }
    }

    @Property
    void tickExecTest(@ForAll @IntRange(min = 0, max = 3) int selectedOption) throws Exception {
        try (MockedStatic<GUI> mockedGUI = Mockito.mockStatic(GUI.class);
             MockedStatic<SoundManager> mockedSoundManager = Mockito.mockStatic(SoundManager.class);
             MockedStatic<ArenaBuilder> arenaBuilderMockedStatic = Mockito.mockStatic(ArenaBuilder.class);
             MockedStatic<SaveDataManager> managerMockedStatic = Mockito.mockStatic(SaveDataManager.class)) {

            NewGameMenu mockedModel = Mockito.mock(NewGameMenu.class);
            NewGameMenuController controller1 = new NewGameMenuController(mockedModel);
            GUI mockedGui = Mockito.mock(GUI.class);
            SoundManager mockedSound = Mockito.mock(SoundManager.class);
            StateChanger mockedStateChanger = Mockito.mock(StateChanger.class);
            SaveDataProvider mockedTenebris = Mockito.mock(SaveDataProvider.class);
            SaveDataManager mockedSaveDataManager = Mockito.mock(SaveDataManager.class);
            SaveData mockedSaveData = Mockito.mock(SaveData.class);

            mockedGUI.when(GUI::getGUI).thenReturn(mockedGui);
            mockedSoundManager.when(SoundManager::getInstance).thenReturn(mockedSound);
            managerMockedStatic.when(SaveDataManager::getInstance).thenReturn(mockedSaveDataManager);
            arenaBuilderMockedStatic.when(() -> ArenaBuilder.build(Mockito.any(SaveData.class))).thenReturn(new Arena());

            List<String> options = new ArrayList<>();
            for (Difficulty difficulty : Difficulty.values()) {
                options.add(difficulty.toString());
            }

            Mockito.when(mockedModel.getOptions()).thenReturn(options);
            Mockito.when(mockedModel.getSelectedOption()).thenReturn(selectedOption);
            Mockito.when(mockedGui.getAction()).thenReturn(Action.EXEC);
            Mockito.when(mockedSaveDataManager.createNewSave(Mockito.any(Difficulty.class))).thenReturn(mockedSaveData);
            controller1.tick(mockedStateChanger, mockedTenebris);

            Mockito.verify(mockedSound, Mockito.times(1)).playSFX(SoundManager.SFX.MENU_SELECT);
            Mockito.verify(mockedSaveDataManager).createNewSave(Difficulty.valueOf(options.get(selectedOption)));
            Mockito.verify(mockedTenebris, Mockito.times(1)).setSaveData(mockedSaveData);
            Mockito.verify(mockedStateChanger, Mockito.times(1)).setState(Mockito.any(ArenaState.class));

        }
    }
}


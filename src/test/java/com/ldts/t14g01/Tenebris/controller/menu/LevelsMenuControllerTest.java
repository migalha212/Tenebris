package com.ldts.t14g01.Tenebris.controller.menu;

import com.ldts.t14g01.Tenebris.Tenebris;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.LevelsMenu;
import com.ldts.t14g01.Tenebris.model.menu.MainMenu;
import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.savedata.SaveDataManager;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import com.ldts.t14g01.Tenebris.state.MenuState;
import com.ldts.t14g01.Tenebris.state.State;
import com.ldts.t14g01.Tenebris.state.StateChanger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Collections;

public class LevelsMenuControllerTest {
    private LevelsMenuController controller;
    private LevelsMenu model;
    private StateChanger stateChanger;
    private SoundManager soundManager;
    private GUI gui;


    @BeforeEach
    public void setUp() {
        this.model = Mockito.mock(LevelsMenu.class);
        this.controller = new LevelsMenuController(model);
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

            Mockito.when(this.gui.getAction()).thenReturn(Action.LOOK_LEFT);

            this.controller.tick(stateChanger, Tenebris.getInstance());

            Mockito.verify(this.model, Mockito.times(1)).moveUp();

            Mockito.when(this.gui.getAction()).thenReturn(Action.LOOK_RIGHT);

            this.controller.tick(stateChanger, Tenebris.getInstance());

            Mockito.verify(this.model, Mockito.times(1)).moveDown();
            Mockito.verify(stateChanger, Mockito.never()).setState(Mockito.any(State.class));
        }
    }

    @Test
    void lookTestVertical1() throws Exception {
        try (MockedStatic<GUI> mockedGUI = Mockito.mockStatic(GUI.class); MockedStatic<SoundManager> mockedSoundManager = Mockito.mockStatic(SoundManager.class)) {
            mockedGUI.when(GUI::getGUI).thenReturn(this.gui);
            mockedSoundManager.when(SoundManager::getInstance).thenReturn(this.soundManager);

            Mockito.when(this.gui.getAction()).thenReturn(Action.LOOK_UP);

            this.controller.tick(stateChanger, Tenebris.getInstance());

            Mockito.when(this.model.getOptions()).thenReturn(Collections.emptyList());
            Mockito.verify(this.soundManager, Mockito.never()).playSFX(SoundManager.SFX.MENU_SWITCH);
        }
    }
}

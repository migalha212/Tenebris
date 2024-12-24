package com.ldts.t14g01.Tenebris.controller.menu;

import com.ldts.t14g01.Tenebris.Tenebris;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.CreditsMenu;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import com.ldts.t14g01.Tenebris.state.MenuState;
import com.ldts.t14g01.Tenebris.state.StateChanger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;


public class CreditsMenuControllerTest {
    private CreditsMenuController controller;
    private CreditsMenu model;
    private StateChanger stateChanger;
    private SoundManager soundManager;
    private GUI gui;

    @BeforeEach
    void setUp() {
        this.model = new CreditsMenu();
        this.controller = new CreditsMenuController(model);
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
    void tickEscTest() throws Exception {
        try (MockedStatic<GUI> mockedGUI = Mockito.mockStatic(GUI.class); MockedStatic<SoundManager> mockedSoundManager = Mockito.mockStatic(SoundManager.class)) {
            mockedGUI.when(GUI::getGUI).thenReturn(this.gui);
            mockedSoundManager.when(SoundManager::getInstance).thenReturn(this.soundManager);

            Mockito.when(this.gui.getAction()).thenReturn(Action.ESC);

            this.controller.tick(this.stateChanger, Tenebris.getInstance());

            Mockito.verify(this.soundManager, Mockito.times(1)).playSFX(SoundManager.SFX.MENU_GO_BACK);
            Mockito.verify(this.stateChanger, Mockito.times(1)).setState(Mockito.any(MenuState.class));
        }
    }

    @Test
    void tickEXECTest() throws Exception {
        try (MockedStatic<GUI> mockedGUI = Mockito.mockStatic(GUI.class); MockedStatic<SoundManager> mockedSoundManager = Mockito.mockStatic(SoundManager.class)) {
            mockedGUI.when(GUI::getGUI).thenReturn(this.gui);
            mockedSoundManager.when(SoundManager::getInstance).thenReturn(this.soundManager);

            Mockito.when(this.gui.getAction()).thenReturn(Action.EXEC);

            this.controller.tick(this.stateChanger, Tenebris.getInstance());

            Mockito.verify(this.soundManager, Mockito.times(1)).playSFX(SoundManager.SFX.MENU_SELECT);
            Mockito.verify(this.stateChanger, Mockito.times(1)).setState(Mockito.any(MenuState.class));
        }
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
}

package com.ldts.t14g01.Tenebris.controller.menu;

import com.ldts.t14g01.Tenebris.Tenebris;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.HowToPlayMenu;
import com.ldts.t14g01.Tenebris.model.menu.MainMenu;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
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
import java.util.ArrayList;
import java.util.List;

public class HowToPlayMenuControllerTest {
    HowToPlayMenuController controller;
    HowToPlayMenu model;
    private StateChanger stateChanger;
    private SoundManager soundManager;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        this.model = Mockito.mock(HowToPlayMenu.class);
        this.controller = new HowToPlayMenuController(model);
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

            this.controller.tick(this.stateChanger, Tenebris.getInstance());

            Mockito.verify(this.soundManager, Mockito.times(1)).playSFX(SoundManager.SFX.MENU_GO_BACK);
            Mockito.verify(this.stateChanger, Mockito.times(1)).setState(Mockito.any(MenuState.class));
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
    void tickExecTest(@ForAll @IntRange(min = 0, max = 8) int selectedOption) throws Exception {
        try (MockedStatic<GUI> mockedGUI = Mockito.mockStatic(GUI.class); MockedStatic<SoundManager> mockedSoundManager = Mockito.mockStatic(SoundManager.class)) {

            HowToPlayMenu model = Mockito.mock(HowToPlayMenu.class);
            HowToPlayMenuController controller1 = new HowToPlayMenuController(model);
            GUI mockedGui = Mockito.mock(GUI.class);
            SoundManager mockedSound = Mockito.mock(SoundManager.class);
            StateChanger mockedStateChanger = Mockito.mock(StateChanger.class);

            mockedGUI.when(GUI::getGUI).thenReturn(mockedGui);
            mockedSoundManager.when(SoundManager::getInstance).thenReturn(mockedSound);

            List<String> options = new ArrayList<>();
            for (HowToPlayMenu.HowToPlayMenuOptions option : HowToPlayMenu.HowToPlayMenuOptions.values())
                options.add(option.name());


            Mockito.when(model.getOptions()).thenReturn(options);
            Mockito.when(model.getSelectedOption()).thenReturn(selectedOption);
            Mockito.when(mockedGui.getAction()).thenReturn(Action.EXEC);

            Mockito.doAnswer(invocationOnMock -> {
                Assertions.assertInstanceOf(MainMenu.class, ((MenuState) invocationOnMock.getArgument(0)).getModel());
                return null;
            }).when(mockedStateChanger).setState(Mockito.any(MenuState.class));

            controller1.tick(mockedStateChanger, Tenebris.getInstance());

            if (HowToPlayMenu.HowToPlayMenuOptions.Back.name().equals(options.get(selectedOption))) {
                Mockito.verify(mockedSound, Mockito.times(1)).playSFX(SoundManager.SFX.MENU_SELECT);
                Mockito.verify(mockedStateChanger, Mockito.times(1)).setState(Mockito.any(MenuState.class));
            }
        }
    }
}

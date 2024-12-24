package com.ldts.t14g01.Tenebris.controller.menu;

import com.ldts.t14g01.Tenebris.Tenebris;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.Arena;
import com.ldts.t14g01.Tenebris.model.arena.ArenaBuilder;
import com.ldts.t14g01.Tenebris.model.menu.MainMenu;
import com.ldts.t14g01.Tenebris.model.menu.PauseMenu;
import com.ldts.t14g01.Tenebris.savedata.SaveData;
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
import java.util.ArrayList;
import java.util.List;

public class PauseMenuControllerTest {
    private PauseMenuController controller;
    private PauseMenu model;
    private StateChanger stateChanger;
    private SoundManager soundManager;
    private GUI gui;

    @BeforeEach
    void setUp() {
        this.model = Mockito.mock(PauseMenu.class);
        this.controller = new PauseMenuController(model);
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
            Mockito.verify(stateChanger, Mockito.times(1)).setState(Mockito.any(ArenaState.class));
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
    void tickExecTest(@ForAll @IntRange(min = 0, max = 2) int selectedOption) throws Exception {
        try (MockedStatic<GUI> mockedGUI = Mockito.mockStatic(GUI.class);
             MockedStatic<SoundManager> mockedSoundManager = Mockito.mockStatic(SoundManager.class);
             MockedStatic<ArenaBuilder> arenaBuilderMockedStatic = Mockito.mockStatic(ArenaBuilder.class)) {

            PauseMenu mockedModel = Mockito.mock(PauseMenu.class);
            PauseMenuController controller1 = new PauseMenuController(mockedModel);
            GUI mockedGui = Mockito.mock(GUI.class);
            SoundManager mockedSound = Mockito.mock(SoundManager.class);
            StateChanger mockedStateChanger = Mockito.mock(StateChanger.class);

            mockedGUI.when(GUI::getGUI).thenReturn(mockedGui);
            mockedSoundManager.when(SoundManager::getInstance).thenReturn(mockedSound);
            arenaBuilderMockedStatic.when(() -> ArenaBuilder.build(Mockito.any(SaveData.class))).thenReturn(new Arena());
            List<String> options = new ArrayList<>();
            for (PauseMenu.PauseMenuOptions option : PauseMenu.PauseMenuOptions.values())
                options.add(option.toString());

            Mockito.when(mockedModel.getOptions()).thenReturn(options);
            Mockito.when(mockedModel.getSelectedOption()).thenReturn(selectedOption);
            Mockito.when(mockedGui.getAction()).thenReturn(Action.EXEC);

            Mockito.doAnswer(invocationOnMock -> {
                Assertions.assertInstanceOf(MainMenu.class, ((MenuState) invocationOnMock.getArgument(0)).getModel());
                return null;
            }).when(mockedStateChanger).setState(Mockito.any(MenuState.class));

            controller1.tick(mockedStateChanger, Tenebris.getInstance());

            Mockito.verify(mockedSound, Mockito.times(1)).playSFX(SoundManager.SFX.MENU_SELECT);
            switch (PauseMenu.PauseMenuOptions.valueOf(options.get(selectedOption))) {
                case Continue -> {
                    // back to arena
                    Mockito.verify(mockedSound, Mockito.times(1)).playSFX(SoundManager.SFX.MENU_GO_BACK);
                    Mockito.verify(mockedStateChanger, Mockito.times(1)).setState(Mockito.any(ArenaState.class));
                }
                case Restart_Level ->
                        Mockito.verify(mockedStateChanger, Mockito.times(1)).setState(Mockito.any(ArenaState.class));

                case Return_to_Main_Menu -> {
                    Mockito.verify(mockedStateChanger, Mockito.times(1)).setState(Mockito.any(MenuState.class));
                }
            }
        }
    }
}

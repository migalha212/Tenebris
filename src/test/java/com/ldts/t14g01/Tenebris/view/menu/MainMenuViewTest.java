package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.gui.LanternaGUI;
import com.ldts.t14g01.Tenebris.model.menu.MainMenu;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static com.ldts.t14g01.Tenebris.gui.GUI.Menu_Options.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class MainMenuViewTest {

    @Property
    void viewTest1(@ForAll @IntRange(min = 0, max = 3) int selectedOption) throws Exception {
        try (MockedStatic<GUI> guiMockedStatic = mockStatic(GUI.class)) {
            // Insure GUI returns the mock
            LanternaGUI mockedGUI = mock(LanternaGUI.class);
            guiMockedStatic.when(GUI::getGUI).thenReturn(mockedGUI);

            // Mock Model
            MainMenu mockedModel = Mockito.mock(MainMenu.class);
            Mockito.when(mockedModel.getOptions()).thenReturn(List.of("New_Game", "How_to_Play", "Credits", "Exit"));
            Mockito.when(mockedModel.getSelectedOption()).thenReturn(selectedOption);
            MainMenuView view = new MainMenuView(mockedModel);

            List<GUI.Menu_Options> options = new ArrayList<>();
            options.add(MAIN_MENU_NEW_GAME);
            options.add(MAIN_MENU_HOW_TO_PLAY);
            options.add(MAIN_MENU_CREDITS);
            options.add(MAIN_MENU_EXIT);

            view.drawElements();
            Mockito.verify(mockedGUI, Mockito.times(1)).drawMainMenu(options, selectedOption);
        }
    }

    @Property
    void viewTest2(@ForAll @IntRange(min = 0, max = 4) int selectedOption) throws Exception {
        try (MockedStatic<GUI> guiMockedStatic = mockStatic(GUI.class)) {
            // Insure GUI returns the mock
            LanternaGUI mockedGUI = mock(LanternaGUI.class);
            guiMockedStatic.when(GUI::getGUI).thenReturn(mockedGUI);


            // Mock Model
            MainMenu mockedModel = Mockito.mock(MainMenu.class);
            Mockito.when(mockedModel.getOptions()).thenReturn(List.of("New_Game", "Continue", "How_to_Play", "Credits", "Exit"));
            Mockito.when(mockedModel.getSelectedOption()).thenReturn(selectedOption);
            MainMenuView view = new MainMenuView(mockedModel);

            List<GUI.Menu_Options> options = new ArrayList<>();
            options.add(MAIN_MENU_NEW_GAME);
            options.add(MAIN_MENU_CONTINUE);
            options.add(MAIN_MENU_HOW_TO_PLAY);
            options.add(MAIN_MENU_CREDITS);
            options.add(MAIN_MENU_EXIT);

            view.drawElements();
            Mockito.verify(mockedGUI, Mockito.times(1)).drawMainMenu(options, selectedOption);
        }
    }

    @Property
    void viewTest3(@ForAll @IntRange(min = 0, max = 5) int selectedOption) throws Exception {
        try (MockedStatic<GUI> guiMockedStatic = mockStatic(GUI.class)) {
            // Insure GUI returns the mock
            LanternaGUI mockedGUI = mock(LanternaGUI.class);
            guiMockedStatic.when(GUI::getGUI).thenReturn(mockedGUI);


            // Mock Model
            MainMenu mockedModel = Mockito.mock(MainMenu.class);
            Mockito.when(mockedModel.getOptions()).thenReturn(List.of("New_Game", "Load_Game", "Levels", "How_to_Play", "Credits", "Exit"));
            Mockito.when(mockedModel.getSelectedOption()).thenReturn(selectedOption);
            MainMenuView view = new MainMenuView(mockedModel);

            List<GUI.Menu_Options> options = new ArrayList<>();
            options.add(MAIN_MENU_NEW_GAME);
            options.add(MAIN_MENU_LOAD_GAME);
            options.add(MAIN_MENU_LEVELS);
            options.add(MAIN_MENU_HOW_TO_PLAY);
            options.add(MAIN_MENU_CREDITS);
            options.add(MAIN_MENU_EXIT);

            view.drawElements();
            Mockito.verify(mockedGUI, Mockito.times(1)).drawMainMenu(options, selectedOption);
        }
    }

    @Property
    void viewTest4(@ForAll @IntRange(min = 0, max = 6) int selectedOption) throws Exception {
        try (MockedStatic<GUI> guiMockedStatic = mockStatic(GUI.class)) {
            // Insure GUI returns the mock
            LanternaGUI mockedGUI = mock(LanternaGUI.class);
            guiMockedStatic.when(GUI::getGUI).thenReturn(mockedGUI);


            // Mock Model
            MainMenu mockedModel = Mockito.mock(MainMenu.class);
            Mockito.when(mockedModel.getOptions()).thenReturn(List.of("New_Game", "Continue", "Load_Game", "Levels", "How_to_Play", "Credits", "Exit"));
            Mockito.when(mockedModel.getSelectedOption()).thenReturn(selectedOption);
            MainMenuView view = new MainMenuView(mockedModel);

            List<GUI.Menu_Options> options = new ArrayList<>();
            options.add(MAIN_MENU_NEW_GAME);
            options.add(MAIN_MENU_CONTINUE);
            options.add(MAIN_MENU_LOAD_GAME);
            options.add(MAIN_MENU_LEVELS);
            options.add(MAIN_MENU_HOW_TO_PLAY);
            options.add(MAIN_MENU_CREDITS);
            options.add(MAIN_MENU_EXIT);

            view.drawElements();
            Mockito.verify(mockedGUI, Mockito.times(1)).drawMainMenu(options, selectedOption);
        }
    }
}

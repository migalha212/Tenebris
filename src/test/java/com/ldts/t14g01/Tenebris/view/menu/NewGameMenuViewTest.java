package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.gui.LanternaGUI;
import com.ldts.t14g01.Tenebris.model.menu.NewGameMenu;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class NewGameMenuViewTest {
    @Test
    void viewTest() throws IOException {
        try (MockedStatic<GUI> guiMockedStatic = mockStatic(GUI.class)) {
            // Mock Model
            NewGameMenu mockedModel = mock(NewGameMenu.class);
            Mockito.when(mockedModel.getOptions()).thenReturn(new ArrayList<>(List.of("Easy", "Normal", "Champion", "Heartless")));

            // Insure GUI returns the mock
            LanternaGUI mockedGUI = mock(LanternaGUI.class);
            guiMockedStatic.when(GUI::getGUI).thenReturn(mockedGUI);

            List<GUI.Menu_Options> options = new ArrayList<>();
            options.add(GUI.Menu_Options.EASY_DIFFICULTY);
            options.add(GUI.Menu_Options.NORMAL_DIFFICULTY);
            options.add(GUI.Menu_Options.CHAMPION_DIFFICULTY);
            options.add(GUI.Menu_Options.HEARTLESS_DIFFICULTY);

            NewGameMenuView view = new NewGameMenuView(mockedModel);
            Mockito.when(mockedModel.getSelectedOption()).thenReturn(0);
            view.drawElements();
            Mockito.verify(mockedGUI, Mockito.times(1)).drawNewGameMenu(options, 0);

            Mockito.when(mockedModel.getSelectedOption()).thenReturn(1);
            view.drawElements();
            Mockito.verify(mockedGUI, Mockito.times(1)).drawNewGameMenu(options, 1);

            Mockito.when(mockedModel.getSelectedOption()).thenReturn(2);
            view.drawElements();
            Mockito.verify(mockedGUI, Mockito.times(1)).drawNewGameMenu(options, 2);

            Mockito.when(mockedModel.getSelectedOption()).thenReturn(3);
            view.drawElements();
            Mockito.verify(mockedGUI, Mockito.times(1)).drawNewGameMenu(options, 3);
        }
    }
}

package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.gui.LanternaGUI;
import com.ldts.t14g01.Tenebris.model.menu.PauseMenu;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class PauseMenuViewTest {
    @Test
    void viewTest() throws IOException {
        try (MockedStatic<GUI> guiMockedStatic = mockStatic(GUI.class)) {
            // Mock Model
            PauseMenu mockedModel = mock(PauseMenu.class);
            Mockito.when(mockedModel.getOptions()).thenReturn(new ArrayList<>(List.of("Continue", "Restart_Level", "Return_to_Main_Menu")));

            // Insure GUI returns the mock
            LanternaGUI mockedGUI = mock(LanternaGUI.class);
            guiMockedStatic.when(GUI::getGUI).thenReturn(mockedGUI);

            List<GUI.Menu_Options> options = new ArrayList<>();
            options.add(GUI.Menu_Options.PAUSE_CONTINUE);
            options.add(GUI.Menu_Options.PAUSE_RESTART);
            options.add(GUI.Menu_Options.PAUSE_RETURN);

            PauseMenuView view = new PauseMenuView(mockedModel);
            Mockito.when(mockedModel.getSelectedOption()).thenReturn(0);
            view.drawElements();
            Mockito.verify(mockedGUI, Mockito.times(1)).drawPauseMenuMenu(options, 0);

            Mockito.when(mockedModel.getSelectedOption()).thenReturn(1);
            view.drawElements();
            Mockito.verify(mockedGUI, Mockito.times(1)).drawPauseMenuMenu(options, 1);

            Mockito.when(mockedModel.getSelectedOption()).thenReturn(2);
            view.drawElements();
            Mockito.verify(mockedGUI, Mockito.times(1)).drawPauseMenuMenu(options, 2);
        }
    }
}

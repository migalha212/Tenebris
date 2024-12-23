package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.gui.LanternaGUI;
import com.ldts.t14g01.Tenebris.model.menu.DeathMenu;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class DeathMenuViewTest {
    @Test
    void viewTest() throws IOException {
        try (MockedStatic<GUI> guiMockedStatic = mockStatic(GUI.class)) {
            // Mock Model
            DeathMenu mockedModel = mock(DeathMenu.class);
            Mockito.when(mockedModel.getOptions()).thenReturn(new ArrayList<>(List.of("Retry", "Return_To_Main_Menu")));

            // Insure GUI returns the mock
            LanternaGUI mockedGUI = mock(LanternaGUI.class);
            guiMockedStatic.when(GUI::getGUI).thenReturn(mockedGUI);

            List<GUI.Menu_Options> options = new ArrayList<>();
            options.add(GUI.Menu_Options.DEATH_MENU_RETRY);
            options.add(GUI.Menu_Options.DEATH_MENU_RETURN);

            DeathMenuView view = new DeathMenuView(mockedModel);
            Mockito.when(mockedModel.getSelectedOption()).thenReturn(0);
            view.drawElements();
            Mockito.verify(mockedGUI, Mockito.times(1)).drawDeathMenu(options, 0);

            Mockito.when(mockedModel.getSelectedOption()).thenReturn(1);
            view.drawElements();
            Mockito.verify(mockedGUI, Mockito.times(1)).drawDeathMenu(options, 1);
        }
    }
}

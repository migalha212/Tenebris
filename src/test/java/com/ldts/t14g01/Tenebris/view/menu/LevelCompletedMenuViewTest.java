package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.gui.LanternaGUI;
import com.ldts.t14g01.Tenebris.model.menu.LevelCompletedMenu;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class LevelCompletedMenuViewTest {
    @Test
    void viewTest() throws IOException {
        try (MockedStatic<GUI> guiMockedStatic = mockStatic(GUI.class)) {
            // Mock Model
            LevelCompletedMenu mockedModel = mock(LevelCompletedMenu.class);
            Mockito.when(mockedModel.getOptions()).thenReturn(new ArrayList<>(List.of("Next_Level", "Return_To_Main_Menu")));

            // Insure GUI returns the mock
            LanternaGUI mockedGUI = mock(LanternaGUI.class);
            guiMockedStatic.when(GUI::getGUI).thenReturn(mockedGUI);


            List<GUI.Menu_Options> options = new ArrayList<>();
            options.add(GUI.Menu_Options.LEVEL_COMPLETED_MENU_NEXT_LEVEL);
            options.add(GUI.Menu_Options.LEVEL_COMPLETED_MENU_RETURN);

            LevelCompletedMenuView view = new LevelCompletedMenuView(mockedModel);
            Mockito.when(mockedModel.getSelectedOption()).thenReturn(0);
            view.drawElements();
            Mockito.verify(mockedGUI, Mockito.times(1)).drawLevelCompletedMenu(options, 0);

            Mockito.when(mockedModel.getSelectedOption()).thenReturn(1);
            view.drawElements();
            Mockito.verify(mockedGUI, Mockito.times(1)).drawLevelCompletedMenu(options, 1);
        }
    }
}

package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.gui.LanternaGUI;
import com.ldts.t14g01.Tenebris.model.menu.HowToPlayMenu;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class HowToPlayMenuViewTest {
    @Test
    void testView() {
        try (MockedStatic<GUI> guiMockedStatic = mockStatic(GUI.class)) {
            // Mock Model
            HowToPlayMenu mockedModel = mock(HowToPlayMenu.class);

            // Insure GUI returns the mock
            LanternaGUI mockedGUI = mock(LanternaGUI.class);
            guiMockedStatic.when(GUI::getGUI).thenReturn(mockedGUI);

            HowToPlayMenuView view = new HowToPlayMenuView(mockedModel);
            for (int i = 0; i < HowToPlayMenu.HowToPlayMenuOptions.values().length; i++) {
                Mockito.when(mockedModel.getSelectedOption()).thenReturn(i);
                view.drawElements();
                Mockito.verify(mockedGUI, Mockito.times(1)).drawHowToPlayMenu(i);
            }
        }
    }
}

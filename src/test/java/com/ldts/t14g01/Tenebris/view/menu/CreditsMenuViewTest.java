package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.gui.LanternaGUI;
import com.ldts.t14g01.Tenebris.model.menu.CreditsMenu;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class CreditsMenuViewTest {
    @Test
    void viewTest() {
        try (MockedStatic<GUI> guiMockedStatic = mockStatic(GUI.class)) {
            CreditsMenuView view = new CreditsMenuView(new CreditsMenu());

            // Insure GUI returns the mock
            LanternaGUI mockedGUI = mock(LanternaGUI.class);
            guiMockedStatic.when(GUI::getGUI).thenReturn(mockedGUI);

            view.drawElements();

            Mockito.verify(mockedGUI, Mockito.times(1)).drawCreditsMenu();
        }
    }

}

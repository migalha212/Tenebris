package com.ldts.t14g01.Tenebris.gui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class GUITest {
    @Test
    void Test1() {
        System.out.println("GUITEST");
        try (MockedStatic<LanternaGUI> lanternaGUIMockedStatic = Mockito.mockStatic(LanternaGUI.class)) {
            LanternaGUI mockedLanternaGUI = Mockito.mock(LanternaGUI.class);
            lanternaGUIMockedStatic.when(LanternaGUI::getGUI).thenReturn(mockedLanternaGUI);

            Assertions.assertSame(mockedLanternaGUI, GUI.getGUI());
        }
    }
}

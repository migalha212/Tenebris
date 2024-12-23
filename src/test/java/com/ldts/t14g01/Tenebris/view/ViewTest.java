package com.ldts.t14g01.Tenebris.view;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.gui.LanternaGUI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class ViewTest {
    @Test
    void Test1() throws IOException {
        try (MockedStatic<GUI> guiMockedStatic = mockStatic(GUI.class)) {
            // Insure GUI returns the mock
            LanternaGUI mockedGUI = mock(LanternaGUI.class);
            guiMockedStatic.when(GUI::getGUI).thenReturn(mockedGUI);

            Object object = new Object();

            View view = new View(object) {
                @Override
                protected void drawElements() throws IOException {
                    // Not to actually close, just to be detected by Mockito
                    GUI.getGUI().close();
                }
            };

            Assertions.assertSame(object, view.getModel());

            view.draw();
            Mockito.verify(mockedGUI, Mockito.times(1)).close();
            Mockito.verify(mockedGUI, Mockito.times(1)).refresh();
        }
    }
}

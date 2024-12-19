package com.ldts.t14g01.Tenebris.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class GUITest {
    private Screen screen;
    private TextGraphics textGraphics;
    private LanternaGUI gui;

    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        textGraphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(textGraphics);

        gui = (LanternaGUI) LanternaGUI.getGUI();
        try {
            gui.setType(GUI.Type.MENU);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gui.setScreen(screen);
    }

    @Test
    void drawTextTest() {
        gui.drawText("This is a Test!", new Vector2D(1, 1), GUI.Colors.WHITE, GUI.Colors.BLACK);

        Mockito.verify(textGraphics).setForegroundColor(TextColor.ANSI.WHITE);
        Mockito.verify(textGraphics).setBackgroundColor(TextColor.ANSI.BLACK);
        Mockito.verify(textGraphics).putString(1, 1, "This is a Test!");
    }
}

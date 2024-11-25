package com.ldts.t14g01.Tenebris.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

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

    @Test
    void drawRectangleTest() {
        Vector2D topLeft = new Vector2D(1, 1);
        Vector2D size = new Vector2D(2, 2);
        gui.drawRectangle(topLeft, size, GUI.Colors.BRIGHT_GREEN);

        Mockito.verify(textGraphics).setBackgroundColor(TextColor.ANSI.GREEN_BRIGHT);
        Mockito.verify(textGraphics).drawRectangle(
                new TerminalPosition(topLeft.x(), topLeft.y()),
                new TerminalSize(size.x(), size.y()),
                ' ');
    }

    @Test
    void clearTest() {
        gui.clear();
        Mockito.verify(textGraphics).setBackgroundColor(TextColor.ANSI.BLACK);
        Mockito.verify(textGraphics).fillRectangle(
                new TerminalPosition(0, 0),
                gui.getScreen().getTerminalSize(),
                ' '
        );
    }

}

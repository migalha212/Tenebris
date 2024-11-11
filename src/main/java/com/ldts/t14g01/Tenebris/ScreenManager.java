package com.ldts.t14g01.Tenebris;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;

import java.awt.*;
import java.io.IOException;

public class ScreenManager {
    private static final double SCREEN_OCCUPANCY = 0.7;
    private static final int BASE_WIDTH = 16;
    private static final int BASE_HEIGHT = 5;

    public static final int MAIN_MENU = 5;
    public static final int ARENA = 12;

    public static Screen newScreen(int multiplier, Screen oldScreen) throws IOException {
        // Properly delete old screen if needed
        if (oldScreen != null) oldScreen.stopScreen();

        // Calculate Terminal Cell Count
        final int numberCols = BASE_WIDTH * multiplier;
        final int numberRows = BASE_HEIGHT * multiplier;

        // Get screen dimensions
        int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        // Calculate terminal window dimensions (80% of screen)
        int windowHeight = (int) (screenHeight * SCREEN_OCCUPANCY);

        // Calculate font size and height
        int fontSize = windowHeight / numberRows;

        // Set up Lanterna terminal with calculated font size
        Screen screen = new DefaultTerminalFactory()
                .setInitialTerminalSize(new TerminalSize(numberCols, numberRows))
                .setTerminalEmulatorFontConfiguration(
                        SwingTerminalFontConfiguration.newInstance(
                                new Font("Monospaced", Font.BOLD, fontSize)
                        )
                ).createScreen();

        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }
}

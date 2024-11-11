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

    private static Screen currentScreen = null;
    private static int currentScreenSize = 0;

    public static Screen newScreen(int screenSize) throws IOException {
        // Return current screen if no changes are needed
        if (ScreenManager.currentScreenSize == screenSize && ScreenManager.currentScreen != null) return ScreenManager.currentScreen;

        // Properly delete old screen if needed
        if (ScreenManager.currentScreen != null) ScreenManager.currentScreen.stopScreen();

        // Calculate Terminal Cell Count
        final int numberCols = BASE_WIDTH * screenSize;
        final int numberRows = BASE_HEIGHT * screenSize;

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

        // Screen initial config
        screen.startScreen();
        screen.doResizeIfNecessary();
        screen.setCursorPosition(null);

        // Update state info
        ScreenManager.currentScreen = screen;
        ScreenManager.currentScreenSize = screenSize;
        return screen;
    }
}

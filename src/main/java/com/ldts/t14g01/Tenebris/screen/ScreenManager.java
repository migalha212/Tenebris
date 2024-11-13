package com.ldts.t14g01.Tenebris.screen;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalResizeListener;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;

import java.awt.*;
import java.io.IOException;

public class ScreenManager implements TerminalResizeListener {
    // Basic Screen Config
    private static final double SCREEN_OCCUPANCY = 0.7;
    private static final int BASE_WIDTH = 16;
    private static final int BASE_HEIGHT = 5;

    // Default Sizes
    public static final int MAIN_MENU = 4;
    public static final int ARENA = 12;

    // State variables
    private static ScreenManager screenManager = null;
    private static ScreenRelaunchHandler screenRelaunchHandler = null;
    private static Screen screen = null;
    private static TerminalSize terminalSize = null;
    private static int screenSize = 0;

    private ScreenManager() {
    }

    public static Screen newScreen(int screenSize) throws IOException {
        // Calculate Terminal Cell Count
        final int numberCols = BASE_WIDTH * screenSize;
        final int numberRows = BASE_HEIGHT * screenSize;

        // Create TerminalSize
        TerminalSize terminalSize = new TerminalSize(numberCols, numberRows);

        // Return current screen if no changes are needed
        if (terminalSize.equals(ScreenManager.terminalSize) && ScreenManager.screen != null)
            return ScreenManager.screen;

        // Properly delete old screen if needed
        if (ScreenManager.screen != null) ScreenManager.screen.stopScreen();

        // Get screen dimensions
        int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        // Calculate terminal window dimensions (80% of screen)
        int windowHeight = (int) (screenHeight * SCREEN_OCCUPANCY);

        // Calculate font size and height
        int fontSize = windowHeight / numberRows;

        // Set up Lanterna terminal with calculated font size
        Terminal terminal = new DefaultTerminalFactory()
                // Set Terminal Size
                .setInitialTerminalSize(terminalSize)

                // Set Font Configuration
                .setTerminalEmulatorFontConfiguration(
                        SwingTerminalFontConfiguration.newInstance(
                                new Font("Monospaced", Font.BOLD, fontSize)
                        )
                )

                // Create Terminal Emulator
                .createTerminalEmulator();

        // Create ScreenManager object that will handle the terminal resize
        if (ScreenManager.screenManager == null)
            ScreenManager.screenManager = new ScreenManager();

        // Add terminal Resize Listener
        terminal.addResizeListener(ScreenManager.screenManager);

        // Create Screen
        Screen screen = new TerminalScreen(terminal);

        // Update state info
        ScreenManager.screen = screen;
        ScreenManager.terminalSize = terminalSize;
        ScreenManager.screenSize = screenSize;

        // Screen initial config
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    // Used to handel when terminal is resized
    @Override
    public void onResized(Terminal terminal, TerminalSize newTerminalSize) {
        // Don't do anything unless it actually changed
        if (newTerminalSize.equals(ScreenManager.terminalSize)) return;

        if (ScreenManager.screen != null)
            try {
                ScreenManager.screen.stopScreen();
                ScreenManager.screen = null;
                ScreenManager.newScreen(ScreenManager.screenSize);
                ScreenManager.screenRelaunchHandler.handle(ScreenManager.screen);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public static void setScreenRelaunchHandler(ScreenRelaunchHandler screenRelaunchHandler) {
        ScreenManager.screenRelaunchHandler = screenRelaunchHandler;
    }

}

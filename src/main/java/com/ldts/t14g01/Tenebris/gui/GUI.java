package com.ldts.t14g01.Tenebris.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalResizeListener;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import com.ldts.t14g01.Tenebris.utils.Position;

import java.awt.*;
import java.io.IOException;

public class GUI implements TerminalResizeListener {
    // Singleton
    private static final GUI guiInstance = new GUI();

    // Sets target window size and aspect ratio
    private static final double SCREEN_OCCUPANCY = 0.7;
    private static final int BASE_WIDTH = 16;
    private static final int BASE_HEIGHT = 5;
    private static final int MENU_SCALE = 4;
    private static final int GRAPHICS_SCALE = 20;

    // Screen Types
    public enum Type {
        GRAPHICS,
        MENU,
        YELLOW
    }

    // Colors
    public enum Colors {
        WHITE,
        YELLOW, BLACK
    }

    // Instance Variables
    private Screen screen;
    private Type type;
    private TerminalSize terminalSize;
    private boolean quitted = false;
    private boolean isDrawing = false;

    private GUI() {
    }

    public static GUI getGUI() {
        return GUI.guiInstance;
    }

    public void setType(Type type) throws IOException {
        // In case it's null close the screen
        if (type == null) {
            this.type = null;
            this.close();
            return;
        }

        // Return if nothing changed
        if (type.equals(this.type)) return;

        // Switch type
        this.type = type;
        this.createScreen();

    }

    private void createScreen() throws IOException {
        // Close current screen
        this.close();

        // Calculate Terminal Cell Count
        int scale = 0;
        switch (this.type) {
            case MENU -> scale = GUI.MENU_SCALE;
            case GRAPHICS -> scale = GUI.GRAPHICS_SCALE;
            case null, default -> scale = GUI.MENU_SCALE;
        }
        final int numberCols = BASE_WIDTH * scale;
        final int numberRows = BASE_HEIGHT * scale;

        // Create TerminalSize
        TerminalSize terminalSize = new TerminalSize(numberCols, numberRows);

        // Get user's screen dimensions
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
                // ToDo: Change font for graphics
                .setTerminalEmulatorFontConfiguration(
                        SwingTerminalFontConfiguration.newInstance(
                                new Font("Monospaced", Font.BOLD, fontSize)
                        )
                )

                // Create Terminal Emulator
                .createTerminalEmulator();

        // Add terminal Resize Listener
        terminal.addResizeListener(this);

        // Create Screen
        Screen screen = new TerminalScreen(terminal);

        // Update state info
        this.screen = screen;
        this.terminalSize = terminalSize;

        // Screen initial config
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
    }

    public void close() throws IOException {
        // Wait while the screen is being drawn to
        while (this.isDrawing) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (this.screen != null) this.screen.stopScreen();
    }

    public boolean quited() {
        return this.quitted;
    }

    public boolean stable() {
        return this.screen != null;
    }

    public void clear() {
        if (this.stable()) this.screen.clear();
    }

    public TextGraphics getTextGraphics() {
        return this.screen.newTextGraphics();
    }

    public TerminalSize getTerminalSize() {
        return this.screen.getTerminalSize();
    }

    public void refresh() throws IOException {
        this.screen.refresh();
    }

    public Action getAction() throws IOException, InterruptedException {
        // Return null if no screen
        if (this.screen == null) return null;

        // Read keystroke
        KeyStroke keyStroke = this.screen.pollInput();

        Action action = null;

        if (keyStroke != null) {
            switch (keyStroke.getKeyType()) {
                case Escape -> action = Action.ESC;
                case ArrowUp -> action = Action.LOOK_UP;
                case ArrowDown -> action = Action.LOOK_DOWN;
                case ArrowLeft -> action = Action.LOOK_LEFT;
                case ArrowRight -> action = Action.LOOK_RIGHT;
                case Enter -> action = Action.EXEC;
                case EOF -> this.handleEOF();
            }
            if (keyStroke.getCharacter() != null)
                switch (keyStroke.getCharacter()) {
                    case 'E', 'e', ' ' -> action = Action.EXEC;
                    case 'Q', 'q' -> action = Action.QUIT;
                }
        }

        return action;
    }

    private void handleEOF() throws InterruptedException, IOException {
        // Wait to give possible screen reload time
        Thread.sleep(250);

        KeyStroke keyStroke = this.screen.pollInput();
        if (keyStroke != null)
            if (keyStroke.getKeyType() == KeyType.EOF) this.quitted = true;
    }

    public void onResized(Terminal terminal, TerminalSize newSize) {
        // Don't do anything unless it actually changed
        if (newSize.equals(this.terminalSize)) return;

        // Reopen Screen
        try {
            this.close();
            this.createScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setIsDrawing(boolean isDrawing) {
        this.isDrawing = isDrawing;
    }


    private static TextColor mapTextColor(GUI.Colors color) {
        TextColor mapped = TextColor.ANSI.WHITE;
        switch (color) {
            case BLACK -> mapped = TextColor.ANSI.BLACK;
            case WHITE -> mapped = TextColor.ANSI.WHITE;
            case YELLOW -> mapped = TextColor.ANSI.YELLOW;
        }
        return mapped;
    }

    public void drawText(String text, Position position, Colors foreGround, Colors backGround) {
        TextGraphics tg = this.screen.newTextGraphics();
        tg.setForegroundColor(GUI.mapTextColor(foreGround));
        tg.setBackgroundColor(GUI.mapTextColor(backGround));
        tg.putString(position.x(), position.y(), text);
    }

}

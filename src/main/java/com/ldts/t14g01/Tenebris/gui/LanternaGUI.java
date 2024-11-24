package com.ldts.t14g01.Tenebris.gui;

import com.googlecode.lanterna.TerminalPosition;
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
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGUI implements GUI, TerminalResizeListener {
    // Singleton
    private static final GUI guiInstance;

    static {
        try {
            guiInstance = new LanternaGUI();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Font ARENA_BASE_FONT;

    static {
        // Load Square Font for the Arena
        URL resource = LanternaGUI.class.getClassLoader().getResource("fonts/square.ttf");
        File fontFile = null;
        Font font;

        try {
            fontFile = new File(resource.toURI());
            font = null;
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        ARENA_BASE_FONT = font;
    }

    // Sets target window size and aspect ratio
    private static final double MENU_SCREEN_OCCUPANCY = 0.65;
    private static final double ARENA_SCREEN_OCCUPANCY = 0.8;
    private static final int MENU_WIDTH = 64;
    private static final int MENU_HEIGHT = 20;
    private static final int ARENA_WIDTH = 480;
    private static final int ARENA_HEIGHT = 300;

    // Instance Variables
    private Screen screen;
    private Type type;
    private TerminalSize terminalSize;
    private boolean quitted = false;


    private LanternaGUI() throws IOException, FontFormatException, URISyntaxException {
    }

    public static GUI getGUI() {
        return LanternaGUI.guiInstance;
    }

    @Override
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
        int numberCols = 0;
        int numberRows = 0;
        switch (this.type) {
            case MENU -> {
                numberCols = LanternaGUI.MENU_WIDTH;
                numberRows = LanternaGUI.MENU_HEIGHT;
            }
            case ARENA -> {
                numberCols = LanternaGUI.ARENA_WIDTH;
                numberRows = LanternaGUI.ARENA_HEIGHT;
            }
            case null, default -> {
            }
        }

        // Create TerminalSize
        TerminalSize terminalSize = new TerminalSize(numberCols, numberRows);

        // Get user's screen dimensions
        int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        double screen_occupancy = LanternaGUI.MENU_SCREEN_OCCUPANCY;
        if (this.type == Type.ARENA) screen_occupancy = LanternaGUI.ARENA_SCREEN_OCCUPANCY;

        // Calculate terminal window dimensions (80% of screen)
        int windowHeight = (int) (screenHeight * screen_occupancy);

        // Calculate font size and height
        int fontSize = windowHeight / numberRows;

        // Set up Lanterna terminal with calculated font size
        DefaultTerminalFactory dtf = new DefaultTerminalFactory();

        // Set Terminal Size
        dtf.setInitialTerminalSize(terminalSize);

        // Set Font Configuration
        switch (this.type) {
            case MENU -> dtf.setTerminalEmulatorFontConfiguration(
                    SwingTerminalFontConfiguration.newInstance(
                            new Font("Monospaced", Font.BOLD, fontSize)
                    )
            );
            case ARENA -> dtf.setTerminalEmulatorFontConfiguration(
                    SwingTerminalFontConfiguration.newInstance(
                            LanternaGUI.ARENA_BASE_FONT.deriveFont(Font.PLAIN, fontSize)
                    )
            );
        }

        // Create Terminal Emulator
        Terminal terminal = dtf.createTerminalEmulator();

        // Create Screen
        Screen screen = new TerminalScreen(terminal);

        // Update state info
        this.screen = screen;
        this.terminalSize = terminalSize;

        // Add terminal Resize Listener
        terminal.addResizeListener(this);

        // Screen initial config
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
    }

    // Actions
    @Override
    public Action getAction() throws IOException, InterruptedException {
        if (this.quitted) return Action.QUIT;

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
                    case 'W', 'w' -> action = Action.MOVE_UP;
                    case 'S', 's' -> action = Action.MOVE_DOWN;
                    case 'A', 'a' -> action = Action.MOVE_LEFT;
                    case 'D', 'd' -> action = Action.MOVE_RIGHT;
                }
        }

        return action;
    }

    // Drawing
    @Override
    public void drawText(String text, Position position, Colors foreGround, Colors backGround) {
        TextGraphics tg = this.screen.newTextGraphics();
        tg.setForegroundColor(LanternaGUI.mapTextColor(foreGround));
        tg.setBackgroundColor(LanternaGUI.mapTextColor(backGround));
        tg.putString(position.x(), position.y(), text);
    }

    @Override
    public void drawRectangle(Position topLeft, Position size, Colors color) {
        TextGraphics tg = this.screen.newTextGraphics();
        tg.setBackgroundColor(LanternaGUI.mapTextColor(color));
        tg.drawRectangle(
                new TerminalPosition(topLeft.x(), topLeft.y()),
                new TerminalSize(size.x(), size.y()),
                ' '
        );
    }

    // Screen Management
    @Override
    public void refresh() throws IOException {
        if (this.stable()) this.screen.refresh();
    }

    @Override
    public void clear() {
        if (this.stable()) {
            TextGraphics tg = this.screen.newTextGraphics();
            tg.setBackgroundColor(TextColor.ANSI.BLACK);
            tg.fillRectangle(
                    new TerminalPosition(0, 0),
                    this.screen.getTerminalSize(),
                    ' '
            );
        }

    }

    @Override
    public void close() throws IOException {
        if (this.stable()) this.screen.stopScreen();
    }

    // Utils
    @Override
    public boolean stable() {
        return this.screen != null;
    }

    @Override
    public Position getWindowSize() {
        TerminalSize ts = null;
        if (this.stable()) ts = this.screen.getTerminalSize();

        Position size = new Position(0, 0);
        if (ts != null) size = new Position(ts.getColumns(), ts.getRows());

        return size;
    }

    private void handleEOF() throws InterruptedException, IOException {
        // Wait to give possible screen reload time
        Thread.sleep(250);

        KeyStroke keyStroke = this.screen.pollInput();
        if (keyStroke != null)
            if (keyStroke.getKeyType() == KeyType.EOF) this.quitted = true;
    }

    private static TextColor mapTextColor(GUI.Colors color) {
        TextColor mapped = TextColor.ANSI.WHITE;
        switch (color) {
            case BLACK -> mapped = TextColor.ANSI.BLACK;
            case CYAN -> mapped = TextColor.ANSI.CYAN;
            case YELLOW -> mapped = TextColor.ANSI.YELLOW;
            case BRIGHT_GREEN -> mapped = TextColor.ANSI.GREEN_BRIGHT;
            case BRIGHT_YELLOW -> mapped = TextColor.Factory.fromString("#DAA520");
        }
        return mapped;
    }

    @Override
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

    // This function is only used for tests and should not be used in any other way
    public void setScreen(Screen screen) {
        this.screen = screen;
    }
}

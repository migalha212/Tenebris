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
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;
import com.ldts.t14g01.Tenebris.utils.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;

public class LanternaGUI implements GUI, TerminalResizeListener, KeyListener {
    // Sets target window size and aspect ratio
    private static final double MENU_SCREEN_OCCUPANCY = 0.65;
    private static final double ARENA_SCREEN_OCCUPANCY = 0.8;
    private static final int MENU_WIDTH = 64;
    private static final int MENU_HEIGHT = 20;
    private static final int ARENA_WIDTH = 480;
    private static final int ARENA_HEIGHT = 300;

    // Instance Variables
    private final Font ARENA_BASE_FONT;
    private Screen screen;
    private Type type;
    private TerminalSize terminalSize;
    private boolean quited = false;
    private final Set<Action> activeActions;

    // Sprites
    private final BufferedImage sprite_dylan_idle_1;
    private final BufferedImage sprite_dylan_idle_2;
    private final BufferedImage sprite_dylan_back_1;
    private final BufferedImage sprite_dylan_back_2;
    private final BufferedImage sprite_dylan_front_1;
    private final BufferedImage sprite_dylan_front_2;
    private final BufferedImage sprite_dylan_left_1;
    private final BufferedImage sprite_dylan_left_2;
    private final BufferedImage sprite_dylan_right_1;
    private final BufferedImage sprite_dylan_right_2;

    private final BufferedImage sprite_wall;
    private final BufferedImage sprite_sandbag;
    private final BufferedImage sprite_spikes;

    private final List<BufferedImage> sprite_death_blood;

    private final BufferedImage sprite_tenebris_harbinger_idle_1;
    private final BufferedImage sprite_tenebris_harbinger_idle_2;
    private final BufferedImage sprite_tenebris_harbinger_front_1;
    private final BufferedImage sprite_tenebris_harbinger_front_2;
    private final BufferedImage sprite_tenebris_harbinger_back_1;
    private final BufferedImage sprite_tenebris_harbinger_back_2;
    private final BufferedImage sprite_tenebris_harbinger_right_1;
    private final BufferedImage sprite_tenebris_harbinger_right_2;
    private final BufferedImage sprite_tenebris_harbinder_left_1;
    private final BufferedImage sprite_tenebris_harbinger_left_2;

    private final BufferedImage sprite_tenebris_heavy_idle_1;
    private final BufferedImage sprite_tenebris_heavy_idle_2;
    private final BufferedImage sprite_tenebris_heavy_front_1;
    private final BufferedImage sprite_tenebris_heavy_front_2;
    private final BufferedImage sprite_tenebris_heavy_back_1;
    private final BufferedImage sprite_tenebris_heavy_back_2;
    private final BufferedImage sprite_tenebris_heavy_right_1;
    private final BufferedImage sprite_tenebris_heavy_right_2;
    private final BufferedImage sprite_tenebris_heavy_left_1;
    private final BufferedImage sprite_tenebris_heavy_left_2;

    private final BufferedImage sprite_tenebris_peon_idle_1;
    private final BufferedImage sprite_tenebris_peon_idle_2;
    private final BufferedImage sprite_tenebris_peon_front_1;
    private final BufferedImage sprite_tenebris_peon_front_2;
    private final BufferedImage sprite_tenebris_peon_back_1;
    private final BufferedImage sprite_tenebris_peon_back_2;
    private final BufferedImage sprite_tenebris_peon_right_1;
    private final BufferedImage sprite_tenebris_peon_right_2;
    private final BufferedImage sprite_tenebris_peon_left_1;
    private final BufferedImage sprite_tenebris_peon_left_2;

    private final BufferedImage sprite_tenebris_spiked_scout_idle_1;
    private final BufferedImage sprite_tenebris_spiked_scout_idle_2;
    private final BufferedImage sprite_tenebris_spiked_scout_front_1;
    private final BufferedImage sprite_tenebris_spiked_scout_front_2;
    private final BufferedImage sprite_tenebris_spiked_scout_back_1;
    private final BufferedImage sprite_tenebris_spiked_scout_back_2;
    private final BufferedImage sprite_tenebris_spiked_scout_right_1;
    private final BufferedImage sprite_tenebris_spiked_scout_right_2;
    private final BufferedImage sprite_tenebris_spiked_scout_left_1;
    private final BufferedImage sprite_tenebris_spiked_scout_left_2;

    private final BufferedImage sprite_tenebris_warden_idle_1;
    private final BufferedImage sprite_tenebris_warden_idle_2;
    private final BufferedImage sprite_tenebris_warden_front_1;
    private final BufferedImage sprite_tenebris_warden_front_2;
    private final BufferedImage sprite_tenebris_warden_back_1;
    private final BufferedImage sprite_tenebris_warden_back_2;
    private final BufferedImage sprite_tenebris_warden_right_1;
    private final BufferedImage sprite_tenebris_warden_right_2;
    private final BufferedImage sprite_tenebris_warden_left_1;
    private final BufferedImage sprite_tenebris_warden_left_2;

    // Singleton
    private static GUI guiInstance;

    public static GUI getGUI() {
        if (LanternaGUI.guiInstance == null) LanternaGUI.guiInstance = new LanternaGUI();
        return LanternaGUI.guiInstance;
    }

    private LanternaGUI() {
        this.activeActions = new TreeSet<>();

        // Load Square Font for the Arena
        Font font;

        try {
            URL resource = LanternaGUI.class.getClassLoader().getResource("fonts/square.ttf");
            File fontFile;
            assert resource != null;
            fontFile = new File(resource.toURI());
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (URISyntaxException | FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        this.ARENA_BASE_FONT = font;

        // Load Sprites
        try {
            this.sprite_dylan_idle_1 = ImageIO.read(new File("src/main/resources/sprites/dylan/idle/1.png"));
            this.sprite_dylan_idle_2 = ImageIO.read(new File("src/main/resources/sprites/dylan/idle/2.png"));
            this.sprite_dylan_front_1 = ImageIO.read(new File("src/main/resources/sprites/dylan/walk-front/1.png"));
            this.sprite_dylan_front_2 = ImageIO.read(new File("src/main/resources/sprites/dylan/walk-front/2.png"));
            this.sprite_dylan_back_1 = ImageIO.read(new File("src/main/resources/sprites/dylan/walk-back/1.png"));
            this.sprite_dylan_back_2 = ImageIO.read(new File("src/main/resources/sprites/dylan/walk-back/2.png"));
            this.sprite_dylan_left_1 = ImageIO.read(new File("src/main/resources/sprites/dylan/walk-left/1.png"));
            this.sprite_dylan_left_2 = ImageIO.read(new File("src/main/resources/sprites/dylan/walk-left/2.png"));
            this.sprite_dylan_right_1 = ImageIO.read(new File("src/main/resources/sprites/dylan/walk-right/1.png"));
            this.sprite_dylan_right_2 = ImageIO.read(new File("src/main/resources/sprites/dylan/walk-right/2.png"));

            this.sprite_wall = ImageIO.read(new File("src/main/resources/sprites/elements/wall.png"));
            this.sprite_sandbag = ImageIO.read(new File("src/main/resources/sprites/elements/sandbag.png"));
            this.sprite_spikes = ImageIO.read(new File("src/main/resources/sprites/elements/spikes.png"));

            this.sprite_death_blood = new ArrayList<>();
            for (int i = 1; i <= 16; i++) this.sprite_death_blood.add(ImageIO.read(new File("src/main/resources/sprites/particles/death-blood/" + i + ".png")));

            this.sprite_tenebris_harbinger_idle_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-harbinder/idle/1.png"));
            this.sprite_tenebris_harbinger_idle_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-harbinder/idle/2.png"));
            this.sprite_tenebris_harbinger_front_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-harbinder/walk-front/1.png"));
            this.sprite_tenebris_harbinger_front_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-harbinder/walk-front/2.png"));
            this.sprite_tenebris_harbinger_back_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-harbinder/walk-back/1.png"));
            this.sprite_tenebris_harbinger_back_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-harbinder/walk-back/2.png"));
            this.sprite_tenebris_harbinger_right_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-harbinder/walk-right/1.png"));
            this.sprite_tenebris_harbinger_right_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-harbinder/walk-right/2.png"));
            this.sprite_tenebris_harbinder_left_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-harbinder/walk-left/1.png"));
            this.sprite_tenebris_harbinger_left_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-harbinder/walk-left/2.png"));

            this.sprite_tenebris_heavy_idle_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-heavy/idle/1.png"));
            this.sprite_tenebris_heavy_idle_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-heavy/idle/2.png"));
            this.sprite_tenebris_heavy_front_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-heavy/walk-front/1.png"));
            this.sprite_tenebris_heavy_front_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-heavy/walk-front/2.png"));
            this.sprite_tenebris_heavy_back_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-heavy/walk-back/1.png"));
            this.sprite_tenebris_heavy_back_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-heavy/walk-back/2.png"));
            this.sprite_tenebris_heavy_right_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-heavy/walk-right/1.png"));
            this.sprite_tenebris_heavy_right_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-heavy/walk-right/2.png"));
            this.sprite_tenebris_heavy_left_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-heavy/walk-left/1.png"));
            this.sprite_tenebris_heavy_left_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-heavy/walk-left/2.png"));

            this.sprite_tenebris_peon_idle_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-peon/idle/1.png"));
            this.sprite_tenebris_peon_idle_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-peon/idle/2.png"));
            this.sprite_tenebris_peon_front_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-peon/walk-front/1.png"));
            this.sprite_tenebris_peon_front_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-peon/walk-front/2.png"));
            this.sprite_tenebris_peon_back_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-peon/walk-back/1.png"));
            this.sprite_tenebris_peon_back_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-peon/walk-back/2.png"));
            this.sprite_tenebris_peon_right_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-peon/walk-right/1.png"));
            this.sprite_tenebris_peon_right_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-peon/walk-right/2.png"));
            this.sprite_tenebris_peon_left_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-peon/walk-left/1.png"));
            this.sprite_tenebris_peon_left_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-peon/walk-left/2.png"));

            this.sprite_tenebris_spiked_scout_idle_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-spiked-scout/idle/1.png"));
            this.sprite_tenebris_spiked_scout_idle_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-spiked-scout/idle/2.png"));
            this.sprite_tenebris_spiked_scout_front_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-spiked-scout/walk-front/1.png"));
            this.sprite_tenebris_spiked_scout_front_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-spiked-scout/walk-front/2.png"));
            this.sprite_tenebris_spiked_scout_back_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-spiked-scout/walk-back/1.png"));
            this.sprite_tenebris_spiked_scout_back_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-spiked-scout/walk-back/2.png"));
            this.sprite_tenebris_spiked_scout_right_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-spiked-scout/walk-right/1.png"));
            this.sprite_tenebris_spiked_scout_right_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-spiked-scout/walk-right/2.png"));
            this.sprite_tenebris_spiked_scout_left_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-spiked-scout/walk-left/1.png"));
            this.sprite_tenebris_spiked_scout_left_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-spiked-scout/walk-left/2.png"));

            this.sprite_tenebris_warden_idle_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-warden/idle/1.png"));
            this.sprite_tenebris_warden_idle_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-warden/idle/2.png"));
            this.sprite_tenebris_warden_front_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-warden/walk-front/1.png"));
            this.sprite_tenebris_warden_front_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-warden/walk-front/2.png"));
            this.sprite_tenebris_warden_back_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-warden/walk-back/1.png"));
            this.sprite_tenebris_warden_back_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-warden/walk-back/2.png"));
            this.sprite_tenebris_warden_right_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-warden/walk-right/1.png"));
            this.sprite_tenebris_warden_right_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-warden/walk-right/2.png"));
            this.sprite_tenebris_warden_left_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-warden/walk-left/1.png"));
            this.sprite_tenebris_warden_left_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-warden/walk-left/2.png"));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createScreen() throws IOException {
        // Close current screen
        this.close();

        // Calculate Terminal Cell Count
        if (this.type == null) throw new RuntimeException("Trying to create screen without specifying a type");
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

        // Set Terminal Size
        DefaultTerminalFactory dtf = new DefaultTerminalFactory();
        dtf.setInitialTerminalSize(terminalSize);

        // Set Font Configuration
        switch (this.type) {
            case MENU ->
                    dtf.setTerminalEmulatorFontConfiguration(SwingTerminalFontConfiguration.newInstance(new Font("Monospaced", Font.BOLD, fontSize)));
            case ARENA ->
                    dtf.setTerminalEmulatorFontConfiguration(SwingTerminalFontConfiguration.newInstance(this.ARENA_BASE_FONT.deriveFont(Font.PLAIN, fontSize)));
        }

        // Create Terminal Emulator
        SwingTerminalFrame terminal = (SwingTerminalFrame) dtf.createTerminal();

        // Add key listeners
        terminal.addKeyListener(this);

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
        if (this.quited) return Action.QUIT;

        // Return null if no screen
        if (!this.stable()) return null;

        // Set Panel as Focus
        // This is needed because if the screen gains focus the underPanel
        // where the key-listeners are placed stop receiving input
        // This is here simply because this is a very frequent function to be called
        ((SwingTerminalFrame) ((TerminalScreen) this.screen).getTerminal()).requestFocus();

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
            if (keyStroke.getCharacter() != null) switch (keyStroke.getCharacter()) {
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

    @Override
    public Set<Action> getActiveActions() {
        return new TreeSet<>(this.activeActions);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Dispatch Keys to Terminal
        SwingTerminalFrame STF = ((SwingTerminalFrame) ((TerminalScreen) this.screen).getTerminal());
        switch (e.getKeyCode()) {
            case 37 -> STF.addInput(new KeyStroke(KeyType.ArrowLeft));
            case 38 -> STF.addInput(new KeyStroke(KeyType.ArrowUp));
            case 39 -> STF.addInput(new KeyStroke(KeyType.ArrowRight));
            case 40 -> STF.addInput(new KeyStroke(KeyType.ArrowDown));
            case 27 -> STF.addInput(new KeyStroke(KeyType.Escape));
            case 10 -> STF.addInput(new KeyStroke(KeyType.Enter));
            case 32 -> STF.addInput(new KeyStroke(' ', false, false));
            case 69 -> STF.addInput(new KeyStroke('e', false, false));
            case 81 -> STF.addInput(new KeyStroke('q', false, false));
            case 87 -> STF.addInput(new KeyStroke('w', false, false));
            case 83 -> STF.addInput(new KeyStroke('s', false, false));
            case 65 -> STF.addInput(new KeyStroke('a', false, false));
            case 68 -> STF.addInput(new KeyStroke('d', false, false));
            case 49 -> STF.addInput(new KeyStroke('1', false, false));
            case 50 -> STF.addInput(new KeyStroke('2', false, false));
            case 51 -> STF.addInput(new KeyStroke('3', false, false));
            default -> {
            }
        }

        // Add to Action List
        Action action;
        switch (e.getKeyCode()) {
            case 37 -> action = Action.LOOK_LEFT;
            case 38 -> action = Action.LOOK_UP;
            case 39 -> action = Action.LOOK_RIGHT;
            case 40 -> action = Action.LOOK_DOWN;
            case 65 -> action = Action.MOVE_LEFT;
            case 68 -> action = Action.MOVE_RIGHT;
            case 83 -> action = Action.MOVE_DOWN;
            case 87 -> action = Action.MOVE_UP;
            default -> action = null;
        }
        if (action != null) this.activeActions.add(action);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Remove from action list
        Action action;
        switch (e.getKeyCode()) {
            case 37 -> action = Action.LOOK_LEFT;
            case 38 -> action = Action.LOOK_UP;
            case 39 -> action = Action.LOOK_RIGHT;
            case 40 -> action = Action.LOOK_DOWN;
            case 65 -> action = Action.MOVE_LEFT;
            case 68 -> action = Action.MOVE_RIGHT;
            case 83 -> action = Action.MOVE_DOWN;
            case 87 -> action = Action.MOVE_UP;
            default -> action = null;
        }
        if (action != null) this.activeActions.remove(action);
    }

    // Drawing
    @Override
    public void drawText(String text, Vector2D position, Colors foreGround, Colors backGround) {
        TextGraphics tg = this.screen.newTextGraphics();
        tg.setForegroundColor(LanternaGUI.mapTextColor(foreGround));
        tg.setBackgroundColor(LanternaGUI.mapTextColor(backGround));
        tg.putString(position.x(), position.y(), text);
    }

    @Override
    public void drawArenaBackGround() {
        if (!this.stable()) return;

        TextGraphics tg = this.screen.newTextGraphics();
        tg.setBackgroundColor(new TextColor.RGB(159, 153, 116));
        tg.fillRectangle(new TerminalPosition(0, 0), this.screen.getTerminalSize(), ' ');
    }

    @Override
    public void drawDylan(Vector2D position, AnimationState state) {
        switch (state) {
            case IDLE_1 -> this.drawImage(position, this.sprite_dylan_idle_1);
            case IDLE_2 -> this.drawImage(position, this.sprite_dylan_idle_2);
            case FRONT_1 -> this.drawImage(position, this.sprite_dylan_front_1);
            case FRONT_2 -> this.drawImage(position, this.sprite_dylan_front_2);
            case BACK_1 -> this.drawImage(position, this.sprite_dylan_back_1);
            case BACK_2 -> this.drawImage(position, this.sprite_dylan_back_2);
            case LEFT_1 -> this.drawImage(position, this.sprite_dylan_left_1);
            case LEFT_2 -> this.drawImage(position, this.sprite_dylan_left_2);
            case RIGHT_1 -> this.drawImage(position, this.sprite_dylan_right_1);
            case RIGHT_2 -> this.drawImage(position, this.sprite_dylan_right_2);
            case null, default -> throw new RuntimeException("Invalid state for LanternaGUI.drawDylan");
        }
    }

    @Override
    public void drawWall(Vector2D position) {
        this.drawImage(position, this.sprite_wall);
    }

    @Override
    public void drawSandbag(Vector2D position) {
        this.drawImage(position, this.sprite_sandbag);
    }

    @Override
    public void drawSpikes(Vector2D position) {
        this.drawImage(position, this.sprite_spikes);
    }

    @Override
    public void drawMonster(Vector2D position, Monster monster, AnimationState state) {
        switch (monster) {
            case TENEBRIS_HARBINGER -> {
                switch (state) {
                    case IDLE_1 -> this.drawImage(position, sprite_tenebris_harbinger_idle_1);
                    case IDLE_2 -> this.drawImage(position, sprite_tenebris_harbinger_idle_2);
                    case FRONT_1 -> this.drawImage(position, sprite_tenebris_harbinger_front_1);
                    case FRONT_2 -> this.drawImage(position, sprite_tenebris_harbinger_front_2);
                    case BACK_1 -> this.drawImage(position, sprite_tenebris_harbinger_back_1);
                    case BACK_2 -> this.drawImage(position, sprite_tenebris_harbinger_back_2);
                    case LEFT_1 -> this.drawImage(position, sprite_tenebris_harbinder_left_1);
                    case LEFT_2 -> this.drawImage(position, sprite_tenebris_harbinger_left_2);
                    case RIGHT_1 -> this.drawImage(position, sprite_tenebris_harbinger_right_1);
                    case RIGHT_2 -> this.drawImage(position, sprite_tenebris_harbinger_right_2);
                    case null, default -> throw new RuntimeException("Invalid state for LanternaGUI.drawMonster");
                }
            }
            case TENEBRIS_HEAVY -> {
                switch (state) {
                    case IDLE_1 -> this.drawImage(position, sprite_tenebris_heavy_idle_1);
                    case IDLE_2 -> this.drawImage(position, sprite_tenebris_heavy_idle_2);
                    case FRONT_1 -> this.drawImage(position, sprite_tenebris_heavy_front_1);
                    case FRONT_2 -> this.drawImage(position, sprite_tenebris_heavy_front_2);
                    case BACK_1 -> this.drawImage(position, sprite_tenebris_heavy_back_1);
                    case BACK_2 -> this.drawImage(position, sprite_tenebris_heavy_back_2);
                    case LEFT_1 -> this.drawImage(position, sprite_tenebris_heavy_left_1);
                    case LEFT_2 -> this.drawImage(position, sprite_tenebris_heavy_left_2);
                    case RIGHT_1 -> this.drawImage(position, sprite_tenebris_heavy_right_1);
                    case RIGHT_2 -> this.drawImage(position, sprite_tenebris_heavy_right_2);
                    case null, default -> throw new RuntimeException("Invalid state for LanternaGUI.drawMonster");
                }
            }
            case TENEBRIS_PEON -> {
                switch (state) {
                    case IDLE_1 -> this.drawImage(position, sprite_tenebris_peon_idle_1);
                    case IDLE_2 -> this.drawImage(position, sprite_tenebris_peon_idle_2);
                    case FRONT_1 -> this.drawImage(position, sprite_tenebris_peon_front_1);
                    case FRONT_2 -> this.drawImage(position, sprite_tenebris_peon_front_2);
                    case BACK_1 -> this.drawImage(position, sprite_tenebris_peon_back_1);
                    case BACK_2 -> this.drawImage(position, sprite_tenebris_peon_back_2);
                    case LEFT_1 -> this.drawImage(position, sprite_tenebris_peon_left_1);
                    case LEFT_2 -> this.drawImage(position, sprite_tenebris_peon_left_2);
                    case RIGHT_1 -> this.drawImage(position, sprite_tenebris_peon_right_1);
                    case RIGHT_2 -> this.drawImage(position, sprite_tenebris_peon_right_2);
                    case null, default -> throw new RuntimeException("Invalid state for LanternaGUI.drawMonster");
                }
            }
            case TENEBRIS_SPIKED_SCOUT -> {
                switch (state) {
                    case IDLE_1 -> this.drawImage(position, sprite_tenebris_spiked_scout_idle_1);
                    case IDLE_2 -> this.drawImage(position, sprite_tenebris_spiked_scout_idle_2);
                    case FRONT_1 -> this.drawImage(position, sprite_tenebris_spiked_scout_front_1);
                    case FRONT_2 -> this.drawImage(position, sprite_tenebris_spiked_scout_front_2);
                    case BACK_1 -> this.drawImage(position, sprite_tenebris_spiked_scout_back_1);
                    case BACK_2 -> this.drawImage(position, sprite_tenebris_spiked_scout_back_2);
                    case LEFT_1 -> this.drawImage(position, sprite_tenebris_spiked_scout_left_1);
                    case LEFT_2 -> this.drawImage(position, sprite_tenebris_spiked_scout_left_2);
                    case RIGHT_1 -> this.drawImage(position, sprite_tenebris_spiked_scout_right_1);
                    case RIGHT_2 -> this.drawImage(position, sprite_tenebris_spiked_scout_right_2);
                    case null, default -> throw new RuntimeException("Invalid state for LanternaGUI.drawMonster");
                }
            }
            case TENEBRIS_WARDEN -> {
                switch (state) {
                    case IDLE_1 -> this.drawImage(position, sprite_tenebris_warden_idle_1);
                    case IDLE_2 -> this.drawImage(position, sprite_tenebris_warden_idle_2);
                    case FRONT_1 -> this.drawImage(position, sprite_tenebris_warden_front_1);
                    case FRONT_2 -> this.drawImage(position, sprite_tenebris_warden_front_2);
                    case BACK_1 -> this.drawImage(position, sprite_tenebris_warden_back_1);
                    case BACK_2 -> this.drawImage(position, sprite_tenebris_warden_back_2);
                    case LEFT_1 -> this.drawImage(position, sprite_tenebris_warden_left_1);
                    case LEFT_2 -> this.drawImage(position, sprite_tenebris_warden_left_2);
                    case RIGHT_1 -> this.drawImage(position, sprite_tenebris_warden_right_1);
                    case RIGHT_2 -> this.drawImage(position, sprite_tenebris_warden_right_2);
                    case null, default -> throw new RuntimeException("Invalid state for LanternaGUI.drawMonster");
                }
            }
            case null, default -> {}
        }
    }

    @Override
    public void drawDeathBlood(Vector2D position, int frameNumber) {
        if (frameNumber <= 0 || frameNumber > GUI.DEATH_BLOOD_FRAME_COUNT)
            throw new RuntimeException("Drawing Invalid Death Blood Frame Number");

        Vector2D spritePosition = position.add(new Vector2D(0, -(this.sprite_death_blood.get(0).getHeight() / 4)));
        this.drawImage(spritePosition, this.sprite_death_blood.get(frameNumber - 1));
    }

    private void drawImage(Vector2D position, BufferedImage sprite) {
        if (!this.stable()) return;

        TextGraphics tg = this.screen.newTextGraphics();
        for (int x = 0; x < sprite.getWidth(); x++) {
            for (int y = 0; y < sprite.getHeight(); y++) {
                int a = sprite.getRGB(x, y);
                int alpha = (a >> 24) & 0xff;
                int red = (a >> 16) & 255;
                int green = (a >> 8) & 255;
                int blue = a & 255;

                int pX = position.x() - sprite.getWidth() / 2 + x;
                int pY = position.y() - sprite.getHeight() / 2 + y;

                if (alpha != 0) {
                    tg.setForegroundColor(new TextColor.RGB(red, green, blue));
                    tg.setBackgroundColor(new TextColor.RGB(red, green, blue));
                    tg.setCharacter(new TerminalPosition(pX, pY), ' ');
                }
            }
        }

    }

    // Screen Management
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

    @Override
    public void refresh() throws IOException {
        if (this.stable()) this.screen.refresh();
    }

    @Override
    public void clear() {
        if (this.stable()) {
            TextGraphics tg = this.screen.newTextGraphics();
            tg.setBackgroundColor(TextColor.ANSI.BLACK);
            tg.fillRectangle(new TerminalPosition(0, 0), this.screen.getTerminalSize(), ' ');
        }
    }

    @Override
    public void close() throws IOException {
        if (this.stable()) this.screen.stopScreen();
    }

    // Utils
    private boolean stable() {
        return this.screen != null;
    }

    @Override
    public Vector2D getWindowSize() {
        TerminalSize ts = null;
        if (this.stable()) ts = this.screen.getTerminalSize();

        Vector2D size = new Vector2D(0, 0);
        if (ts != null) size = new Vector2D(ts.getColumns(), ts.getRows());

        return size;
    }

    private void handleEOF() throws InterruptedException, IOException {
        // Wait to give possible screen reload time
        Thread.sleep(250);

        KeyStroke keyStroke = this.screen.pollInput();
        if (keyStroke != null) if (keyStroke.getKeyType() == KeyType.EOF) this.quited = true;
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

    // This function is only used for tests and should not be used in any other way
    public Screen getScreen() {
        return this.screen;
    }
}

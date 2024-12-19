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
import com.ldts.t14g01.Tenebris.utils.Pair;
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
import java.util.List;
import java.util.*;

public class LanternaGUI implements GUI, TerminalResizeListener, KeyListener {
    // Sets target window size and aspect ratio
    private static final double SCREEN_OCCUPANCY = 0.8;
    private static final int SCREEN_WIDTH = 486;
    private static final int SCREEN_HEIGHT = 306;

    // Instance Variables
    private final Font BASE_FONT;
    private Screen screen;
    private TerminalSize terminalSize;
    private boolean quited = false;
    private final Set<Action> activeActions;

    // Menus
    private final EnumMap<Menus, BufferedImage> sprite_menu_backgrounds;
    private final EnumMap<Menus, BufferedImage> sprite_menu_titles;
    private final EnumMap<Menu_Options, Pair<BufferedImage>> sprite_menu_options;

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

    private final BufferedImage sprite_empty_health_bar;
    private final BufferedImage sprite_green_health_part;
    private final BufferedImage sprite_pistol;
    private final BufferedImage sprite_grenade_launcher;

    private final BufferedImage sprite_wall;
    private final BufferedImage sprite_breakable_wall;
    private final BufferedImage sprite_sandbag;
    private final BufferedImage sprite_spikes;

    private final List<BufferedImage> sprite_explosion;
    private final List<BufferedImage> sprite_spell_explostion;
    private final List<BufferedImage> sprite_death_blood;
    private final List<BufferedImage> sprite_damage_blood;
    private final List<BufferedImage> sprite_breakable_wall_damage;

    private final BufferedImage sprite_tenebris_harbinger_idle_1;
    private final BufferedImage sprite_tenebris_harbinger_idle_2;
    private final BufferedImage sprite_tenebris_harbinger_front_1;
    private final BufferedImage sprite_tenebris_harbinger_front_2;
    private final BufferedImage sprite_tenebris_harbinger_back_1;
    private final BufferedImage sprite_tenebris_harbinger_back_2;
    private final BufferedImage sprite_tenebris_harbinger_right_1;
    private final BufferedImage sprite_tenebris_harbinger_right_2;
    private final BufferedImage sprite_tenebris_harbinger_left_1;
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

    private final BufferedImage sprite_bullet_horizontal;
    private final BufferedImage sprite_bullet_vertical;
    private final BufferedImage sprite_explosive;
    private final BufferedImage sprite_spell;

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
        this.BASE_FONT = font;

        // Load Sprites
        try {
            this.sprite_menu_backgrounds = new EnumMap<>(Menus.class);
            this.sprite_menu_titles = new EnumMap<>(Menus.class);
            this.sprite_menu_options = new EnumMap<>(Menu_Options.class);

            // Menus Backgrounds
            this.sprite_menu_backgrounds.put(Menus.MAIN_MENU, ImageIO.read(new File("src/main/resources/sprites/menus/main-background.png")));

            // Menus Titles
            this.sprite_menu_titles.put(Menus.MAIN_MENU, ImageIO.read(new File("src/main/resources/sprites/menus/titles/main-menu.png")));

            // Main Menu Options
            this.sprite_menu_options.put(Menu_Options.MAIN_MENU_NEW_GAME, new Pair<>(ImageIO.read(new File("src/main/resources/sprites/menus/options/main-menu/new-game-selected.png")), ImageIO.read(new File("src/main/resources/sprites/menus/options/main-menu/new-game.png"))));
            this.sprite_menu_options.put(Menu_Options.MAIN_MENU_CONTINUE, new Pair<>(ImageIO.read(new File("src/main/resources/sprites/menus/options/main-menu/continue-selected.png")), ImageIO.read(new File("src/main/resources/sprites/menus/options/main-menu/continue.png"))));
            this.sprite_menu_options.put(Menu_Options.MAIN_MENU_LOAD_GAME, new Pair<>(ImageIO.read(new File("src/main/resources/sprites/menus/options/main-menu/load-game-selected.png")), ImageIO.read(new File("src/main/resources/sprites/menus/options/main-menu/load-game.png"))));
            this.sprite_menu_options.put(Menu_Options.MAIN_MENU_LEVELS, new Pair<>(ImageIO.read(new File("src/main/resources/sprites/menus/options/main-menu/levels-selected.png")), ImageIO.read(new File("src/main/resources/sprites/menus/options/main-menu/levels.png"))));
            this.sprite_menu_options.put(Menu_Options.MAIN_MENU_HOW_TO_PLAY, new Pair<>(ImageIO.read(new File("src/main/resources/sprites/menus/options/main-menu/how-to-play-selected.png")), ImageIO.read(new File("src/main/resources/sprites/menus/options/main-menu/how-to-play.png"))));
            this.sprite_menu_options.put(Menu_Options.MAIN_MENU_CREDITS, new Pair<>(ImageIO.read(new File("src/main/resources/sprites/menus/options/main-menu/credits-selected.png")), ImageIO.read(new File("src/main/resources/sprites/menus/options/main-menu/credits.png"))));
            this.sprite_menu_options.put(Menu_Options.MAIN_MENU_EXIT, new Pair<>(ImageIO.read(new File("src/main/resources/sprites/menus/options/main-menu/exit-selected.png")), ImageIO.read(new File("src/main/resources/sprites/menus/options/main-menu/exit.png"))));

            // TODO ADD OTHERS

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

            this.sprite_empty_health_bar = ImageIO.read(new File("src/main/resources/sprites/dylan/hp/emptybar.png"));
            this.sprite_green_health_part = ImageIO.read(new File("src/main/resources/sprites/dylan/hp/healthpart.png"));
            this.sprite_pistol = ImageIO.read(new File("src/main/resources/sprites/dylan/weapons/pistol.png"));
            this.sprite_grenade_launcher = ImageIO.read(new File("src/main/resources/sprites/dylan/weapons/grenade-launcher.png"));

            this.sprite_wall = ImageIO.read(new File("src/main/resources/sprites/elements/wall.png"));
            this.sprite_breakable_wall = ImageIO.read(new File("src/main/resources/sprites/elements/breakablewall.png"));
            this.sprite_sandbag = ImageIO.read(new File("src/main/resources/sprites/elements/sandbag.png"));
            this.sprite_spikes = ImageIO.read(new File("src/main/resources/sprites/elements/spikes.png"));

            this.sprite_explosion = new ArrayList<>();
            for (int i = 1; i <= GUI.EXPLOSION_FRAME_COUNT; i++)
                this.sprite_explosion.add(ImageIO.read(new File("src/main/resources/sprites/particles/explosion/" + i + ".png")));

            this.sprite_spell_explostion = new ArrayList<>();
            for (int i = 1; i <= GUI.SPELL_EXPLOSION_FRAME_COUNT; i++)
                this.sprite_spell_explostion.add(ImageIO.read(new File("src/main/resources/sprites/particles/spell-explosion/" + i + ".png")));

            this.sprite_death_blood = new ArrayList<>();
            for (int i = 1; i <= GUI.DEATH_BLOOD_FRAME_COUNT; i++)
                this.sprite_death_blood.add(ImageIO.read(new File("src/main/resources/sprites/particles/death-blood/" + i + ".png")));

            this.sprite_damage_blood = new ArrayList<>();
            for (int i = 1; i <= GUI.DAMAGE_BLOOD_FRAME_COUNT; i++)
                this.sprite_damage_blood.add(ImageIO.read(new File("src/main/resources/sprites/particles/damage-blood/" + i + ".png")));

            this.sprite_breakable_wall_damage = new ArrayList<>();
            for (int i = 1; i <= GUI.BREAKABLE_WALL_DAMAGE_FRAME_COUNT; i++) {
                this.sprite_breakable_wall_damage.add(ImageIO.read(new File("src/main/resources/sprites/particles/breakable-wall-damage/" + i + ".png")));
            }
            this.sprite_tenebris_harbinger_idle_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-harbinder/idle/1.png"));
            this.sprite_tenebris_harbinger_idle_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-harbinder/idle/2.png"));
            this.sprite_tenebris_harbinger_front_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-harbinder/walk-front/1.png"));
            this.sprite_tenebris_harbinger_front_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-harbinder/walk-front/2.png"));
            this.sprite_tenebris_harbinger_back_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-harbinder/walk-back/1.png"));
            this.sprite_tenebris_harbinger_back_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-harbinder/walk-back/2.png"));
            this.sprite_tenebris_harbinger_right_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-harbinder/walk-right/1.png"));
            this.sprite_tenebris_harbinger_right_2 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-harbinder/walk-right/2.png"));
            this.sprite_tenebris_harbinger_left_1 = ImageIO.read(new File("src/main/resources/sprites/monsters/tenebris-harbinder/walk-left/1.png"));
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

            this.sprite_bullet_horizontal = ImageIO.read(new File("src/main/resources/sprites/projectiles/bullet/horizontal.png"));
            this.sprite_bullet_vertical = ImageIO.read(new File("src/main/resources/sprites/projectiles/bullet/vertical.png"));
            this.sprite_explosive = ImageIO.read(new File("src/main/resources/sprites/projectiles/explosive/explosive.png"));
            this.sprite_spell = ImageIO.read(new File("src/main/resources/sprites/projectiles/spell/spell.png"));

            this.createScreen();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void createScreen() throws IOException {
        // Close current screen
        this.close();

        // Create TerminalSize
        TerminalSize terminalSize = new TerminalSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        // Set Terminal Size
        DefaultTerminalFactory dtf = new DefaultTerminalFactory();
        dtf.setInitialTerminalSize(terminalSize);

        // Get user's screen dimensions
        int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        // Calculate terminal window dimensions (80% of screen)
        int windowHeight = (int) (screenHeight * SCREEN_OCCUPANCY);

        // Calculate font size and height
        int fontSize = windowHeight / SCREEN_HEIGHT;

        // Set Font Configuration
        dtf.setTerminalEmulatorFontConfiguration(SwingTerminalFontConfiguration.newInstance(this.BASE_FONT.deriveFont(Font.PLAIN, fontSize)));

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
                case 'R', 'r' -> action = Action.RELOAD;
                case '1' -> action = Action.SELECT_1;
                case '2' -> action = Action.SELECT_2;
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
            case 10, 32, 69 -> action = Action.EXEC;
            default -> action = null;
        }
        if (action != null) this.activeActions.add(action);
    }

    @Override
    public void keyReleased(KeyEvent e) {
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
            case 82 -> STF.addInput(new KeyStroke('r', false, false));
            default -> {
            }
        }

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
            case 10, 32, 69 -> action = Action.EXEC;
            default -> action = null;
        }
        if (action != null) this.activeActions.remove(action);
    }

    // Drawing
    // TODO: TO BE DELETE
    @Override
    public void drawText(String text, Vector2D position, Colors foreGround, Colors backGround) {
        TextGraphics tg = this.screen.newTextGraphics();
        tg.setForegroundColor(LanternaGUI.mapTextColor(foreGround));
        tg.setBackgroundColor(LanternaGUI.mapTextColor(backGround));
        tg.putString(position.x(), position.y(), text);
    }

    @Override
    public void drawMainMenu(List<Menu_Options> options, int selectedOption) {
        if (!this.stable()) return;
        int centerX = this.getWindowSize().x() / 2;
        int centerY = this.getWindowSize().y() / 2;
        Vector2D center = new Vector2D(centerX, centerY);

        // Draw BackGround
        this.drawImage(this.sprite_menu_backgrounds.get(Menus.MAIN_MENU));

        // Draw Title
        this.drawImage(center.add(new Vector2D(0, -70)), this.sprite_menu_titles.get(Menus.MAIN_MENU));

        // Draw Options
        if (selectedOption < 0 || selectedOption >= options.size())
            throw new RuntimeException("Invalid Selected Option");


        BufferedImage sprite;
        Vector2D position;
        if (options.size() > 5) position = center;
        else position = center.add(new Vector2D(0, 40));

        for (Menu_Options option : options) {
            boolean selected = option == options.get(selectedOption);
            if (selected) sprite = this.sprite_menu_options.get(option).first;
            else sprite = this.sprite_menu_options.get(option).second;

            this.drawImage(position, sprite);
            position = position.add(new Vector2D(0, 20));
        }
    }

    @Override
    public void drawArenaBackGround() {
        if (!this.stable()) return;

        TextGraphics tg = this.screen.newTextGraphics();
        tg.setBackgroundColor(new TextColor.RGB(159, 153, 116));
        tg.fillRectangle(new TerminalPosition(0, 0), this.screen.getTerminalSize(), ' ');
    }

    @Override
    public void drawArenaUI(int maxHP, int hp) {
        Vector2D position = new Vector2D(92, 297);

        // Determine how many health parts the player still has, ceiling division
        int HEALTHBAR_PARTS = 7;
        int portion = maxHP / HEALTHBAR_PARTS;
        int current = (hp + portion - 1) / portion;

        // Draw the bottom layer of the health bar
        this.drawImage(position, this.sprite_empty_health_bar);

        // Draw the dynamic part of the health bar
        for (int i = 0; i < current; i++)
            this.drawImage(new Vector2D(position.x() - 64 + i * 18, position.y()), this.sprite_green_health_part);
    }

    @Override
    public void drawWeapon(Weapon weapon, int numberOfBullets) {
        Vector2D weaponPos = new Vector2D(174, 297);
        Vector2D bulletPos = new Vector2D(192, 297);
        BufferedImage ammoImage;

        // Draw gun
        switch (weapon) {
            case PISTOL -> {
                this.drawImage(weaponPos, this.sprite_pistol);
                ammoImage = this.sprite_bullet_vertical;
            }
            case GRENADE_LAUNCHER -> {
                this.drawImage(weaponPos, this.sprite_grenade_launcher);
                ammoImage = this.sprite_explosive;
            }
            case null, default -> throw new RuntimeException("Trying to draw gun that doesn't exist.");
        }

        // Draw ammo
        for (int i = 0; i < numberOfBullets; i++) {
            this.drawImage(bulletPos, ammoImage);
            bulletPos = bulletPos.add(new Vector2D(ammoImage.getWidth() / 2, 0));
        }
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
                    case LEFT_1 -> this.drawImage(position, sprite_tenebris_harbinger_left_1);
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
            case null, default -> {
            }
        }
    }

    @Override
    public void drawStaticElement(Vector2D position, StaticElement staticElement) {
        switch (staticElement) {
            case WALL -> this.drawImage(position, this.sprite_wall);
            case BREAKABLE_WALL -> this.drawImage(position, this.sprite_breakable_wall);
            case SANDBAG -> this.drawImage(position, this.sprite_sandbag);
            case SPIKE -> this.drawImage(position, this.sprite_spikes);
            case null, default -> throw new RuntimeException("Invalid Static Element");
        }
    }

    @Override
    public void drawParticleEffect(Vector2D position, ParticleEffect particleEffect, int frameNumber) {
        switch (particleEffect) {
            case SPELL_EXPLOSION -> {
                if (frameNumber <= 0 || frameNumber > GUI.SPELL_EXPLOSION_FRAME_COUNT)
                    throw new RuntimeException("Drawing Invalid Explosion Frame Number");

                this.drawImage(position, this.sprite_spell_explostion.get(frameNumber - 1));
            }

            case DEATH_BLOOD -> {
                if (frameNumber <= 0 || frameNumber > GUI.DEATH_BLOOD_FRAME_COUNT)
                    throw new RuntimeException("Drawing Invalid Death Blood Frame Number");

                Vector2D spritePosition = position.add(new Vector2D(0, -(this.sprite_death_blood.getFirst().getHeight() / 4)));
                this.drawImage(spritePosition, this.sprite_death_blood.get(frameNumber - 1));
            }

            case DAMAGE_BLOOD -> {
                if (frameNumber <= 0 || frameNumber > GUI.DAMAGE_BLOOD_FRAME_COUNT)
                    throw new RuntimeException("Drawing Invalid Damage Blood Frame Number");

                this.drawImage(position, this.sprite_damage_blood.get(frameNumber - 1));
            }

            case EXPLOSION -> {
                if (frameNumber <= 0 || frameNumber > GUI.EXPLOSION_FRAME_COUNT)
                    throw new RuntimeException("Drawing Invalid Explosion Frame Number");

                this.drawImage(position, this.sprite_explosion.get(frameNumber - 1));
            }

            case BREAKABLE_WALL_DAMAGE -> {
                if (frameNumber <= 0 || frameNumber > GUI.BREAKABLE_WALL_DAMAGE_FRAME_COUNT)
                    throw new RuntimeException("Drawing Invalid Breakable Wall Damage Frame Number");

                this.drawImage(position, this.sprite_breakable_wall_damage.get(frameNumber - 1));
            }

            case null, default -> throw new RuntimeException("Invalid particle effect.");
        }
    }

    @Override
    public void drawProjectile(Vector2D position, Projectile projectile, Vector2D.Direction direction) {
        switch (projectile) {
            case BULLET -> {
                switch (direction) {
                    case RIGHT, DOWN_RIGHT, UP_RIGHT, LEFT, DOWN_LEFT, UP_LEFT ->
                            this.drawImage(position, this.sprite_bullet_horizontal);
                    case UP, DOWN -> this.drawImage(position, this.sprite_bullet_vertical);
                    case null, default -> throw new RuntimeException("Trying to draw bullet with invalid direction");
                }
            }
            case EXPLOSIVE -> this.drawImage(position, this.sprite_explosive);
            case SPELL -> this.drawImage(position, this.sprite_spell);
            case null, default -> throw new RuntimeException("Invalid Projectile");
        }
    }

    private void drawImage(BufferedImage sprite) {
        if (!this.stable()) return;

        TextGraphics tg = this.screen.newTextGraphics();
        for (int x = 0; x < sprite.getWidth(); x++) {
            for (int y = 0; y < sprite.getHeight(); y++) {
                int a = sprite.getRGB(x, y);
                int alpha = (a >> 24) & 0xff;
                int red = (int) (((a >> 16) & 255) * ((double) alpha / 255));
                int green = (int) (((a >> 8) & 255) * ((double) alpha / 255));
                int blue = (int) ((a & 255) * ((double) alpha / 255));

                if (alpha != 0) {
                    tg.setForegroundColor(new TextColor.RGB(red, green, blue));
                    tg.setBackgroundColor(new TextColor.RGB(red, green, blue));
                    tg.setCharacter(new TerminalPosition(x, y), ' ');
                }
            }
        }
    }

    private void drawImage(Vector2D position, BufferedImage sprite) {
        if (!this.stable()) return;

        TextGraphics tg = this.screen.newTextGraphics();
        for (int x = 0; x < sprite.getWidth(); x++) {
            for (int y = 0; y < sprite.getHeight(); y++) {
                int a = sprite.getRGB(x, y);
                int alpha = (a >> 24) & 0xff;
                int red = (int) (((a >> 16) & 255) * ((double) alpha / 255));
                int green = (int) (((a >> 8) & 255) * ((double) alpha / 255));
                int blue = (int) ((a & 255) * ((double) alpha / 255));

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
    public void refresh() throws IOException {
        if (this.stable()) this.screen.refresh();
    }

    @Override
    public void close() throws IOException {
        // Clear Active Actions
        this.activeActions.clear();
        if (this.stable()) this.screen.stopScreen();
    }

    @Override
    public Vector2D getWindowSize() {
        TerminalSize ts = null;
        if (this.stable()) ts = this.screen.getTerminalSize();

        Vector2D size = new Vector2D(0, 0);
        if (ts != null) size = new Vector2D(ts.getColumns(), ts.getRows());

        return size;
    }

    // Utils
    private boolean stable() {
        return this.screen != null;
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
            case GREEN -> mapped = TextColor.ANSI.GREEN;
            case RED -> mapped = TextColor.ANSI.RED;
            case ORANGE -> mapped = TextColor.Factory.fromString("#E26313");
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

    // For Tests

    // This function is only used for tests and should not be used in any other way
    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    // This function is only used for tests and should not be used in any other way
    public Screen getScreen() {
        return this.screen;
    }
}

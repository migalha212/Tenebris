package com.ldts.t14g01.Tenebris.menus;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.ldts.t14g01.Tenebris.State;
import com.ldts.t14g01.Tenebris.screen.ScreenGetter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HowToPlayMenu implements Menu {
    private static final String name = "How to Play";
    private int selectedOption;
    private final List<String> options;

    public HowToPlayMenu() {
        this.selectedOption = 0;
        this.options = new ArrayList<>();
        updateOptions();
    }

    public void updateOptions(){
        options.add("Menu Navigation");
        options.add("Default Controls");
        options.add("Objective");
        options.add("Game Basics");
        options.add("Weapons");
        options.add("Enemies");
        options.add("Bosses");
        options.add("Difficulty Levels");
        options.add("Map Elements");
        options.add("Back");
    }

    @Override
    public String getName() {
        return HowToPlayMenu.name;
    }

    @Override
    public void run(ScreenGetter screenGetter, State state) throws IOException, InterruptedException {
        // Get options
        Screen screen;

        // While the active menu is this
        while (this.equals(state.currentMenu())) {
            // Get most up-to-date screen
            screen = screenGetter.getScreen();

            // Draw menu
            draw(screen);

            // Delay
            Thread.sleep(50);

            // Read keystroke
            KeyStroke keyStroke = screen.pollInput();
            if (keyStroke != null) {
                switch (keyStroke.getKeyType()) {
                    case ArrowUp -> selectedOption = (selectedOption - 1 + options.size()) % options.size();
                    case ArrowDown -> selectedOption = (selectedOption + 1) % options.size();
                    case Enter -> this.executeOption(state);
                    case Escape -> this.returnToMainMenu(state);
                    case EOF -> this.handleEOFCharacter(screenGetter, state);
                }
                if (keyStroke.getCharacter() != null) {
                    switch (keyStroke.getCharacter()) {
                        case 'Q', 'q', 'E', 'e' -> this.returnToMainMenu(state);
                    }
                }
            }
        }
    }

    private void executeOption(State state) {
        String Option = options.get(this.selectedOption);
        //As the only selectable option is the Back Button
        if (Objects.equals(Option, "Back")){
            state.setNextMenu(new MainMenu());
        }
    }

    private void draw(Screen screen) throws IOException {
        //Clear Screen
        screen.clear();

        // Get TextGraphics
        TextGraphics textGraphics = screen.newTextGraphics();

        // Get center position
        int centerX = screen.getTerminalSize().getColumns() / 2;
        int centerY = screen.getTerminalSize().getRows() / 2;

        //X axis Offset
        int offsetX = 4;

        // Draw Title;
        List<String> titleLines = new ArrayList<>();
        titleLines.add(HowToPlayMenu.name);
        titleLines.add("───────────────");

        for (int i = 0; i < titleLines.size(); i++) {
            textGraphics
                    .setForegroundColor(TextColor.Factory.fromString("#DAA520"))
                    .putString(
                            offsetX,
                            centerY - 8 + i,
                            titleLines.get(i)
                    );
        }

        //Draw Options
        for (int i = 0; i < options.size(); i++) {
            TextColor color = TextColor.ANSI.WHITE;

            // Highlight selected Option
            if (i == this.selectedOption) color = TextColor.ANSI.YELLOW;

            // Draw Option
            if (i != options.size() - 1){
                textGraphics
                        .setForegroundColor(color)
                        .putString(offsetX, 6 + i, options.get(i));

            //Draw Back Button (special case)
            } else {
                textGraphics
                        .setForegroundColor(color)
                        .putString(
                                centerX - options.get(i).length() / 2,
                                screen.getTerminalSize().getRows() - 2,
                                options.get(i)
                        );
            }
        }

        switch (selectedOption) {
            case 0 -> showMenuNavigationControls(textGraphics);
            case 1 -> showDefaultControls(textGraphics);
            case 2 -> showObjective(textGraphics);
            case 3 -> showGameBasics(textGraphics);
            case 4 -> showWeaponsStats(textGraphics);
            case 5 -> showMonsterInfo(textGraphics);
//            case 6 ->
            case 7 -> showDifficultyLevelsExplanation(textGraphics);
//            case 8 ->
        }

        // Update Screen
        screen.refresh();
    }

    private void showMenuNavigationControls(TextGraphics textGraphics){
        List<String> navigationOptions = new ArrayList<>();
        navigationOptions.add("↑ - Move Up");
        navigationOptions.add("↓ - Move Down");
        navigationOptions.add("Enter - Select Option");
        navigationOptions.add("Q / ESC - Go to Previous Menu");

        for (int i = 0; i < navigationOptions.size(); i++) {
            TextColor color = TextColor.ANSI.WHITE;

            // Draw Navigation Controls Explanation
            textGraphics
                    .setForegroundColor(color)
                    .putString(23, 6 + i, navigationOptions.get(i));
        }
    }

    private void showDefaultControls(TextGraphics textGraphics){
        List<String> defaultMovementControls = new ArrayList<>();
        defaultMovementControls.add("Move Character:");
        defaultMovementControls.add("   W - Up");
        defaultMovementControls.add("   A - Left");
        defaultMovementControls.add("   S - Down");
        defaultMovementControls.add("   D - Right");

        for (int i = 0; i < defaultMovementControls.size(); i++) {
            TextColor color = TextColor.ANSI.WHITE;

            // Draw Movement Controls Explanation
            textGraphics
                    .setForegroundColor(color)
                    .putString(25, 6 + i, defaultMovementControls.get(i));
        }

        List<String> defaultAimControls = new ArrayList<>();
        defaultAimControls.add("Aim Controls:");
        defaultAimControls.add("    ↑ - Up");
        defaultAimControls.add("    ← - Left");
        defaultAimControls.add("    ↓ - Down");
        defaultAimControls.add("    → - Right");
        defaultAimControls.add("    Space - Shoot");

        for (int i = 0; i < defaultAimControls.size(); i++) {
            TextColor color = TextColor.ANSI.WHITE;

            // Draw Aim Controls Explanation
            textGraphics
                    .setForegroundColor(color)
                    .putString(43, 6 + i, defaultAimControls.get(i));
        }
    }

    private void showObjective(TextGraphics textGraphics){
        List<String> lines = new ArrayList<>();
        lines.add("Play as Dylan Macron, ");
        lines.add("a character fighting for survival," );
        lines.add("as he battles monsters ");
        lines.add("across different arenas.");
        lines.add("Collect weapons, ");
        lines.add("manage resources, ");
        lines.add("and adapt to different enemies and ");
        lines.add("bosses to progress.");

        int space = 0;

        for (int i = 0; i < lines.size(); i++) {
            TextColor color = TextColor.ANSI.WHITE;

            if (i == 4){
                space = 1;
            }

            // Draw Game Objective Explanation
            textGraphics
                    .setForegroundColor(color)
                    .putString(23, 6 + i + space, lines.get(i));
        }
    }

    private void showGameBasics(TextGraphics textGraphics){
        List<String> gameBasics = new ArrayList<>();
        gameBasics.add("Health Points (HP)");
        gameBasics.add("Represents the damage that an Entity");
        gameBasics.add("can take before dying.");
        gameBasics.add("Energy (EN)");
        gameBasics.add("Required to fire weapons;");
        gameBasics.add("Regenerates over time.");
        gameBasics.add("Damage (DMG)");
        gameBasics.add("Indicates how much HP is lost when an");
        gameBasics.add("Entity or obstacle inflicts damage.");

        int space = 0;

        for (int i = 0; i < gameBasics.size(); i++) {
            TextColor color = TextColor.ANSI.WHITE;

            if (i == 0 || i == 3 || i == 6){
                color = TextColor.ANSI.CYAN;
                if (i != 0){
                    space++;
                }
            }

            // Draw Game Basics Explanation
            textGraphics
                    .setForegroundColor(color)
                    .putString(23, 5 + i + space, gameBasics.get(i));
        }
    }

    private void showWeaponsStats(TextGraphics textGraphics){
        List<String> weaponsStats = new ArrayList<>();
        weaponsStats.add("Simple Shot");
        weaponsStats.add("  • Fire Rate     : 2 bullets/s");
        weaponsStats.add("  • Energy Cost   : 10 EN/bullet");
        weaponsStats.add("  • Damage : 5 HP");
        weaponsStats.add("Explosion Shot");
        weaponsStats.add("  • Fire Rate     : 4 shots/s");
        weaponsStats.add("  • Energy Cost   : 25 EN/bullet");
        weaponsStats.add("  • Damage : (15 - 3,75 per radius) HP");
        weaponsStats.add("  • Max Radius: 4 tiles");
        weaponsStats.add("Death Ray (Laser)");
        weaponsStats.add("  • Cooldown      : 1 minute");
        weaponsStats.add("  • Energy Cost   : 20 EN/s");
        weaponsStats.add("  • Damage : 20 HP/s");
        weaponsStats.add("  • Max Duration: 5 seconds");

        int space = 0;

        for (int i = 0; i < weaponsStats.size(); i++) {
            TextColor color = TextColor.ANSI.WHITE;

            if (i == 0 || i == 4 || i == 9){
                color = TextColor.ANSI.CYAN;
                if (i != 0){
                    space++;
                }
            }

            // Draw Weapon Stats
            textGraphics
                    .setForegroundColor(color)
                    .putString(23, 1 + i + space, weaponsStats.get(i));
        }
    }

    private void showMonsterInfo(TextGraphics textGraphics){
        List<String> monsterInfo = new ArrayList<>();
        monsterInfo.add("Tenebris Peon");
        monsterInfo.add("Melee range, low HP, moderate damage");
        monsterInfo.add("Tenebris Heavy");
        monsterInfo.add("High HP, low damage");
        monsterInfo.add("Tenebris Spiked Scout");
        monsterInfo.add("Low HP, high collision damage");
        monsterInfo.add("Tenebris Harbinger");
        monsterInfo.add("High damage, Range attacker, low HP");
        monsterInfo.add("Tenebris Warden");
        monsterInfo.add("Mini-Boss: High HP, high fire rate");

        int space = 0;

        for (int i = 0; i < monsterInfo.size(); i++) {
            TextColor color = TextColor.ANSI.WHITE;

            if (i % 2 == 0){
                color = TextColor.ANSI.CYAN;
                if (i != 0){
                    space++;
                }
            }

            // Draw Weapon Stats
            textGraphics
                    .setForegroundColor(color)
                    .putString(23, 2 + i + space, monsterInfo.get(i));
        }
    }

    private void showDifficultyLevelsExplanation(TextGraphics textGraphics){
        List<String> difficultyLevelExplain = new ArrayList<>();
        difficultyLevelExplain.add("Easy");
        difficultyLevelExplain.add("• Higher HP that resets in each arena");
        difficultyLevelExplain.add("• Normal EN regeneration, frequent drops");
        difficultyLevelExplain.add("Normal");
        difficultyLevelExplain.add("• Standard HP that resets in each arena");
        difficultyLevelExplain.add("• Slower EN regeneration, frequent drops");
        difficultyLevelExplain.add("Champion");
        difficultyLevelExplain.add("• Standard HP that resets in each level");
        difficultyLevelExplain.add("• Only larger monsters drop EN");
        difficultyLevelExplain.add("Heartless");
        difficultyLevelExplain.add("• 'Champion' rules");
        difficultyLevelExplain.add("• No respawn!");

        int space = 0;

        for (int i = 0; i < difficultyLevelExplain.size(); i++) {
            TextColor color = TextColor.ANSI.WHITE;

            if (i % 3 == 0){
                color = TextColor.ANSI.CYAN;
                if (i != 0){
                    space++;
                }
            }

            // Draw Weapon Stats
            textGraphics
                    .setForegroundColor(color)
                    .putString(23, 1 + i + space, difficultyLevelExplain.get(i));
        }

    }

    // When pressing Escape or Q or "Back Button" the game will return to the Main Menu
    private void returnToMainMenu(State state) {
        Menu nextMenu = new MainMenu();
        state.setNextMenu(nextMenu);
    }

    private void quit(State state) {
        state.quit();
    }

    private void handleEOFCharacter(ScreenGetter screenGetter, State state) throws IOException, InterruptedException {
        // Wait to give possible screen reload time
        Thread.sleep(250);

        Screen screen = screenGetter.getScreen();
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke != null)
            if (keyStroke.getKeyType() == KeyType.EOF) this.quit(state);
    }

}

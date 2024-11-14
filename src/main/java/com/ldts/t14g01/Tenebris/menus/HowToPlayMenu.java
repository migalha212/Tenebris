package com.ldts.t14g01.Tenebris.menus;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.state.State;
import com.ldts.t14g01.Tenebris.state.States;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HowToPlayMenu implements Menu {
    private final String[] options;
    private int selectedOption;

    public HowToPlayMenu() {
        this.selectedOption = 0;
        this.options = new String[]{
                "Menu Navigation",
                "Default Controls",
                "Objective",
                "Game Basics",
                "Weapons",
                "Enemies",
                "Bosses",
                "Difficulty Levels",
                "Map Elements",
                "Back"
        };
    }

    @Override
    public void tick(State state, Action action) {
        switch (action) {
            case LOOK_UP -> selectedOption = (selectedOption - 1 + options.length) % options.length;
            case LOOK_DOWN -> selectedOption = (selectedOption + 1) % options.length;
            case EXEC -> this.executeOption(state);
            case ESC, QUIT -> this.returnToMainMenu(state);
            case null, default -> {
            }
        }

    }

    @Override
    public void draw(GUI gui) throws IOException {
        // Clear Screen
        gui.clear();

        // Get TextGraphics
        TextGraphics textGraphics = gui.getTextGraphics();

        // Get center position
        int centerX = gui.getTerminalSize().getColumns() / 2;
        int centerY = gui.getTerminalSize().getRows() / 2;

        //X axis Offset
        int offsetX = 4;

        // Draw Title;
        List<String> titleLines = new ArrayList<>();
        titleLines.add("How to Play");
        titleLines.add("───────────────");

        for (int i = 0; i < titleLines.size(); i++)
            textGraphics
                    .setForegroundColor(TextColor.Factory.fromString("#DAA520"))
                    .putString(
                            offsetX,
                            centerY - 8 + i,
                            titleLines.get(i)
                    );


        // Draw Options
        for (int i = 0; i < options.length; i++) {
            TextColor color = TextColor.ANSI.WHITE;

            // Highlight selected Option
            if (i == this.selectedOption) color = TextColor.ANSI.YELLOW;

            // Draw Back Button (special case)
            if (i == options.length - 1) {
                textGraphics
                        .setForegroundColor(color)
                        .putString(
                                centerX - options[i].length() / 2,
                                gui.getTerminalSize().getRows() - 2,
                                options[i]
                        );
            }
            // Draw other Option
            else {
                textGraphics
                        .setForegroundColor(color)
                        .putString(offsetX, 6 + i, options[i]);
            }
        }

        switch (selectedOption) {
            case 0 -> showMenuNavigationControls(textGraphics);
            case 1 -> showDefaultControls(textGraphics);
            case 2 -> showObjective(textGraphics);
            case 3 -> showGameBasics(textGraphics);
            case 4 -> showWeaponsStats(textGraphics);
            case 5 -> showMonsterInfo(textGraphics);
            case 6 -> showBossesInfo(textGraphics);
            case 7 -> showDifficultyLevelsExplanation(textGraphics);
            case 8 -> showMapElements(textGraphics);
        }

        // Update Screen
        gui.refresh();
    }

    private void showMenuNavigationControls(TextGraphics textGraphics) {
        List<String> navigationOptions = new ArrayList<>();
        navigationOptions.add(" - Move Up");
        navigationOptions.add(" - Move Down");
        navigationOptions.add(" - Select Option");
        navigationOptions.add(" - Go to Previous Menu");

        List<String> navigationKeys = new ArrayList<>();
        navigationKeys.add("      ↑");
        navigationKeys.add("      ↓");
        navigationKeys.add("  Enter");
        navigationKeys.add("Q / ESC");

        for (int i = 0; i < navigationOptions.size(); i++) {
            // Draw Navigation Controls Explanation
            textGraphics
                    .setForegroundColor(TextColor.ANSI.CYAN)
                    .putString(23, 6 + i, navigationKeys.get(i));
            textGraphics
                    .setForegroundColor(TextColor.ANSI.WHITE)
                    .putString(23 + navigationKeys.get(i).length(), 6 + i, navigationOptions.get(i));
        }
    }

    private void showDefaultControls(TextGraphics textGraphics) {
        textGraphics
                .setForegroundColor(TextColor.ANSI.WHITE)
                .putString(25, 6, "Move Character:");

        List<String> defaultMovementDescription = new ArrayList<>();
        defaultMovementDescription.add(" - Up");
        defaultMovementDescription.add(" - Left");
        defaultMovementDescription.add(" - Down");
        defaultMovementDescription.add(" - Right");

        List<String> defaultMovementKeys = new ArrayList<>();
        defaultMovementKeys.add("   W");
        defaultMovementKeys.add("   A");
        defaultMovementKeys.add("   S");
        defaultMovementKeys.add("   D");

        for (int i = 0; i < defaultMovementDescription.size(); i++) {
            // Draw Movement Controls Explanation
            textGraphics
                    .setForegroundColor(TextColor.ANSI.WHITE)
                    .putString(29, 7 + i, defaultMovementDescription.get(i));

            textGraphics
                    .setForegroundColor(TextColor.ANSI.CYAN)
                    .putString(25, 7 + i, defaultMovementKeys.get(i));
        }

        textGraphics
                .setForegroundColor(TextColor.ANSI.WHITE)
                .putString(43, 6, "Aim Controls:");

        List<String> defaultAimDescription = new ArrayList<>();
        defaultAimDescription.add(" - Up");
        defaultAimDescription.add(" - Left");
        defaultAimDescription.add(" - Down");
        defaultAimDescription.add(" - Right");
        defaultAimDescription.add(" - Shoot");

        List<String> defaultAimKeys = new ArrayList<>();
        defaultAimKeys.add("    ↑");
        defaultAimKeys.add("    ←");
        defaultAimKeys.add("    ↓");
        defaultAimKeys.add("    →");
        defaultAimKeys.add("Space");

        for (int i = 0; i < defaultAimDescription.size(); i++) {
            // Draw Aim Controls Explanation
            textGraphics
                    .setForegroundColor(TextColor.ANSI.CYAN)
                    .putString(43, 7 + i, defaultAimKeys.get(i));

            textGraphics
                    .setForegroundColor(TextColor.ANSI.WHITE)
                    .putString(48, 7 + i, defaultAimDescription.get(i));
        }
    }

    private void showObjective(TextGraphics textGraphics) {
        List<String> lines = new ArrayList<>();
        lines.add("Play as Dylan Macron, ");
        lines.add("a character fighting for survival,");
        lines.add("as he battles monsters ");
        lines.add("across different arenas.");
        lines.add("Collect weapons, ");
        lines.add("manage resources, ");
        lines.add("and adapt to different enemies and ");
        lines.add("bosses to progress.");

        int space = 0;

        for (int i = 0; i < lines.size(); i++) {
            TextColor color = TextColor.ANSI.WHITE;

            if (i == 4) {
                space = 1;
            }

            // Draw Game Objective Explanation
            textGraphics
                    .setForegroundColor(color)
                    .putString(23, 6 + i + space, lines.get(i));
        }
    }

    private void showGameBasics(TextGraphics textGraphics) {
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

            if (i == 0 || i == 3 || i == 6) {
                color = TextColor.ANSI.CYAN;
                if (i != 0) {
                    space++;
                }
            }

            // Draw Game Basics Explanation
            textGraphics
                    .setForegroundColor(color)
                    .putString(23, 5 + i + space, gameBasics.get(i));
        }
    }

    private void showWeaponsStats(TextGraphics textGraphics) {
        List<String> weaponsStats = new ArrayList<>();
        weaponsStats.add("Simple Shot");
        weaponsStats.add("  • Fire Rate     : 2 bullets/s");
        weaponsStats.add("  • Energy Cost   : 10 EN/bullet");
        weaponsStats.add("  • Damage        : 5 HP");
        weaponsStats.add("Explosion Shot");
        weaponsStats.add("  • Fire Rate     : 4 shots/s");
        weaponsStats.add("  • Energy Cost   : 25 EN/bullet");
        weaponsStats.add("  • Damage        : 15 - 3,75 HP");
        weaponsStats.add("  • Max Radius    : 4 tiles");
        weaponsStats.add("Death Ray (Laser)");
        weaponsStats.add("  • Cooldown      : 1 minute");
        weaponsStats.add("  • Energy Cost   : 20 EN/s");
        weaponsStats.add("  • Damage        : 20 HP/s");
        weaponsStats.add("  • Max Duration  : 5 seconds");

        int space = 0;

        for (int i = 0; i < weaponsStats.size(); i++) {
            TextColor color = TextColor.ANSI.WHITE;

            if (i == 0 || i == 4 || i == 9) {
                color = TextColor.ANSI.CYAN;
                if (i != 0) {
                    space++;
                }
            }

            // Draw Weapon Stats
            textGraphics
                    .setForegroundColor(color)
                    .putString(23, 1 + i + space, weaponsStats.get(i));
        }
    }

    private void showMonsterInfo(TextGraphics textGraphics) {
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

            if (i % 2 == 0) {
                color = TextColor.ANSI.CYAN;
                if (i != 0) {
                    space++;
                }
            }

            // Draw Monster Info
            textGraphics
                    .setForegroundColor(color)
                    .putString(23, 2 + i + space, monsterInfo.get(i));
        }
    }

    private void showBossesInfo(TextGraphics textGraphics) {
        List<String> bossesIntro = new ArrayList<>();
        bossesIntro.add("Bosses have unique arenas & attack phases");
        bossesIntro.add("Follow attack cues and destroy their");
        bossesIntro.add("weak points to defeat them!");
        for (int i = 0; i < bossesIntro.size(); i++) {
            TextColor color = TextColor.ANSI.GREEN_BRIGHT;

            // Draw Intro
            textGraphics
                    .setForegroundColor(color)
                    .putString(23, 1 + i, bossesIntro.get(i));
        }

        List<String> bossesInfo = new ArrayList<>();
        bossesInfo.add("Black Sum Emissary");
        bossesInfo.add("Avoid marked spots to dodge attacks");
        bossesInfo.add("Shoot upwards to damage");
        bossesInfo.add("Abyssal Archon");
        bossesInfo.add("Destroy 2 Abyssal Bulbs to reach Phase 2");
        bossesInfo.add("Then attack! But maneuver carefully.");
        bossesInfo.add("Nightmare Sovereign");
        bossesInfo.add("Moves through different arenas,");
        bossesInfo.add("Each with unique challenges!");
        bossesInfo.add("Survive or defeat him. Choose wisely!");

        int space = 0;

        for (int i = 0; i < bossesInfo.size(); i++) {
            TextColor color = TextColor.ANSI.WHITE;

            if (i % 3 == 0 && i < bossesInfo.size() - 1) {
                color = TextColor.ANSI.CYAN;
                if (i != 0) {
                    space++;
                }
            }

            // Draw Bosses Info
            textGraphics
                    .setForegroundColor(color)
                    .putString(23, 5 + i + space, bossesInfo.get(i));
        }

    }

    private void showDifficultyLevelsExplanation(TextGraphics textGraphics) {
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

            if (i % 3 == 0) {
                color = TextColor.ANSI.CYAN;
                if (i != 0) {
                    space++;
                }
            }

            // Draw Difficulty Levels Information
            textGraphics
                    .setForegroundColor(color)
                    .putString(23, 1 + i + space, difficultyLevelExplain.get(i));
        }
    }

    private void showMapElements(TextGraphics textGraphics) {
        List<String> mapElementsInfo = new ArrayList<>();
        mapElementsInfo.add("Standard Wall");
        mapElementsInfo.add("Blocks movement and bullets");
        mapElementsInfo.add("Destructible Wall/Crates");
        mapElementsInfo.add("Can be destroyed with bullets");
        mapElementsInfo.add("Spike");
        mapElementsInfo.add("Deals damage on contact");
        mapElementsInfo.add("Sand bag");
        mapElementsInfo.add("Blocks movement but not bullets");

        int space = 0;

        for (int i = 0; i < mapElementsInfo.size(); i++) {
            TextColor color = TextColor.ANSI.WHITE;

            if (i % 2 == 0) {
                color = TextColor.ANSI.CYAN;
                if (i != 0) {
                    space++;
                }
            }

            // Draw Map Elements Info
            textGraphics
                    .setForegroundColor(color)
                    .putString(23, 4 + i + space, mapElementsInfo.get(i));
        }
    }

    // When pressing Escape or Q or "Back Button" the game will return to the Main Menu
    private void returnToMainMenu(State state) {
        state.setState(States.MAIN_MENU);
    }

    private void executeOption(State state) {
        // As the only selectable option is the Back Button
        if ("Back".equals(this.options[this.selectedOption]))
            state.setState(States.MAIN_MENU);
    }
}

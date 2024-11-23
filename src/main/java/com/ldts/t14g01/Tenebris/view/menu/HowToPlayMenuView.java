package com.ldts.t14g01.Tenebris.view.menu;

import com.googlecode.lanterna.TextColor;
import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.HowToPlayMenu;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.utils.Position;
import com.ldts.t14g01.Tenebris.view.View;

import java.util.ArrayList;
import java.util.List;

public class HowToPlayMenuView extends View<Menu> {
    public HowToPlayMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) {
        // Get center position
        int centerX = gui.getWindowSize().x() / 2;
        int centerY = gui.getWindowSize().y() / 2;

        // X axis Offset
        int offsetX = 4;

        // Draw Title;
        List<String> titleLines = new ArrayList<>();
        titleLines.add("How to Play");
        titleLines.add("───────────────");

        for (int i = 0; i < titleLines.size(); i++)
            gui.drawText(
                    titleLines.get(i),
                    new Position(offsetX, centerY - 8 + i),
                    GUI.Colors.BRIGHT_YELLOW,
                    GUI.Colors.BLACK
            );

        // Draw Options
        for (int i = 0; i < this.getModel().getOptions().size(); i++) {
            GUI.Colors foreGroundColor = GUI.Colors.WHITE;

            // Highlight selected Option
            if (i == this.getModel().getSelectedOption()) foreGroundColor = GUI.Colors.YELLOW;

            // Draw Back Button (special case)
            if (i == this.getModel().getOptions().size() - 1) {
                gui.drawText(
                        this.getModel().getOptions().get(i).replace('_', ' '),
                        new Position(
                                centerX - this.getModel().getOptions().get(i).length() / 2,
                                gui.getWindowSize().y() - 2
                        ),
                        foreGroundColor,
                        GUI.Colors.BLACK
                );

            }
            // Draw other Option
            else
                gui.drawText(
                        this.getModel().getOptions().get(i).replace('_', ' '),
                        new Position(offsetX, 6 + i),
                        foreGroundColor,
                        GUI.Colors.BLACK
                );

            // Draw Information of the selected option
            switch (HowToPlayMenu.HowToPlayMenuOptions.valueOf(this.getModel().getOptions().get(this.getModel().getSelectedOption()))) {
                case Menu_Navigation -> drawMenuNavigationControls(gui);
                case Default_Controls -> drawDefaultControls(gui);
                case Objective -> drawObjective(gui);
                case Game_Basics -> drawGameBasics(gui);
                case Weapons -> drawWeapons(gui);
//                case Enemies -> drawEnemies(gui);
//                case Bosses -> drawBosses(gui);
//                case Difficulty_Levels -> drawDifficultyLevels(gui);
//                case Map_Elements -> drawMapElements(gui);
            }
        }
    }

    private void drawMenuNavigationControls(GUI gui) {
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
            gui.drawText(
                    navigationKeys.get(i),
                    new Position(23, 6 + i),
                    GUI.Colors.CYAN,
                    GUI.Colors.BLACK
            );
            gui.drawText(
                    navigationOptions.get(i),
                    new Position(
                            23 + navigationKeys.get(i).length(),
                            6 + i
                    ),
                    GUI.Colors.WHITE,
                    GUI.Colors.BLACK
            );
        }
    }

    private void drawDefaultControls(GUI gui) {
        gui.drawText(
                "Move Character:",
                new Position(25, 6),
                GUI.Colors.WHITE,
                GUI.Colors.BLACK
        );

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
            gui.drawText(
                    defaultMovementDescription.get(i),
                    new Position(29, 7 + i),
                    GUI.Colors.WHITE,
                    GUI.Colors.BLACK
            );

            gui.drawText(
                    defaultMovementKeys.get(i),
                    new Position(25, 7 + i),
                    GUI.Colors.CYAN,
                    GUI.Colors.BLACK
            );
        }

        gui.drawText(
                "Aim Controls:",
                new Position(43, 6),
                GUI.Colors.WHITE,
                GUI.Colors.BLACK
        );

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
            gui.drawText(
                    defaultAimKeys.get(i),
                    new Position(43, 7 + i),
                    GUI.Colors.CYAN,
                    GUI.Colors.BLACK
            );

            gui.drawText(
                    defaultAimDescription.get(i),
                    new Position(48, 7 + i),
                    GUI.Colors.WHITE,
                    GUI.Colors.BLACK
            );
        }
    }

    private void drawObjective(GUI gui) {
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
            if (i == 4) {
                space = 1;
            }

            // Draw Game Objective Explanation
            gui.drawText(
                    lines.get(i),
                    new Position(
                            23,
                            6 + i + space
                    ),
                    GUI.Colors.WHITE,
                    GUI.Colors.BLACK
            );
        }
    }

    private void drawGameBasics(GUI gui) {
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
            GUI.Colors color = GUI.Colors.WHITE;

            if (i == 0 || i == 3 || i == 6) {
                color = GUI.Colors.CYAN;
                if (i != 0) {
                    space++;
                }
            }

            // Draw Game Basics Explanation
            gui.drawText(
                    gameBasics.get(i),
                    new Position(23, 5 + i + space),
                    color,
                    GUI.Colors.BLACK
            );
        }
    }

    private void drawWeapons(GUI gui) {
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
            GUI.Colors color = GUI.Colors.WHITE;

            if (i == 0 || i == 4 || i == 9) {
                color = GUI.Colors.CYAN;
                if (i != 0) {
                    space++;
                }
            }

            // Draw Weapon Stats
            gui.drawText(
                    weaponsStats.get(i),
                    new Position(23, 1 + i + space),
                    color,
                    GUI.Colors.BLACK
            );
        }
    }



}

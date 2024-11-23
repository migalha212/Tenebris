package com.ldts.t14g01.Tenebris.view.menu;

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
                //case Objective -> drawObjective(gui);
                //case Game_Basics -> drawGameBasics(gui);
                //case Weapons -> drawWeapons();
                //case Enemies -> drawEnemies(gui);
                //case Bosses -> drawBosses(gui);
                //case Difficulty_Levels -> drawDifficultyLevels(gui);
                //case Map_Elements -> drawMapElements(gui);
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

}

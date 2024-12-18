package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.savedata.SaveDataManager;
import com.ldts.t14g01.Tenebris.utils.Difficulty;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelsMenuView extends View<Menu> {
    public LevelsMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements() throws IOException {
        GUI gui = GUI.getGUI();

        // Get center position
        int centerX = gui.getWindowSize().x() / 2;
        int centerY = gui.getWindowSize().y() / 2;

        // X axis Offset
        int offsetX = 4;

        // Draw title
        List<String> titleLines = new ArrayList<>();
        titleLines.add("Load Level");
        titleLines.add("───────────────");

        for (int i = 0; i < titleLines.size(); i++)
            gui.drawText(
                    titleLines.get(i),
                    new Vector2D(offsetX, centerY - 8 + i),
                    GUI.Colors.BRIGHT_YELLOW,
                    GUI.Colors.BLACK
            );

        // Fail Safe
        if (this.getModel().getOptions() == null) {
            String message = "You have no saves to reference, how are you here?";
            gui.drawText(
                    message,
                    new Vector2D(centerX - message.length() / 2, centerY),
                    GUI.Colors.WHITE,
                    GUI.Colors.BLACK
            );
            return;
        }

        // Draw menu UI
        gui.drawText("Select a Level to play:", new Vector2D(offsetX, centerY - 4), GUI.Colors.WHITE, GUI.Colors.BLACK);
        String difficultyref = "Save Difficulty: ";
        String difficulty = SaveDataManager.getInstance().getLastOpen().getDifficulty().name();
        gui.drawText(
                difficultyref,
                new Vector2D(gui.getWindowSize().x() - 4 - difficultyref.length() - difficulty.length(), centerY - 7),
                GUI.Colors.WHITE,
                GUI.Colors.BLACK
        );
        GUI.Colors foreGroundColor = null;
        switch (Difficulty.valueOf(difficulty)) {
            case Easy -> foreGroundColor = GUI.Colors.BRIGHT_GREEN;
            case Normal -> foreGroundColor = GUI.Colors.BRIGHT_YELLOW;
            case Champion -> foreGroundColor = GUI.Colors.ORANGE;
            case Heartless -> foreGroundColor = GUI.Colors.RED;
        }
        gui.drawText(
                difficulty,
                new Vector2D(gui.getWindowSize().x() - 4 - difficulty.length(), centerY - 7),
                foreGroundColor,
                GUI.Colors.BLACK
        );

        // Draw the levels list
        for (int i = 1; i <= this.getModel().getOptions().size(); i++) {
            foreGroundColor = GUI.Colors.WHITE;
            if (i - 1 == this.getModel().getSelectedOption()) foreGroundColor = GUI.Colors.YELLOW;
            gui.drawText(
                    "Level " + i,
                    new Vector2D(offsetX, centerY + i - 3),
                    foreGroundColor,
                    GUI.Colors.BLACK
            );
        }
    }
}

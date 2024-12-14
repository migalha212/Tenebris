package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.utils.Difficulty;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadGameMenuView extends View<Menu> {
    public LoadGameMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) throws IOException {

        //used for drawing options and getting other info
        List<SaveData> saves = SaveData.getSaves();

        int centerX = gui.getWindowSize().x() / 2;
        int centerY = gui.getWindowSize().y() / 2;

        // X axis Offset
        int offsetX = 4;

        // Draw title
        List<String> titleLines = new ArrayList<>();
        titleLines.add("Load Game");
        titleLines.add("───────────────");

        for (int i = 0; i < titleLines.size(); i++)
            gui.drawText(titleLines.get(i), new Vector2D(offsetX, centerY - 8 + i), GUI.Colors.BRIGHT_YELLOW, GUI.Colors.BLACK);

        // Show number of available saves
        int available = saves.size();
        gui.drawText("Available Saves: " + available, new Vector2D(centerX + 8, centerY - 7), GUI.Colors.WHITE, GUI.Colors.BLACK);

        //show which save is selected, and it's respective info
        String difficulty = saves.get(this.getModel().getSelectedOption()).getDifficulty().name();
        int level = saves.get(this.getModel().getSelectedOption()).getLevel();
        String text = "Selected Save: ";
        gui.drawText(
                text,
                new Vector2D(offsetX, centerY - 4),
                GUI.Colors.WHITE,
                GUI.Colors.BLACK
        );
        gui.drawText(
                Integer.toString(this.getModel().getSelectedOption() + 1),
                new Vector2D(4 + text.length(), centerY - 4),
                GUI.Colors.YELLOW,
                GUI.Colors.BLACK
        );

        // Draw the info for the save
        List<String> saveInfo = new ArrayList<>();
        saveInfo.add("Dylan has ventured to level ");
        saveInfo.add(String.valueOf(level));
        saveInfo.add(" on ");
        saveInfo.add(difficulty);
        saveInfo.add(" difficulty");
        String atual = "";

        for (int i = 0; i < saveInfo.size(); i++) {
            GUI.Colors foreGroundColor = GUI.Colors.WHITE;
            if (i == 1) {
                foreGroundColor = GUI.Colors.YELLOW;
            } else if (i == 3) {
                switch (Difficulty.valueOf(difficulty)) {
                    case Easy -> foreGroundColor = GUI.Colors.BRIGHT_GREEN;
                    case Normal -> foreGroundColor = GUI.Colors.BRIGHT_YELLOW;
                    case Champion -> foreGroundColor = GUI.Colors.ORANGE;
                    case Heartless -> foreGroundColor = GUI.Colors.RED;
                };
            }
            gui.drawText(saveInfo.get(i), new Vector2D(centerX - 27 + atual.length(), centerY - 1), foreGroundColor, GUI.Colors.BLACK);

            atual += saveInfo.get(i);
        }

        // Draw commands
        gui.drawText("Enter ", new Vector2D(centerX + 4, centerY + 4), GUI.Colors.CYAN, GUI.Colors.BLACK);
        gui.drawText("to Select", new Vector2D(centerX + 10, centerY + 4), GUI.Colors.WHITE, GUI.Colors.BLACK);

        gui.drawText("D ", new Vector2D(centerX + 4, centerY + 6), GUI.Colors.CYAN, GUI.Colors.BLACK);
        gui.drawText("to Delete Save", new Vector2D(centerX + 6, centerY + 6), GUI.Colors.WHITE, GUI.Colors.BLACK);

        gui.drawText("↑ ", new Vector2D(centerX - 17, centerY + 4), GUI.Colors.CYAN, GUI.Colors.BLACK);
        gui.drawText("- Next Save", new Vector2D(centerX - 15, centerY + 4), GUI.Colors.WHITE, GUI.Colors.BLACK);

        gui.drawText("↓ ", new Vector2D(centerX - 21, centerY + 6), GUI.Colors.CYAN, GUI.Colors.BLACK);
        gui.drawText("- Previous Save", new Vector2D(centerX - 19, centerY + 6), GUI.Colors.WHITE, GUI.Colors.BLACK);

    }
}

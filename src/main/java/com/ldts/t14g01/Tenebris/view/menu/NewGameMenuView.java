package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.utils.Difficulty;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;

public class NewGameMenuView extends View<Menu> {
    public NewGameMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements() throws IOException {
        GUI gui = GUI.getGUI();

        // Place Menu Tittle
        gui.drawText("Select Difficulty", new Vector2D(4, 6), GUI.Colors.WHITE, GUI.Colors.BLACK);

        // Draw Options
        for (String name : this.getModel().getOptions()) {
            Difficulty difficulty = Difficulty.valueOf(name);
            GUI.Colors foreGroundColor;

            // Highlight selected Option
            if (difficulty.name().equals(this.getModel().getOptions().get(this.getModel().getSelectedOption())))
                foreGroundColor = GUI.Colors.YELLOW;
            else foreGroundColor = GUI.Colors.WHITE;

            // Draw Option
            gui.drawText(difficulty.name(),
                    new Vector2D(4, 10 + difficulty.ordinal()),
                    foreGroundColor,
                    GUI.Colors.BLACK
            );
        }

        String description;
        switch (Difficulty.valueOf(this.getModel().getOptions().get(this.getModel().getSelectedOption()))) {
            case Easy -> description = "Meant for beginners"; //to explore\nand learn the ways of Tenebris";
            case Normal -> description = "Increased combat difficulty";
            case Champion -> description = "Unforgiving challenge awaits";
            case Heartless -> description = "Good Luck.";
            default -> throw new RuntimeException("Invalid Difficulty");
        }

        // Draw option description
        gui.drawText(
                description,
                new Vector2D(25, 10 + this.getModel().getSelectedOption()),
                GUI.Colors.WHITE,
                GUI.Colors.BLACK
        );
    }
}

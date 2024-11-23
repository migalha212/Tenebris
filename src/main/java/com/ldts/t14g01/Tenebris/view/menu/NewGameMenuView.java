package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.utils.Difficulty;
import com.ldts.t14g01.Tenebris.utils.Position;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;

public class NewGameMenuView extends View<Menu> {
    public NewGameMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) throws IOException {
        gui.setType(GUI.Type.MENU);
        // ToDo : Draw BackGround

        // Place Menu Tittle
        gui.drawText(
                "Select Difficulty",
                new Position(4, 6),
                GUI.Colors.WHITE,
                GUI.Colors.BLACK
        );

        // Draw Options
        for (String name : this.getModel().getOptions()) {
            Difficulty difficulty = Difficulty.valueOf(name);
            GUI.Colors foreGroundColor = GUI.Colors.WHITE;

            // Highlight selected Option
            if (difficulty.name().equals(this.getModel().getOptions().get(this.getModel().getSelectedOption())))
                foreGroundColor = GUI.Colors.YELLOW;

            // Draw Option
            gui.drawText(
                    difficulty.name(),
                    new Position(4, 10 + difficulty.ordinal()),
                    foreGroundColor,
                    GUI.Colors.BLACK
            );
        }

        // ToDo: Improve messages
        String description = "";
        switch (Difficulty.valueOf(this.getModel().getOptions().get(this.getModel().getSelectedOption()))) {
            case Easy -> description = "Meant for beginners"; //to explore\nand learn the ways of Tenebris";
            case Normal -> description = "Increased combat difficulty";
            case Champion -> description = "Unforgiving challenge awaits";
            case Heartless -> description = "Good Luck.";
        }

        // Draw option description
        gui.drawText(
                description,
                new Position(25, 10 + this.getModel().getSelectedOption()),
                GUI.Colors.WHITE,
                GUI.Colors.BLACK
        );
    }
}

package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.utils.Position;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;

public class MainMenuView extends View<Menu> {

    public MainMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) throws IOException {
        gui.setType(GUI.Type.MENU);
        // ToDo : Draw Background

        // Get center x and center y position
        int centerX = gui.getTerminalSize().getColumns() / 2;
        int centerY = gui.getTerminalSize().getRows() / 2;

        // Align Options Left
        int leftX = 4;

        // Draw Title
        String title = "Main Menu";
        gui.drawText(
                title,
                new Position(
                        centerX - title.length() / 2,
                        centerY - 4
                ),
                GUI.Colors.WHITE,
                GUI.Colors.BLACK
        );

        // Draw options (left-aligned)
        for (int i = 0; i < this.getModel().getOptions().size(); i++) {
            GUI.Colors foreGroundColor = GUI.Colors.WHITE;

            // Add markers for the selected option
            if (i == this.getModel().getSelectedOption())
                foreGroundColor = GUI.Colors.YELLOW;

            // Draw option
            gui.drawText(
                    this.getModel().getOptions().get(i).replace('_', ' '),
                    new Position(leftX, centerY + i),
                    foreGroundColor,
                    GUI.Colors.BLACK
            );
        }
    }
}

package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.View;

public class PauseMenuView extends View<Menu> {
    public PauseMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements() {
        GUI gui = GUI.getGUI();

        // Get center x and center y position
        int centerX = gui.getWindowSize().x() / 2;
        int centerY = gui.getWindowSize().y() / 2;

        // Align Options Left
        int leftX = 4;

        // Draw Title
        String title = "Pause Menu";
        gui.drawText(
                title,
                new Vector2D(centerX - title.length() / 2, centerY - 4),
                GUI.Colors.WHITE,
                GUI.Colors.BLACK
        );

        // Draw options (left-aligned)
        for (int i = 0; i < this.getModel().getOptions().size(); i++) {
            GUI.Colors foreGroundColor;

            // Add markers for the selected option
            if (i == this.getModel().getSelectedOption()) foreGroundColor = GUI.Colors.YELLOW;
            else foreGroundColor = GUI.Colors.WHITE;

            // Draw option
            gui.drawText(
                    this.getModel().getOptions().get(i).replace('_', ' '),
                    new Vector2D(leftX, centerY + i),
                    foreGroundColor,
                    GUI.Colors.BLACK
            );
        }
    }
}

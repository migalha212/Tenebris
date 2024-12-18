package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelCompletedMenuView extends View<Menu> {
    public LevelCompletedMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements() throws IOException {
        GUI gui = GUI.getGUI();

        // Get center position
        int centerX = gui.getWindowSize().x() / 2;
        int centerY = gui.getWindowSize().y() / 2;

        // Title Lines
        List<String> title = new ArrayList<>();
        title.add("▗▖   ▗▄▄▄▖▗▖  ▗▖▗▄▄▄▖▗▖       ▗▄▄▄  ▗▄▖ ▗▖  ▗▖▗▄▄▄▖");
        title.add("▐▌   ▐▌   ▐▌  ▐▌▐▌   ▐▌       ▐▌  █▐▌ ▐▌▐▛▚▖▐▌▐▌   ");
        title.add("▐▌   ▐▛▀▀▘▐▌  ▐▌▐▛▀▀▘▐▌       ▐▌  █▐▌ ▐▌▐▌ ▝▜▌▐▛▀▀▘");
        title.add("▐▙▄▄▖▐▙▄▄▖ ▝▚▞▘ ▐▙▄▄▖▐▙▄▄▖    ▐▙▄▄▀▝▚▄▞▘▐▌  ▐▌▐▙▄▄▖");

        // Draw Title
        for (int i = 0; i < title.size(); i++)
            gui.drawText(
                    title.get(i),
                    new Vector2D(centerX - title.getFirst().length() / 2, 3 + i),
                    GUI.Colors.CYAN,
                    GUI.Colors.BLACK
            );


        // Draw Options
        for (int i = 0; i < this.getModel().getOptions().size(); i++) {
            GUI.Colors foreGroundColor;

            // Add markers for the selected option
            if (i == this.getModel().getSelectedOption()) foreGroundColor = GUI.Colors.YELLOW;
            else foreGroundColor = GUI.Colors.WHITE;

            // Calculate x position
            int spaceX;
            if (i == 0) spaceX = 18;
            else if (i == 1) spaceX = gui.getWindowSize().x() - 30;
            else spaceX = 0;

            // Draw option
            gui.drawText(
                    this.getModel().getOptions().get(i).replace('_', ' '),
                    new Vector2D(spaceX, centerY + 4),
                    foreGroundColor,
                    GUI.Colors.BLACK
            );
        }
    }
}

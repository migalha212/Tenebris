package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeathMenuView extends View<Menu> {
    public DeathMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) throws IOException {
        int centerX = gui.getWindowSize().x() / 2;
        int centerY = gui.getWindowSize().y() / 2;

        List<String> title = new ArrayList<>();
        title.add("▗▖  ▗▖ ▗▄▖ ▗▖ ▗▖    ▗▄▄▄ ▗▄▄▄▖▗▄▄▄▖▗▄▄▄ ");
        title.add(" ▝▚▞▘ ▐▌ ▐▌▐▌ ▐▌    ▐▌  █  █  ▐▌   ▐▌  █");
        title.add("  ▐▌  ▐▌ ▐▌▐▌ ▐▌    ▐▌  █  █  ▐▛▀▀▘▐▌  █");
        title.add("  ▐▌  ▝▚▄▞▘▝▚▄▞▘    ▐▙▄▄▀▗▄█▄▖▐▙▄▄▖▐▙▄▄▀");

        for (int i = 0; i < title.size(); i++)
            gui.drawText(title.get(i), new Vector2D(centerX - title.getFirst().length() / 2, 3 + i), GUI.Colors.RED, GUI.Colors.BLACK);


        for (int i = 0; i < this.getModel().getOptions().size(); i++) {
            GUI.Colors foreGroundColor = GUI.Colors.WHITE;

            // Add markers for the selected option
            if (i == this.getModel().getSelectedOption()) foreGroundColor = GUI.Colors.YELLOW;

            int spaceX = 0;

            if (i == 0) spaceX = 18;
            else if (i == 1) spaceX = gui.getWindowSize().x() - 30;

            // Draw option
            gui.drawText(this.getModel().getOptions().get(i).replace('_', ' '), new Vector2D(spaceX, centerY + 4), foreGroundColor, GUI.Colors.BLACK);
        }

    }

}

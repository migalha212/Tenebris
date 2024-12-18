package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameOverMenuView extends View<Menu> {
    public GameOverMenuView(Menu model) {
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
        title.add(" ▗▄▄▖ ▗▄▖ ▗▖  ▗▖▗▄▄▄▖     ▗▄▖ ▗▖  ▗▖▗▄▄▄▖▗▄▄▖");
        title.add("▐▌   ▐▌ ▐▌▐▛▚▞▜▌▐▌       ▐▌ ▐▌▐▌  ▐▌▐▌   ▐▌ ▐▌");
        title.add("▐▌▝▜▌▐▛▀▜▌▐▌  ▐▌▐▛▀▀▘    ▐▌ ▐▌▐▌  ▐▌▐▛▀▀▘▐▛▀▚▖");
        title.add("▝▚▄▞▘▐▌ ▐▌▐▌  ▐▌▐▙▄▄▖    ▝▚▄▞▘ ▝▚▞▘ ▐▙▄▄▖▐▌ ▐▌");

        // Draw Title
        for (int i = 0; i < title.size(); i++)
            gui.drawText(
                    title.get(i),
                    new Vector2D(centerX - title.getFirst().length() / 2, 3 + i),
                    GUI.Colors.RED,
                    GUI.Colors.BLACK
            );

        // Draw Option
        gui.drawText(
                "Back To Main Menu",
                new Vector2D(centerX - 8, centerY + 4),
                GUI.Colors.YELLOW,
                GUI.Colors.BLACK
        );
    }
}

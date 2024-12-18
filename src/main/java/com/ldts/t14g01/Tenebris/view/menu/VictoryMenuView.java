package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.utils.Vector2D;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VictoryMenuView extends View<Menu> {
    public VictoryMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements() throws IOException {
        GUI gui = GUI.getGUI();

        int centerX = gui.getWindowSize().x() / 2;
        int centerY = gui.getWindowSize().y() / 2;

        List<String> title = new ArrayList<>();
        title.add("▗▖  ▗▖ ▗▄▖ ▗▖ ▗▖    ▗▖ ▗▖ ▗▄▖ ▗▖  ▗▖");
        title.add(" ▝▚▞▘ ▐▌ ▐▌▐▌ ▐▌    ▐▌ ▐▌▐▌ ▐▌▐▛▚▖▐▌");
        title.add("  ▐▌  ▐▌ ▐▌▐▌ ▐▌    ▐▌ ▐▌▐▌ ▐▌▐▌ ▝▜▌");
        title.add("  ▐▌  ▝▚▄▞▘▝▚▄▞▘    ▐▙█▟▌▝▚▄▞▘▐▌  ▐▌");

        for (int i = 0; i < title.size(); i++)
            gui.drawText(
                    title.get(i),
                    new Vector2D(centerX - title.getFirst().length() / 2, 3 + i),
                    GUI.Colors.GREEN,
                    GUI.Colors.BLACK
            );

        gui.drawText(
                "Back To Main Menu",
                new Vector2D(centerX - 8, centerY + 4),
                GUI.Colors.YELLOW,
                GUI.Colors.BLACK
        );
    }
}

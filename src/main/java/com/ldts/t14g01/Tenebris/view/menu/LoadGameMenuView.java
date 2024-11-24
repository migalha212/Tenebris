package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.utils.Position;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;

public class LoadGameMenuView extends View<Menu> {
    public LoadGameMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) throws IOException {
        String message = "This menu has not been implemented yet -_-";

        int centerX = gui.getWindowSize().x() / 2;
        int centerY = gui.getWindowSize().y() / 2;

        gui.drawText(
                message,
                new Position(
                        centerX - message.length() / 2,
                        centerY
                ),
                GUI.Colors.YELLOW,
                GUI.Colors.BLACK
        );
    }
}

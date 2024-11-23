package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.utils.Position;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;

public class CreditsMenuView extends View<Menu> {
    public CreditsMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) throws IOException {
        gui.setType(GUI.Type.MENU);

        gui.drawText(
                "teste",
                new Position(4, 6),
                GUI.Colors.WHITE,
                GUI.Colors.BLACK
        );
    }
}

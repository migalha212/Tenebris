package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;

public class VictoryMenuView extends View<Menu> {
    public VictoryMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements() throws IOException {
        GUI.getGUI().drawVictoryMenu();
    }
}

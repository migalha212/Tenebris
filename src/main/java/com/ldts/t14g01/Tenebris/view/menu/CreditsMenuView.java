package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.view.View;

public class CreditsMenuView extends View<Menu> {
    public CreditsMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements() {
        GUI.getGUI().drawCreditsMenu();
    }
}

package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.DeathMenu;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeathMenuView extends View<Menu> {
    public DeathMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements() throws IOException {

        List<GUI.Menu_Options> options = new ArrayList<>();
        for (String option : this.getModel().getOptions()) {
            switch (DeathMenu.DeathMenuOptions.valueOf(option)) {
                case Retry -> options.add(GUI.Menu_Options.DEATH_MENU_RETRY);
                case Return_To_Main_Menu -> options.add(GUI.Menu_Options.DEATH_MENU_RETURN);
            }
        }
        GUI.getGUI().drawDeathMenu(options, this.getModel().getSelectedOption());
    }
}

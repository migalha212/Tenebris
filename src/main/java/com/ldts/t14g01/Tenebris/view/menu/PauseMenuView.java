package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.model.menu.PauseMenu;
import com.ldts.t14g01.Tenebris.view.View;

import java.util.ArrayList;
import java.util.List;

public class PauseMenuView extends View<Menu> {
    public PauseMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements() {
        List<GUI.Menu_Options> options = new ArrayList<>();
        for (String option : this.getModel().getOptions()) {
            switch (PauseMenu.PauseMenuOptions.valueOf(option)) {
                case Continue -> options.add(GUI.Menu_Options.PAUSE_CONTINUE);
                case Restart_Level -> options.add(GUI.Menu_Options.PAUSE_RESTART);
                case Return_to_Main_Menu -> options.add(GUI.Menu_Options.PAUSE_RETURN);
            }
        }
        GUI.getGUI().drawPauseMenuMenu(options, this.getModel().getSelectedOption());
    }
}

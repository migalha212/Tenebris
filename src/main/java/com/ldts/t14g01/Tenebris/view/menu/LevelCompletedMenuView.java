package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.LevelCompletedMenu;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
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
        List<GUI.Menu_Options> options = new ArrayList<>();
        for (String option : this.getModel().getOptions()) {
            switch (LevelCompletedMenu.LevelCompletedMenuOptions.valueOf(option)) {
                case Next_Level -> options.add(GUI.Menu_Options.LEVEL_COMPLETED_MENU_NEXT_LEVEL);
                case Return_To_Main_Menu -> options.add(GUI.Menu_Options.LEVEL_COMPLETED_MENU_RETURN);
            }
        }
        GUI.getGUI().drawLevelCompletedMenu(options, this.getModel().getSelectedOption());
    }
}

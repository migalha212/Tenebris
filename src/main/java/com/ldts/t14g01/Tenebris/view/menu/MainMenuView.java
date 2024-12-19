package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.MainMenu;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainMenuView extends View<Menu> {

    public MainMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements() throws IOException {
        List<GUI.Menu_Options> options = new ArrayList<>();

        for (String option : this.getModel().getOptions()) {
            switch (MainMenu.MainMenuOptions.valueOf(option)) {
                case New_Game -> options.add(GUI.Menu_Options.MAIN_MENU_NEW_GAME);
                case Continue -> options.add(GUI.Menu_Options.MAIN_MENU_CONTINUE);
                case Load_Game -> options.add(GUI.Menu_Options.MAIN_MENU_LOAD_GAME);
                case Levels -> options.add(GUI.Menu_Options.MAIN_MENU_LEVELS);
                case How_to_Play -> options.add(GUI.Menu_Options.MAIN_MENU_HOW_TO_PLAY);
                case Credits -> options.add(GUI.Menu_Options.MAIN_MENU_CREDITS);
                case Exit -> options.add(GUI.Menu_Options.MAIN_MENU_EXIT);
            }
        }

        GUI.getGUI().drawMainMenu(options, this.getModel().getSelectedOption());
    }
}

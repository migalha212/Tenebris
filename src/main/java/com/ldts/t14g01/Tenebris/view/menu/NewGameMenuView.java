package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.utils.Difficulty;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewGameMenuView extends View<Menu> {
    public NewGameMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements() throws IOException {
        List<GUI.Menu_Options> options = new ArrayList<>();

        for (String option : this.getModel().getOptions()) {
            switch (Difficulty.valueOf(option)) {
                case Easy -> options.add(GUI.Menu_Options.EASY_DIFFICULTY);
                case Normal -> options.add(GUI.Menu_Options.NORMAL_DIFFICULTY);
                case Champion -> options.add(GUI.Menu_Options.CHAMPION_DIFFICULTY);
                case Heartless -> options.add(GUI.Menu_Options.HEARTLESS_DIFFICULTY);
            }
        }

        GUI.getGUI().drawNewGameMenu(options, this.getModel().getSelectedOption());
    }
}

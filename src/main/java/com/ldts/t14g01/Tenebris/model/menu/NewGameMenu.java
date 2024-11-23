package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.controller.menu.NewGameMenuController;
import com.ldts.t14g01.Tenebris.utils.Difficulty;
import com.ldts.t14g01.Tenebris.view.View;
import com.ldts.t14g01.Tenebris.view.menu.NewGameMenuView;

public class NewGameMenu extends Menu {
    public NewGameMenu() {
        super();
        this.createOptions();
    }

    private void createOptions() {
        for (Difficulty difficulty : Difficulty.values())
            this.options.add(difficulty.name());
    }

    @Override
    public View<Menu> getView() {
        return new NewGameMenuView(this);
    }

    @Override
    public Controller<Menu> getController() {
        return new NewGameMenuController(this);
    }
}

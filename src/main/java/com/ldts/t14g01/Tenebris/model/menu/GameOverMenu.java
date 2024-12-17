package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.controller.menu.GameOverMenuController;
import com.ldts.t14g01.Tenebris.view.View;
import com.ldts.t14g01.Tenebris.view.menu.GameOverMenuView;

public class GameOverMenu extends Menu {
    public GameOverMenu() {
        super();
    }

    @Override
    public View<Menu> getView() {
        return new GameOverMenuView(this);
    }

    @Override
    public Controller<Menu> getController() {
        return new GameOverMenuController(this);
    }
}

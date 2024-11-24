package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.controller.menu.LevelsMenuController;
import com.ldts.t14g01.Tenebris.view.View;
import com.ldts.t14g01.Tenebris.view.menu.LevelsMenuView;

public class LevelsMenu extends Menu {
    public LevelsMenu() {
        super();
    }

    @Override
    public View<Menu> getView() {
        return new LevelsMenuView(this);
    }

    @Override
    public Controller<Menu> getController() {
        return new LevelsMenuController(this);
    }
}

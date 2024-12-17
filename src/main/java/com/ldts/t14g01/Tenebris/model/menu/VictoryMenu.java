package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.controller.menu.VictoryMenuController;
import com.ldts.t14g01.Tenebris.view.View;
import com.ldts.t14g01.Tenebris.view.menu.VictoryMenuView;

public class VictoryMenu extends Menu {
    public VictoryMenu() {
        super();
    }

    @Override
    public View<Menu> getView() {
        return new VictoryMenuView(this);
    }

    @Override
    public Controller<Menu> getController() {
        return new VictoryMenuController(this);
    }
}

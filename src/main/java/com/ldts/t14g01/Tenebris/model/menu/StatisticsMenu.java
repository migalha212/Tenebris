package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.controller.menu.StatisticsMenuController;
import com.ldts.t14g01.Tenebris.view.View;
import com.ldts.t14g01.Tenebris.view.menu.StatisticsMenuView;

public class StatisticsMenu extends Menu {
    @Override
    public View<Menu> getView() {
        return new StatisticsMenuView(this);
    }

    @Override
    public Controller<Menu> getController() {
        return new StatisticsMenuController(this);
    }
}

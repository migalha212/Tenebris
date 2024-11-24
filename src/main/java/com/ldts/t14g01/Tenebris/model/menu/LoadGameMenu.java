package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.controller.menu.LoadGameMenuController;
import com.ldts.t14g01.Tenebris.view.View;
import com.ldts.t14g01.Tenebris.view.menu.LoadGameMenuView;

public class LoadGameMenu extends Menu {
    public LoadGameMenu() {
        super();
        this.createOptions();
    }

    private void createOptions() {
        // ToDo: Create Options
    }

    @Override
    public View<Menu> getView() {
        return new LoadGameMenuView(this);
    }

    @Override
    public Controller<Menu> getController() {
        return new LoadGameMenuController(this);
    }
}

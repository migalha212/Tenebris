package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.controller.menu.CreditsMenuController;
import com.ldts.t14g01.Tenebris.view.View;
import com.ldts.t14g01.Tenebris.view.menu.CreditsMenuView;

public class CreditsMenu extends Menu {
    public CreditsMenu() {
        super();
    }

    @Override
    public View<Menu> getView() {
        return new CreditsMenuView(this);
    }

    @Override
    public Controller<Menu> getController() {
        return new CreditsMenuController(this);
    }
}

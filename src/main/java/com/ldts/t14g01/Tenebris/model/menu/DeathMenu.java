package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.controller.menu.DeathMenuController;
import com.ldts.t14g01.Tenebris.view.View;
import com.ldts.t14g01.Tenebris.view.menu.DeathMenuView;

public class DeathMenu extends Menu {
    public enum DeathMenuOptions {
        Retry, Return_To_Main_Menu
    }

    public DeathMenu() {
        super();
        this.options.add(DeathMenuOptions.Retry.name());
        this.options.add(DeathMenuOptions.Return_To_Main_Menu.name());
    }

    @Override
    public View<Menu> getView() {
        return new DeathMenuView(this);
    }

    @Override
    public Controller<Menu> getController() {
        return new DeathMenuController(this);
    }
}

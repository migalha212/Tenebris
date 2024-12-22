package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.controller.menu.LevelCompletedMenuController;
import com.ldts.t14g01.Tenebris.view.View;
import com.ldts.t14g01.Tenebris.view.menu.LevelCompletedMenuView;

public class LevelCompletedMenu extends Menu {
    public enum LevelCompletedMenuOptions {
        Next_Level, Return_To_Main_Menu
    }

    public LevelCompletedMenu() {
        super();
        this.options.add(LevelCompletedMenuOptions.Next_Level.name());
        this.options.add(LevelCompletedMenuOptions.Return_To_Main_Menu.name());
    }

    @Override
    public View<Menu> getView() {
        return new LevelCompletedMenuView(this);
    }

    @Override
    public Controller<Menu> getController() {
        return new LevelCompletedMenuController(this);
    }
}

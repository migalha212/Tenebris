package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.controller.menu.PauseMenuController;
import com.ldts.t14g01.Tenebris.model.arena.Arena;
import com.ldts.t14g01.Tenebris.view.View;
import com.ldts.t14g01.Tenebris.view.menu.PauseMenuView;

public class PauseMenu extends Menu {
    private Arena arena;

    public enum PauseMenuOptions {
        Continue,
        Statistics,
        Back_to_Main_Menu
    }

    public PauseMenu(Arena arena) {
        super();
        this.arena = arena;
        this.options.add(PauseMenuOptions.Continue.name());
        this.options.add(PauseMenuOptions.Statistics.name());
        this.options.add(PauseMenuOptions.Back_to_Main_Menu.name());
    }

    public Arena getArena() {
        return this.arena;
    }

    @Override
    public View<Menu> getView() {
        return new PauseMenuView(this);
    }

    @Override
    public Controller<Menu> getController() {
        return new PauseMenuController(this);
    }
}

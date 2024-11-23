package com.ldts.t14g01.Tenebris.controller;

import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.model.menu.Menu;

public class MenuController extends Controller<Menu> {
    public MenuController(Menu model) {
        super(model);
    }

    void executeOption() {}

    void quit() {}

    @Override
    public void tick(Action action) {
        switch (action) {
            case LOOK_UP -> this.getModel().moveUp();
            case LOOK_DOWN -> this.getModel().moveDown();
            case EXEC -> this.executeOption();
            case ESC, QUIT -> this.quit();
            case null, default -> {
            }
        }
    }
}

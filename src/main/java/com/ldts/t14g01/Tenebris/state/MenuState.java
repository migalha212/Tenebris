package com.ldts.t14g01.Tenebris.state;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.controller.MenuController;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.view.View;

public class MenuState extends State<Menu> {
    public MenuState(Menu model) {
        super(model);
    }

    @Override
    protected View<Menu> getView() {
        return this.getModel().getView();
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(this.getModel());
    }
}

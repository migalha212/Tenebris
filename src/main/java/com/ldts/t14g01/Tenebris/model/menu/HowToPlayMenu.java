package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.controller.menu.HowToPlayMenuController;
import com.ldts.t14g01.Tenebris.view.View;
import com.ldts.t14g01.Tenebris.view.menu.HowToPlayMenuView;

public class HowToPlayMenu extends Menu {
    public enum HowToPlayMenuOptions {
        Menu_Navigation,
        Default_Controls,
        Objective,
        Game_Basics,
        Weapons,
        Enemies,
        Difficulty_Levels,
        Map_Elements,
        Back
    }

    public HowToPlayMenu() {
        super();
        this.createOptions();
    }

    private void createOptions() {
        for (HowToPlayMenuOptions option : HowToPlayMenuOptions.values())
            this.options.add(option.name());
    }

    @Override
    public View<Menu> getView() {
        return new HowToPlayMenuView(this);
    }

    @Override
    public Controller<Menu> getController() {
        return new HowToPlayMenuController(this);
    }
}

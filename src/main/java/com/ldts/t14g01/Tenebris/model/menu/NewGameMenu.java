package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.utils.Difficulty;
import com.ldts.t14g01.Tenebris.view.NewGameMenuView;
import com.ldts.t14g01.Tenebris.view.View;

public class NewGameMenu extends Menu {
    public NewGameMenu() {
        super();
        this.createOptions();
    }

    private void createOptions() {
        for (Difficulty difficulty : Difficulty.values())
            this.options.add(difficulty.name());
    }

    @Override
    public View<Menu> getView() {
        return new NewGameMenuView(this);
    }
}

package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.controller.menu.LoadGameMenuController;
import com.ldts.t14g01.Tenebris.savedata.SaveDataManager;
import com.ldts.t14g01.Tenebris.view.View;
import com.ldts.t14g01.Tenebris.view.menu.LoadGameMenuView;

public class LoadGameMenu extends Menu {
    public LoadGameMenu() {
        super();
        this.updateOptions();
    }

    public void updateOptions() {
        // Clear Previous Options
        this.options.clear();

        // Add Empty Strings to represent each save
        // The index of the String will represent the selected Number
        for (int i = 0; i < SaveDataManager.getInstance().getSaveCount(); i++) this.options.add("");

        if (this.selectedOption >= this.options.size()) this.selectedOption = this.options.size() - 1;
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

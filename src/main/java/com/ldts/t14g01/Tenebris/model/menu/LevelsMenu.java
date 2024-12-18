package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.controller.menu.LevelsMenuController;
import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.view.View;
import com.ldts.t14g01.Tenebris.view.menu.LevelsMenuView;

public class LevelsMenu extends Menu {
    public LevelsMenu(SaveDataProvider saveDataProvider) {
        super();
        this.createOptions(saveDataProvider);
    }

    private void createOptions(SaveDataProvider saveDataProvider) {
        SaveData saveData = saveDataProvider.getSaveData();

        // The menu should not work with no saveData Selected
        if (saveData == null) {
            this.options = null;
            return;
        }

        // Add Empty Strings to represent each save
        // The index of the String will represent the selected Number
        for (int i = 0; i < saveData.getLevel(); i++) this.options.add("");
    }

    @Override
    public View<Menu> getView() {
        return new LevelsMenuView(this);
    }

    @Override
    public Controller<Menu> getController() {
        return new LevelsMenuController(this);
    }
}

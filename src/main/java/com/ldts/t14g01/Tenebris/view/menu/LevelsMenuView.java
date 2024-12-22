package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.savedata.SaveDataManager;
import com.ldts.t14g01.Tenebris.utils.Difficulty;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;

public class LevelsMenuView extends View<Menu> {
    public LevelsMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements() throws IOException {
        int unlockedLevel = SaveDataManager.getInstance().getLastOpen().getLevel();
        int selectedLevel = this.getModel().getSelectedOption() + 1;
        Difficulty difficulty = SaveDataManager.getInstance().getLastOpen().getDifficulty();
        GUI.getGUI().drawLevelsMenu(unlockedLevel, selectedLevel, difficulty);
    }
}

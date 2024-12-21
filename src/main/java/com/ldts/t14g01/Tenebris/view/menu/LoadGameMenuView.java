package com.ldts.t14g01.Tenebris.view.menu;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.savedata.SaveDataManager;
import com.ldts.t14g01.Tenebris.utils.Difficulty;
import com.ldts.t14g01.Tenebris.view.View;

import java.io.IOException;

public class LoadGameMenuView extends View<Menu> {
    public LoadGameMenuView(Menu model) {
        super(model);
    }

    @Override
    protected void drawElements() throws IOException {
        int numberOfSaves = SaveDataManager.getInstance().getSaveCount();

        if (numberOfSaves == 0) {
            GUI.getGUI().drawLoadGameMenu(0, 0, 0, null);
            return;
        }

        int activeSaveNumber = this.getModel().getSelectedOption() + 1;
        SaveData selectedSave = SaveDataManager.getInstance().getSave(this.getModel().getSelectedOption() + 1);
        Difficulty difficulty = selectedSave.getDifficulty();
        int level = selectedSave.getLevel();

        GUI.getGUI().drawLoadGameMenu(numberOfSaves, activeSaveNumber, level, difficulty);
    }
}

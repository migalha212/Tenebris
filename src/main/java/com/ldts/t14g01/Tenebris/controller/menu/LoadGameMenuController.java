package com.ldts.t14g01.Tenebris.controller.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.model.arena.ArenaBuilder;
import com.ldts.t14g01.Tenebris.model.menu.LoadGameMenu;
import com.ldts.t14g01.Tenebris.model.menu.MainMenu;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.savedata.SaveDataManager;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.state.ArenaState;
import com.ldts.t14g01.Tenebris.state.MenuState;
import com.ldts.t14g01.Tenebris.state.StateChanger;

import java.io.IOException;

public class LoadGameMenuController extends Controller<Menu> {
    public LoadGameMenuController(Menu model) {
        super(model);
    }

    @Override
    public void tick(Action action, StateChanger stateChanger, SaveDataProvider saveDataProvider) throws IOException {
        // Update Option Count
        ((LoadGameMenu) this.getModel()).updateOptions();

        switch (action) {
            case ESC -> stateChanger.setState(new MenuState(new MainMenu(saveDataProvider)));
            case QUIT -> stateChanger.setState(null);
            case LOOK_UP -> this.getModel().moveDown();
            case LOOK_DOWN -> this.getModel().moveUp();
            case EXEC -> this.executeOption(stateChanger, saveDataProvider);
            case MOVE_RIGHT -> {
                if (SaveDataManager.getInstance().getSaveCount() == 0) return;
                SaveData saveToDelete = SaveDataManager.getInstance().getSave(this.getModel().getSelectedOption() + 1);
                if (saveToDelete.equals(saveDataProvider.getSaveData())) saveDataProvider.setSaveData(null);
                SaveDataManager.getInstance().deleteSave(saveToDelete);

                // Update Option Count
                ((LoadGameMenu) this.getModel()).updateOptions();
            }
            case null, default -> {
            }
        }
    }

    void executeOption(StateChanger stateChanger, SaveDataProvider saveDataProvider) throws IOException {
        // Go Back to Main Menu if no saves exist
        if (SaveDataManager.getInstance().getSaveCount() == 0) {
            stateChanger.setState(new MenuState(new MainMenu(saveDataProvider)));
            return;
        }

        // Load Arena Otherwise
        SaveData loadedSaveData = SaveDataManager.getInstance().getSave(this.getModel().getSelectedOption() + 1);
        saveDataProvider.setSaveData(loadedSaveData);
        stateChanger.setState(new ArenaState(ArenaBuilder.build(loadedSaveData)));
    }
}

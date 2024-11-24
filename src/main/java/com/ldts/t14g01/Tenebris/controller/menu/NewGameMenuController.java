package com.ldts.t14g01.Tenebris.controller.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.model.arena.Arena;
import com.ldts.t14g01.Tenebris.model.menu.MainMenu;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.state.ArenaState;
import com.ldts.t14g01.Tenebris.state.MenuState;
import com.ldts.t14g01.Tenebris.state.StateChanger;
import com.ldts.t14g01.Tenebris.utils.Difficulty;

public class NewGameMenuController extends Controller<Menu> {
    public NewGameMenuController(Menu model) {
        super(model);
    }

    void executeOption(StateChanger stateChanger, SaveDataProvider saveDataProvider) {
        // Create new Save Data
        saveDataProvider.setSaveData(new SaveData(Difficulty.valueOf(this.getModel().getOptions().get(this.getModel().getSelectedOption()))));
        stateChanger.setState(new ArenaState(new Arena()));
    }

    void quit(StateChanger stateChanger) {
        stateChanger.setState(null);
    }

    @Override
    public void tick(Action action, StateChanger stateChanger, SaveDataProvider saveDataProvider) {
        switch (action) {
            case LOOK_UP -> this.getModel().moveUp();
            case LOOK_DOWN -> this.getModel().moveDown();
            case EXEC -> this.executeOption(stateChanger, saveDataProvider);
            case ESC -> stateChanger.setState(new MenuState(new MainMenu(saveDataProvider)));
            case QUIT -> this.quit(stateChanger);
            case null, default -> {
            }
        }
    }
}

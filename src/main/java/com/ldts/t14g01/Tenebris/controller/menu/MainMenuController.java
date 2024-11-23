package com.ldts.t14g01.Tenebris.controller.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.model.menu.HowToPlayMenu;
import com.ldts.t14g01.Tenebris.model.menu.MainMenu;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.model.menu.NewGameMenu;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.state.MenuState;
import com.ldts.t14g01.Tenebris.state.StateChanger;

public class MainMenuController extends Controller<Menu> {
    public MainMenuController(Menu model) {
        super(model);
    }

    void executeOption(StateChanger stateChanger) {
        switch (MainMenu.MainMenuOptions.valueOf(this.getModel().getOptions().get(this.getModel().getSelectedOption()))) {
            case New_Game -> stateChanger.setState(new MenuState(new NewGameMenu()));
            case How_to_Play -> stateChanger.setState(new MenuState(new HowToPlayMenu()));
            case Exit -> this.quit(stateChanger);
            // ToDo: Add Other Menus when they are available
            case null, default -> {
            }
        }
    }

    void quit(StateChanger stateChanger) {
        stateChanger.setState(null);
    }

    @Override
    public void tick(Action action, StateChanger stateChanger, SaveDataProvider saveDataProvider) {
        switch (action) {
            case LOOK_UP -> this.getModel().moveUp();
            case LOOK_DOWN -> this.getModel().moveDown();
            case EXEC -> this.executeOption(stateChanger);
            case ESC, QUIT -> this.quit(stateChanger);
            case null, default -> {
            }
        }
    }
}

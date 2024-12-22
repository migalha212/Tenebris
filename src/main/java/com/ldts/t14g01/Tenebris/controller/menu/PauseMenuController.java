package com.ldts.t14g01.Tenebris.controller.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.ArenaBuilder;
import com.ldts.t14g01.Tenebris.model.menu.MainMenu;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.model.menu.PauseMenu;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import com.ldts.t14g01.Tenebris.state.ArenaState;
import com.ldts.t14g01.Tenebris.state.MenuState;
import com.ldts.t14g01.Tenebris.state.StateChanger;

import java.io.IOException;

public class PauseMenuController extends Controller<Menu> {
    public PauseMenuController(Menu model) {
        super(model);
    }

    void executeOption(StateChanger stateChanger, SaveDataProvider saveDataProvider) throws IOException {
        SoundManager.getInstance().playSFX(SoundManager.SFX.MENU_SELECT);
        switch (PauseMenu.PauseMenuOptions.valueOf(this.getModel().getOptions().get(this.getModel().getSelectedOption()))) {
            case Continue -> this.goBackToArena(stateChanger);
            case Restart_Level ->
                    stateChanger.setState(new ArenaState(ArenaBuilder.build(saveDataProvider.getSaveData())));
            // case Statistics -> stateChanger.setState(new MenuState(new StatisticsMenu()));
            case Back_to_Main_Menu -> stateChanger.setState(new MenuState(new MainMenu(saveDataProvider)));
            default -> {
            }
        }
    }

    private void goBackToArena(StateChanger stateChanger) throws IOException {
        SoundManager.getInstance().playSFX(SoundManager.SFX.MENU_GO_BACK);
        stateChanger.setState(new ArenaState(((PauseMenu) this.getModel()).getArena()));
    }

    void quit(StateChanger stateChanger) throws IOException {
        stateChanger.setState(null);
    }

    @Override
    public void tick(StateChanger stateChanger, SaveDataProvider saveDataProvider) throws IOException, InterruptedException {
        Action action = GUI.getGUI().getAction();

        switch (action) {
            case LOOK_UP -> this.getModel().moveUp();
            case LOOK_DOWN -> this.getModel().moveDown();
            case EXEC -> this.executeOption(stateChanger, saveDataProvider);
            case QUIT -> this.quit(stateChanger);
            case ESC -> this.goBackToArena(stateChanger);
            case null, default -> {
            }
        }
    }
}

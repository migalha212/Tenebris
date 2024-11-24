package com.ldts.t14g01.Tenebris.controller.arena;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.model.arena.Arena;
import com.ldts.t14g01.Tenebris.model.menu.MainMenu;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.state.MenuState;
import com.ldts.t14g01.Tenebris.state.StateChanger;


public class ArenaController extends Controller<Arena> {
    public ArenaController(Arena model) {
        super(model);
    }

    @Override
    public void tick(Action action, StateChanger stateChanger, SaveDataProvider saveDataProvider) {
        switch (action) {
            case ESC -> stateChanger.setState(new MenuState(new MainMenu(saveDataProvider)));
            case QUIT -> stateChanger.setState(null);
            case null, default -> {
            }
        }
    }
}

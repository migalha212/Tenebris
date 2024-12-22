package com.ldts.t14g01.Tenebris.controller.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.MainMenu;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import com.ldts.t14g01.Tenebris.state.MenuState;
import com.ldts.t14g01.Tenebris.state.StateChanger;

import java.io.IOException;

public class GameOverMenuController extends Controller<Menu> {
    private static final int DELAY = 15;
    private int frameCount;

    public GameOverMenuController(Menu model) {
        super(model);
    }

    @Override
    public void tick(StateChanger stateChanger, SaveDataProvider saveDataProvider) throws IOException, InterruptedException {
        frameCount++;
        Action action = GUI.getGUI().getAction();
        if (frameCount < DELAY) return;

        switch (action) {
            case ESC, EXEC -> {
                SoundManager.getInstance().playSFX(SoundManager.SFX.MENU_GO_BACK);
                stateChanger.setState(new MenuState(new MainMenu(saveDataProvider)));
            }
            case QUIT -> stateChanger.setState(null);
            case null, default -> {
            }
        }
    }
}

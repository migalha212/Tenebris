package com.ldts.t14g01.Tenebris.controller.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.arena.ArenaBuilder;
import com.ldts.t14g01.Tenebris.model.menu.MainMenu;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.savedata.SaveDataManager;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import com.ldts.t14g01.Tenebris.state.ArenaState;
import com.ldts.t14g01.Tenebris.state.MenuState;
import com.ldts.t14g01.Tenebris.state.StateChanger;

import java.io.IOException;

public class LevelsMenuController extends Controller<Menu> {
    public LevelsMenuController(Menu model) {
        super(model);
    }

    @Override
    public void tick(StateChanger stateChanger, SaveDataProvider saveDataProvider) throws IOException, InterruptedException {
        Action action = GUI.getGUI().getAction();

        switch (action) {
            case EXEC -> this.executeOption(stateChanger, saveDataProvider);
            case LOOK_UP, LOOK_DOWN -> this.moveVertical();
            case LOOK_LEFT -> this.getModel().moveUp();
            case LOOK_RIGHT -> this.getModel().moveDown();
            case QUIT -> stateChanger.setState(null);
            case ESC -> {
                SoundManager.getInstance().playSFX(SoundManager.SFX.MENU_GO_BACK);
                stateChanger.setState(new MenuState(new MainMenu(saveDataProvider)));
            }
            case null, default -> {
            }
        }
    }

    private void moveVertical() {
        if (this.getModel().getOptions().isEmpty()) return;
        if (this.getModel().getSelectedOption() + 1 > 3)
            this.getModel().setSelectedOption(this.getModel().getSelectedOption() - 3);
        else if (this.getModel().getSelectedOption() + 3 < this.getModel().getOptions().size())
            this.getModel().setSelectedOption(this.getModel().getSelectedOption() + 3);
        SoundManager.getInstance().playSFX(SoundManager.SFX.MENU_SWITCH);
    }

    public void executeOption(StateChanger stateChanger, SaveDataProvider saveDataProvider) throws IOException {
        SoundManager.getInstance().playSFX(SoundManager.SFX.MENU_SELECT);

        // load arena
        SaveData newSaveData = SaveDataManager.getInstance().createNewSave(
                SaveDataManager.getInstance().getLastOpen().getDifficulty(),
                this.getModel().getSelectedOption() + 1
        );
        saveDataProvider.setSaveData(newSaveData);
        stateChanger.setState(new ArenaState(ArenaBuilder.build(newSaveData)));
    }
}

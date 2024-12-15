package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.controller.menu.MainMenuController;
import com.ldts.t14g01.Tenebris.savedata.SaveDataManager;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.view.View;
import com.ldts.t14g01.Tenebris.view.menu.MainMenuView;

public class MainMenu extends Menu {
    public enum MainMenuOptions {
        New_Game,
        Load_Game,
        Levels,
        Continue,
        How_to_Play,
        Credits,
        Exit
    }

    public MainMenu(SaveDataProvider saveDataProvider) {
        super();
        this.createOptions(saveDataProvider);
    }

    private void createOptions(SaveDataProvider saveDataProvider) {
        this.options.add(MainMenuOptions.New_Game.name());

        // Continue Option only Visible if a game save is loaded
        if (saveDataProvider.getSaveData() != null) {
            this.options.add(MainMenuOptions.Continue.name());
        }

        // Load Game Menu and Levels only visible if saved Games exist
        if (SaveDataManager.getInstance().getSaveCount() != 0) {
            this.options.add(MainMenuOptions.Load_Game.name());
            this.options.add(MainMenuOptions.Levels.name());
        }

        this.options.add(MainMenuOptions.How_to_Play.name());
        this.options.add(MainMenuOptions.Credits.name());
        this.options.add(MainMenuOptions.Exit.name());
    }

    @Override
    public View<Menu> getView() {
        return new MainMenuView(this);
    }

    @Override
    public Controller<Menu> getController() {
        return new MainMenuController(this);
    }
}

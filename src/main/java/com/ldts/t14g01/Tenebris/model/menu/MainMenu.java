package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.controller.menu.MainMenuController;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.view.menu.MainMenuView;
import com.ldts.t14g01.Tenebris.view.View;

public class MainMenu extends Menu {
    private static final String name = "Main Menu";
    public enum MainMenuOptions {
        New_Game,
        Load_Game,
        Levels,
        Statistics,
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
        this.options.add(MainMenuOptions.Load_Game.name());

        // Levels only visible if a game is loaded
        if (saveDataProvider.getSaveData() != null) {
            this.options.add(MainMenuOptions.Levels.name());
            this.options.add(MainMenuOptions.Statistics.name());
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

package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.SaveData;
import com.ldts.t14g01.Tenebris.view.MainMenuView;
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

    public MainMenu(SaveData saveData) {
        super();
        this.createOptions(saveData);
    }

    private void createOptions(SaveData saveData) {
        this.options.add(MainMenuOptions.New_Game.name());
        this.options.add(MainMenuOptions.Load_Game.name());

        // Levels only visible if a game is loaded
        if (saveData != null) {
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
}

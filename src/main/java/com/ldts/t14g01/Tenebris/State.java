package com.ldts.t14g01.Tenebris;

import com.ldts.t14g01.Tenebris.menus.MainMenu;
import com.ldts.t14g01.Tenebris.menus.Menu;
import com.ldts.t14g01.Tenebris.screen.ScreenGetter;

import java.io.IOException;

public class State {
    private GameData gameData;
    private Menu menu;
    private boolean inMenu;

    private State(GameData gameData, Menu menu, boolean inMenu) {
        this.gameData = gameData;
        this.menu = menu;
        this.inMenu = inMenu;
    }

    // Create Initial game state
    // Only on game boot
    // Default to the Main Menu
    public static State initState() {
        return new State(null, new MainMenu(), true);
    }

    public boolean isInMenu() {
        return inMenu;
    }

    public void runMenu(ScreenGetter screenGetter) throws IOException, InterruptedException {
        // Run menus
        while (this.menu != null && this.inMenu)
            this.menu.run(screenGetter, this);

        // Clear Menu State
        this.inMenu = false;
        this.menu = null;
    }
}

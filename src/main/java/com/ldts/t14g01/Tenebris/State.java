package com.ldts.t14g01.Tenebris;

import com.ldts.t14g01.Tenebris.menus.MainMenu;
import com.ldts.t14g01.Tenebris.menus.Menu;

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

    public void setInMenu(boolean inMenu) {
        this.inMenu = inMenu;
    }

    // Returns true is the game has any save loaded
    public boolean hasLoadedGame() {
        return this.gameData != null;
    }

    public Menu currentMenu() {
        if (!this.inMenu) return null;
        return this.menu;
    }

    public void setNextMenu(Menu menu) {
        this.menu = menu;
        this.inMenu = true;
    }

    public void quit() {
        this.menu = null;
        this.gameData = null;
        this.inMenu = false;
    }
}

package com.ldts.t14g01.Tenebris.state;

import com.ldts.t14g01.Tenebris.GameData;
import com.ldts.t14g01.Tenebris.menus.CreditsMenu;
import com.ldts.t14g01.Tenebris.menus.MainMenu;
import com.ldts.t14g01.Tenebris.menus.Menu;
import com.ldts.t14g01.Tenebris.menus.NewGameMenu;
import com.ldts.t14g01.Tenebris.screen.ScreenGetter;

import java.io.IOException;

public class State {
    private static final State instance = new State();
    private States state;
    private GameData gameData;
    private Menu menu;

    private State() {
        // Init state to MainMenu
        this.setState(States.MAIN_MENU);
    }

    public static State getInstance() {
        return State.instance;
    }

    public void loadGame(GameData gameData) {
        this.gameData = gameData;
    }

    public boolean setState(States state) {
        if (this.state == state) return true;

        // Sanity check
        switch (state) {
            case IN_GAME, PAUSE_MENU, STATISTICS_MENU, LEVELS_MENU -> {
                // Invalid state because no gameData has been loaded
                if (this.gameData == null) return false;
            }
        }

        // Update menu
        switch (state) {
            case MAIN_MENU -> this.menu = new MainMenu(this);
            case NEW_GAME_MENU -> this.menu = new NewGameMenu();
            case CREDITS_MENU -> this.menu = new CreditsMenu();

            // ToDo: Fill this with the other menus when they work
            default -> this.menu = new MainMenu(this);
        }

        // Update state
        this.state = state;
        return true;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public void tick(ScreenGetter screenGetter) throws IOException, InterruptedException {
        this.menu.run(this, screenGetter);

    }

    public boolean isRunning() {
        return this.state != null;
    }

    // Returns true is the game has any save loaded
    public boolean hasLoadedGame() {
        return this.gameData != null;
    }

    public void quit() {
        this.state = null;
        this.gameData = null;
    }
}

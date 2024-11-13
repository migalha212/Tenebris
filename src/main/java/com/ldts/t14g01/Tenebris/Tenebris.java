package com.ldts.t14g01.Tenebris;

import com.googlecode.lanterna.screen.Screen;
import com.ldts.t14g01.Tenebris.screen.ScreenGetter;
import com.ldts.t14g01.Tenebris.screen.ScreenManager;
import com.ldts.t14g01.Tenebris.screen.ScreenRelaunchHandler;

import java.io.IOException;

public class Tenebris implements ScreenRelaunchHandler, ScreenGetter {
    private Screen screen;
    private final State state;

    public static void main(String[] args) throws IOException, InterruptedException {
        Tenebris tenebris = new Tenebris();
        tenebris.run();
    }

    Tenebris() throws IOException {
        // Init game state
        this.state = State.initState();
        ScreenManager.setScreenRelaunchHandler(this);
    }

    public void run() throws IOException, InterruptedException {
        // Run while in menus of in a game
        while (this.state.hasLoadedGame() || this.state.isInMenu()) {
            // Run Menus
            while (this.state.isInMenu() && this.state.currentMenu() != null) {
                this.screen = ScreenManager.newScreen(ScreenManager.MAIN_MENU);
                this.state.currentMenu().run(this, this.state);
            }

            // Clear Menu State
            this.state.setInMenu(false);
            this.state.setNextMenu(null);

            // Run Game
            while (this.state.hasLoadedGame()){
                this.screen = ScreenManager.newScreen(ScreenManager.ARENA);
                //this.state.currentArena().run(this, this.state);
            }
        }

        // End of Game
        // Close Screen
        this.screen.close();
    }

    // Receives new screen when it is relaunched
    @Override
    public void handle(Screen screen) {
        this.screen = screen;
    }

    @Override
    public Screen getScreen() {
        return this.screen;
    }
}

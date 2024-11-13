package com.ldts.t14g01.Tenebris;

import com.googlecode.lanterna.screen.Screen;
import com.ldts.t14g01.Tenebris.screen.ScreenGetter;
import com.ldts.t14g01.Tenebris.screen.ScreenManager;
import com.ldts.t14g01.Tenebris.screen.ScreenRelaunchHandler;
import com.ldts.t14g01.Tenebris.state.State;

import java.io.IOException;

public class Tenebris implements ScreenRelaunchHandler, ScreenGetter {
    private final State state;
    private Screen screen;

    public static void main(String[] args) throws IOException, InterruptedException {
        Tenebris tenebris = new Tenebris();
        tenebris.run();
    }

    Tenebris() throws IOException {
        // Init game state
        this.state = State.getInstance();
        ScreenManager.setScreenRelaunchHandler(this);
    }

    public void run() throws IOException, InterruptedException {
        long frameTime = 1 / 30;

        // While the game is running
        while (this.state.isRunning()) {
            // Record start time
            long startTime = System.currentTimeMillis();

            // Update screen
            this.screen = ScreenManager.newScreen(ScreenManager.MAIN_MENU);
            this.state.tick(this);

            // Wait
            long endTime = System.currentTimeMillis();
            long waitTime = endTime - startTime - frameTime;
            if (waitTime > 0) Thread.sleep(waitTime);
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

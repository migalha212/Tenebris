package com.ldts.t14g01.Tenebris;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.MainMenu;
import com.ldts.t14g01.Tenebris.state.State;
import com.ldts.t14g01.Tenebris.state.MenuState;

import java.io.IOException;

public class Tenebris {
    private final GUI gui;
    private final State state;
    private final SaveData saveData;

    public static void main(String[] args) throws IOException, InterruptedException {
        Tenebris tenebris = new Tenebris();
        tenebris.run();
    }

    Tenebris() {
        // Init game state
        this.state = new MenuState(new MainMenu(null));
        this.gui = GUI.getGUI();
        this.saveData = null;
    }

    public void run() throws IOException, InterruptedException {
        long frameTime = 1000 / 30;

        // While the game is running
        while (this.state != null) {
            // Record start time
            long startTime = System.currentTimeMillis();

            // Tick game
            this.state.tick(gui);

            // Wait to limit frame rate
            long endTime = System.currentTimeMillis();
            long waitTime = frameTime - (endTime - startTime);
            if (waitTime > 0) Thread.sleep(waitTime);
        }

        // End of Game so Close Screen
        this.gui.close();
    }
}

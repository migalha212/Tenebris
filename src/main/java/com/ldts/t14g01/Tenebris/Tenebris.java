package com.ldts.t14g01.Tenebris;

import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.state.State;

import java.io.IOException;

public class Tenebris {
    private final State state;
    private final GUI gui;

    public static void main(String[] args) throws IOException, InterruptedException {
        Tenebris tenebris = new Tenebris();
        tenebris.run();
    }

    Tenebris() throws IOException {
        // Init game state
        this.state = State.getInstance();
        this.gui = GUI.getGUI();
    }

    public void run() throws IOException, InterruptedException {
        long frameTime = 1000 / 30;

        // While the game is running
        while (this.state.isRunning()) {
            // Record start time
            long startTime = System.currentTimeMillis();

            // Get action
            Action action = gui.getAction();

            // Close game if window was closed
            if (gui.quited()) {
                this.state.quit();
                continue;
            }

            // Tick game
            this.state.tick(gui, action);

            // Wait to limit frame rate
            long endTime = System.currentTimeMillis();
            long waitTime = frameTime - (endTime - startTime);
            if (waitTime > 0) Thread.sleep(waitTime);
        }

        // End of Game so Close Screen
        this.gui.close();
    }
}

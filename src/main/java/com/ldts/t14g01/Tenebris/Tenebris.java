package com.ldts.t14g01.Tenebris;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.MainMenu;
import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.state.MenuState;
import com.ldts.t14g01.Tenebris.state.State;
import com.ldts.t14g01.Tenebris.state.StateChanger;

import java.io.IOException;

public class Tenebris implements StateChanger, SaveDataProvider {
    private final GUI gui;
    private State state;
    private SaveData saveData;

    public static void main(String[] args) throws IOException, InterruptedException {
        Tenebris tenebris = new Tenebris();
        tenebris.run();
    }

    Tenebris() {
        // Init game state
        this.state = new MenuState(new MainMenu(this));
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
            this.state.tick(gui, this, this);

            // Wait to limit frame rate
            long endTime = System.currentTimeMillis();
            long waitTime = frameTime - (endTime - startTime);
            if (waitTime > 0) Thread.sleep(waitTime);
        }

        // End of Game so Close Screen
        this.gui.close();
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    @Override
    public SaveData getSaveData() {
        return this.saveData;
    }

    @Override
    public void setSaveData(SaveData saveData) {
        if (this.saveData != null) this.saveData.save();
        this.saveData = saveData;
    }
}

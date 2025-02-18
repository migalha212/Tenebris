package com.ldts.t14g01.Tenebris;

import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.model.menu.MainMenu;
import com.ldts.t14g01.Tenebris.savedata.SaveData;
import com.ldts.t14g01.Tenebris.savedata.SaveDataManager;
import com.ldts.t14g01.Tenebris.savedata.SaveDataProvider;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import com.ldts.t14g01.Tenebris.state.MenuState;
import com.ldts.t14g01.Tenebris.state.State;
import com.ldts.t14g01.Tenebris.state.StateChanger;

import java.io.IOException;

public class Tenebris implements StateChanger, SaveDataProvider {
    public static final int FPS = 30;
    private static Tenebris instance;

    private State state;
    private boolean stateChanged;
    private SaveData saveData;

    public static void main(String[] args) throws Exception {
        Tenebris.getInstance().run();
    }

    private Tenebris() throws Exception {
        // Init game state
        this.saveData = SaveDataManager.getInstance().getLastOpen();
        this.setState(new MenuState(new MainMenu(this)));
    }

    public static Tenebris getInstance() throws Exception {
        if (Tenebris.instance == null) Tenebris.instance = new Tenebris();
        return Tenebris.instance;
    }

    public void run() throws IOException, InterruptedException {
        long frameTime = 1000 / FPS;
        // While the game is running
        while (this.state != null) {
            // Record start time
            long startTime = System.currentTimeMillis();

            // Tick game
            this.stateChanged = false;
            this.state.tick(this, this);

            // Wait to limit frame rate
            long endTime = System.currentTimeMillis();
            long waitTime = frameTime - (endTime - startTime);
            if (waitTime > 0) Thread.sleep(waitTime);
        }

        SoundManager.getInstance().playSFX(SoundManager.SFX.QUIT);
        GUI.getGUI().close();
    }

    @Override
    public void setState(State state) throws IOException {
        this.stateChanged = true;
        this.state = state;
    }

    @Override
    public boolean stateChanged() {
        return this.stateChanged;
    }

    @Override
    public SaveData getSaveData() {
        return this.saveData;
    }

    @Override
    public void setSaveData(SaveData saveData) {
        this.saveData = saveData;
    }
}

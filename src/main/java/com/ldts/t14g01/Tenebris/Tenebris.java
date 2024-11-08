package com.ldts.t14g01.Tenebris;

import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Tenebris {
    Screen screen;

    public static void main(String[] args) throws IOException {
        Tenebris tenebris = new Tenebris();
        tenebris.run();
    }

    Tenebris() throws IOException {
        this.screen = ScreenManager.createScreen(ScreenManager.MAIN_MENU);
    }

    public void run() {
    }
}
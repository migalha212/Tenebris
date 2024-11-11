package com.ldts.t14g01.Tenebris;

import com.googlecode.lanterna.screen.Screen;
import com.ldts.t14g01.Tenebris.menus.MainMenu;
import java.io.IOException;

public class Tenebris {
    private Screen screen;

    public static void main(String[] args) throws IOException {
        Tenebris tenebris = new Tenebris();
        tenebris.run();
    }

    Tenebris() throws IOException {
        this.screen = ScreenManager.createScreen(ScreenManager.MAIN_MENU);
        screen.startScreen();
    }

    public void run() throws IOException {
        MainMenu mainMenu = new MainMenu();

        boolean gameStart = mainMenu.runMenu(screen);
        screen.stopScreen();

        if (gameStart) {
            System.out.println("Starting the game...");
        } else {
            System.out.println("Exiting Tenebris.");
        }
    }
}

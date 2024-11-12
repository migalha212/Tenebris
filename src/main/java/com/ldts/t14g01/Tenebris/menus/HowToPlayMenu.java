package com.ldts.t14g01.Tenebris.menus;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.ldts.t14g01.Tenebris.State;
import com.ldts.t14g01.Tenebris.screen.ScreenGetter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HowToPlayMenu implements Menu {
    private static final String name = "Credits";

    public HowToPlayMenu() {
    }

    @Override
    public String getName() {
        return HowToPlayMenu.name;
    }

    @Override
    public void run(ScreenGetter screenGetter, State state) throws IOException, InterruptedException {
        // Get options
        Screen screen;

        // While the active menu is this
        while (this.equals(state.currentMenu())) {
            // Get most up-to-date screen
            screen = screenGetter.getScreen();

            // Draw menu
            draw(screen);

            // Delay
            Thread.sleep(50);

            // Read keystroke
            KeyStroke keyStroke = screen.pollInput();
            if (keyStroke != null) {
                switch (keyStroke.getKeyType()) {
                    case Enter -> this.returnToMainMenu(state);
                    case Escape -> this.returnToMainMenu(state);
                    case EOF -> this.handleEOFCharacter(screenGetter, state);
                }
                if (keyStroke.getCharacter() != null) {
                    switch (keyStroke.getCharacter()) {
                        case 'Q', 'q', 'E', 'e' -> this.returnToMainMenu(state);
                    }
                }
            }
        }
    }

    private void draw(Screen screen) throws IOException {
        //Clear Screen
        screen.clear();

        // Get TextGraphics
        TextGraphics textGraphics = screen.newTextGraphics();

        // Get center position
        int centerX = screen.getTerminalSize().getColumns() / 2;
        int centerY = screen.getTerminalSize().getRows() / 2;

        // Draw Title;
        textGraphics
                .setForegroundColor(TextColor.Factory.fromString("#DAA520"))
                    .putString(
                            centerX - HowToPlayMenu.name.length()/2,
                            centerY - 8,
                            HowToPlayMenu.name
                    );

        // Draw Back Button
        String backText = "Back";
        textGraphics
                .setForegroundColor(TextColor.ANSI.YELLOW)
                .putString(
                        centerX - backText.length() / 2,
                        screen.getTerminalSize().getRows() - 3,
                        backText
                );

        // Update Screen
        screen.refresh();
    }

    //Set next menu to the selected option
    //By now, the only option is to go back to Main Menu
    private void returnToMainMenu(State state) {
        Menu nextMenu = new MainMenu();
        state.setNextMenu(nextMenu);
    }

    private void quit(State state) {
        state.quit();
    }

    private void handleEOFCharacter(ScreenGetter screenGetter, State state) throws IOException, InterruptedException {
        // Wait to give possible screen reload time
        Thread.sleep(250);

        Screen screen = screenGetter.getScreen();
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke != null)
            if (keyStroke.getKeyType() == KeyType.EOF) this.quit(state);
    }

}

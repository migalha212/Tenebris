package com.ldts.t14g01.Tenebris.menus;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.ldts.t14g01.Tenebris.GameData;
import com.ldts.t14g01.Tenebris.screen.ScreenGetter;
import com.ldts.t14g01.Tenebris.state.State;
import com.ldts.t14g01.Tenebris.state.States;
import com.ldts.t14g01.Tenebris.utils.Difficulty;

import java.io.IOException;


public class NewGameMenu implements Menu {
    private static final String name = "New Game";
    private static final Difficulty[] options = Difficulty.values();
    private int selectedOption;

    public NewGameMenu() {
        this.selectedOption = Difficulty.Normal.ordinal();
    }

    @Override
    public void run(State state, ScreenGetter screenGetter) throws IOException, InterruptedException {

        // Get the up-to-date screen
        Screen screen = screenGetter.getScreen();

        // Draw the menu
        draw(screen);

        // Read keystroke
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke != null) {
            switch (keyStroke.getKeyType()) {
                case ArrowUp -> selectedOption = (selectedOption - 1 + options.length) % options.length;
                case ArrowDown -> selectedOption = (selectedOption + 1) % options.length;
                case Enter -> this.executeOption(state);
                case Escape -> this.backToMain(state);
                case EOF -> this.handleEOFCharacter(screenGetter, state);
            }
            if (keyStroke.getCharacter() != null) {
                switch (keyStroke.getCharacter()) {
                    case 'Q', 'q' -> this.backToMain(state);
                    case 'E', 'e' -> this.executeOption(state);
                }
            }
        }

    }

    private void executeOption(State state) {
        Difficulty difficulty = options[this.selectedOption];
        GameData gameData = new GameData(difficulty);
        state.setGameData(gameData);

        // Temporary while arena isn't created
        state.setState(States.MAIN_MENU);
    }

    private void draw(Screen screen) throws IOException {
        // Clear current screen
        screen.clear();

        // Get textGraphics
        TextGraphics textGraphics = screen.newTextGraphics();

        // Place Menu Tittle
        textGraphics
                .setForegroundColor(TextColor.ANSI.WHITE)
                .putString(
                        4,
                        6,
                        "Select Difficulty"
                );

        // Draw Options
        for (Difficulty difficulty : options) {
            TextColor color = TextColor.ANSI.WHITE;

            // Highlight selected Option
            if (difficulty.ordinal() == this.selectedOption) color = TextColor.ANSI.YELLOW;

            // Draw Option
            textGraphics
                    .setForegroundColor(color)
                    .putString(4, 10 + difficulty.ordinal(), difficulty.name());
        }

        // ToDo: Improve messages
        String description = "";
        switch (options[this.selectedOption]) {
            case Easy -> description = "Meant for beginners"; //to explore\nand learn the ways of Tenebris";
            case Normal -> description = "Increased combat difficulty";
            case Champion -> description = "Unforgiving challenge awaits";
            case Heartless -> description = "Good Luck.";
        }

        // Draw option description
        textGraphics
                .setForegroundColor(TextColor.ANSI.WHITE)
                .putString(25, 10 + selectedOption, description);


        screen.refresh();
    }

    // When pressing Escape or Q the game will return to the Main Menu
    private void backToMain(State state) {
        state.setState(States.MAIN_MENU);
    }

    private void handleEOFCharacter(ScreenGetter screenGetter, State state) throws IOException, InterruptedException {
        // Wait to give possible screen reload time
        Thread.sleep(250);

        Screen screen = screenGetter.getScreen();
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke != null)
            if (keyStroke.getKeyType() == KeyType.EOF) this.quit(state);
    }

    private void quit(State state) {
        state.quit();
    }
}

package com.ldts.t14g01.Tenebris.menus;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.ldts.t14g01.Tenebris.GameData;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.state.State;
import com.ldts.t14g01.Tenebris.state.States;
import com.ldts.t14g01.Tenebris.utils.Difficulty;

import java.io.IOException;

public class NewGameMenu implements Menu {
    private static final Difficulty[] options = Difficulty.values();
    private int selectedOption;

    public NewGameMenu() {
        this.selectedOption = Difficulty.Normal.ordinal();
    }

    @Override
    public void tick(State state, Action action) {
        switch (action) {
            case LOOK_UP -> selectedOption = (selectedOption - 1 + options.length) % options.length;
            case LOOK_DOWN -> selectedOption = (selectedOption + 1) % options.length;
            case EXEC -> this.executeOption(state);
            case ESC, QUIT -> this.backToMain(state);
            case null, default -> {}
        }
    }

    @Override
    public void draw(GUI gui) throws IOException {
        // Clear Screen
        gui.clear();

        // Get Text Graphics
        TextGraphics textGraphics = gui.getTextGraphics();

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


        gui.refresh();
    }

    private void executeOption(State state) {
        Difficulty difficulty = options[this.selectedOption];
        GameData gameData = new GameData(difficulty);
        state.setGameData(gameData);

        // Temporary while arena isn't created
        state.setState(States.MAIN_MENU);
    }

    // When pressing Escape or Q the game will return to the Main Menu
    private void backToMain(State state) {
        state.setState(States.MAIN_MENU);
    }
}

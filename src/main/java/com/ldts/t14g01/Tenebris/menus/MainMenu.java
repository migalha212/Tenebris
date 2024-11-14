package com.ldts.t14g01.Tenebris.menus;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.ldts.t14g01.Tenebris.gui.Action;
import com.ldts.t14g01.Tenebris.gui.GUI;
import com.ldts.t14g01.Tenebris.state.State;
import com.ldts.t14g01.Tenebris.state.States;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainMenu implements Menu {
    private static final String name = "Main Menu";
    private int selectedOption;
    private final List<States> options;

    public MainMenu(State state) {
        this.selectedOption = 0;

        // Get options
        this.options = new ArrayList<>();
        this.updateOptions(state);
    }

    @Override
    public void tick(State state, Action action) {
        switch (action) {
            case LOOK_UP -> selectedOption = (selectedOption - 1 + options.size()) % options.size();
            case LOOK_DOWN -> selectedOption = (selectedOption + 1) % options.size();
            case EXEC -> this.executeOption(state);
            case ESC, QUIT -> this.quit(state);
            case null, default -> {
            }
        }

    }

    @Override
    public void draw(GUI gui) throws IOException {
        // Clear Screen
        gui.clear();

        // Get TextGraphics
        TextGraphics textGraphics = gui.getTextGraphics();

        // Get center x and center y position
        int centerX = gui.getTerminalSize().getColumns() / 2;
        int centerY = gui.getTerminalSize().getRows() / 2;

        // Align Options Left
        int leftX = 4;

        // Draw Title
        textGraphics
                .setForegroundColor(TextColor.ANSI.WHITE)
                .putString(
                        centerX - MainMenu.name.length() / 2,
                        centerY - 4,
                        MainMenu.name
                );

        // Draw options (left-aligned)
        for (int i = 0; i < options.size(); i++) {
            // Get name and color
            String name = getOptionName(i);
            TextColor color = TextColor.ANSI.WHITE;

            // Add markers for the selected option
            if (i == selectedOption) {
                color = TextColor.ANSI.YELLOW;
            }

            // Draw option
            textGraphics
                    .setForegroundColor(color)
                    .putString(leftX, centerY + i, name);
        }

        // Update Screen
        gui.refresh();
    }

    private void updateOptions(State state) {
        options.clear();
        options.add(States.NEW_GAME_MENU);
        options.add(States.LOAD_GAME_MENU);

        // Levels only visible if a game is loaded
        if (state.hasLoadedGame()) {
            options.add(States.LEVELS_MENU);
            options.add(States.STATISTICS_MENU);
        }

        options.add(States.HOW_TO_PLAY_MENU);
        options.add(States.SETTINGS_MENU);
        options.add(States.CREDITS_MENU);

        // Quit option
        options.add(null);
    }

    private String getOptionName(int i) {
        States option = options.get(i);
        String name;

        switch (option) {
            case NEW_GAME_MENU -> name = "New Game";
            case LOAD_GAME_MENU -> name = "Load Game";
            case LEVELS_MENU -> name = "Levels";
            case STATISTICS_MENU -> name = "Statistics";
            case HOW_TO_PLAY_MENU -> name = "How to Play";
            case SETTINGS_MENU -> name = "Settings";
            case CREDITS_MENU -> name = "Credits";
            case null -> name = "Exit";
            default -> name = "";
        }
        return name;
    }

    private void executeOption(State state) {
        States nextState = options.get(selectedOption);

        if (nextState == null) {
            this.quit(state);
            return;
        }
        state.setState(nextState);
    }

    private void quit(State state) {
        state.quit();
    }
}

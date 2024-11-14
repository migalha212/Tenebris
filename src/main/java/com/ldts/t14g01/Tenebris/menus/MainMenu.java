package com.ldts.t14g01.Tenebris.menus;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.ldts.t14g01.Tenebris.screen.ScreenGetter;
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
    public void run(State state, ScreenGetter screenGetter) throws IOException, InterruptedException {
        // Get most up-to-date screen
        Screen screen = screenGetter.getScreen();

        // Draw menu
        draw(screen);

        // Read keystroke
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke != null) {
            switch (keyStroke.getKeyType()) {
                case ArrowUp -> selectedOption = (selectedOption - 1 + options.size()) % options.size();
                case ArrowDown -> selectedOption = (selectedOption + 1) % options.size();
                case Enter -> this.executeOption(state);
                case Escape -> this.quit(state);
                case EOF -> this.handleEOFCharacter(screenGetter, state);
            }
            if (keyStroke.getCharacter() != null)
                switch (keyStroke.getCharacter()) {
                    case 'Q', 'q' -> this.quit(state);
                    case 'E', 'e' -> this.executeOption(state);
                }
        }

    }

    private void draw(Screen screen) throws IOException {
        // Clear Screen
        screen.clear();

        // Get TextGraphics
        TextGraphics textGraphics = screen.newTextGraphics();

        // Get center x and center y position
        int centerX = screen.getTerminalSize().getColumns() / 2;
        int centerY = screen.getTerminalSize().getRows() / 2;

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
        screen.refresh();
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

    // Set next menu to the selected option
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

    private void handleEOFCharacter(ScreenGetter screenGetter, State state) throws IOException, InterruptedException {
        // Wait to give possible screen reload time
        Thread.sleep(250);

        Screen screen = screenGetter.getScreen();
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke != null)
            if (keyStroke.getKeyType() == KeyType.EOF) this.quit(state);
    }
}

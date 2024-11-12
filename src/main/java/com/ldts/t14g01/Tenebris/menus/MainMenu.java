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

public class MainMenu implements Menu {
    private static final String name = "Main Menu";
    private int selectedOption;
    private final List<Menu> options;

    public MainMenu() {
        this.selectedOption = 0;
        this.options = new ArrayList<>();
    }

    private void updateOptions(State state) {
        options.clear();
        options.add(new NewGameMenu());
        options.add(new LoadGameMenu());

        // Levels only visible if a game is loaded
        if (state.hasLoadedGame())
            options.add(new LevelsMenu());

        options.add(new SettingsMenu());
        options.add(new HowToPlayMenu());
        options.add(new CreditsMenu());

        // Quit option
        options.add(null);
    }

    @Override
    public String getName() {
        return MainMenu.name;
    }

    @Override
    public void run(ScreenGetter screenGetter, State state) throws IOException, InterruptedException {
        // Get options
        this.updateOptions(state);
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
        for (int i = 0; i < this.options.size(); i++) {
            Menu menu = this.options.get(i);
            String name = menu == null ? "Exit" : menu.getName();

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

    // Set next menu to the selected option
    private void executeOption(State state) {
        Menu nextMenu = this.options.get(this.selectedOption);
        if (nextMenu == null) this.quit(state);
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

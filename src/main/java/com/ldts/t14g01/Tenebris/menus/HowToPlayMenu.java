package com.ldts.t14g01.Tenebris.menus;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.ldts.t14g01.Tenebris.GameData;
import com.ldts.t14g01.Tenebris.State;
import com.ldts.t14g01.Tenebris.screen.ScreenGetter;
import com.ldts.t14g01.Tenebris.utils.Difficulty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HowToPlayMenu implements Menu {
    private static final String name = "How to Play";
    private int selectedOption;
    private final List<String> options;

    public HowToPlayMenu() {
        this.selectedOption = 0;
        this.options = new ArrayList<>();
        updateOptions();
    }

    public void updateOptions(){
        options.add("Menu Navigation");
        options.add("Default Controls");
        options.add("Game Basics");
        options.add("Weapons");
        options.add("Enemies");
        options.add("Bosses");
        options.add("Difficulty Levels");
        options.add("Map Elements");
        options.add("Back");
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
                    case ArrowUp -> selectedOption = (selectedOption - 1 + options.size()) % options.size();
                    case ArrowDown -> selectedOption = (selectedOption + 1) % options.size();
                    case Enter -> this.executeOption(state);
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

    private void executeOption(State state) {
        String Option = options.get(this.selectedOption);
        //As the only selectable option is the Back Button
        if (Objects.equals(Option, "Back")){
            state.setNextMenu(new MainMenu());
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

        //X axis Offset
        int offsetX = 4;

        // Draw Title;
        List<String> titleLines = new ArrayList<>();
        titleLines.add(HowToPlayMenu.name);
        titleLines.add("───────────────");

        for (int i = 0; i < titleLines.size(); i++) {
            textGraphics
                    .setForegroundColor(TextColor.Factory.fromString("#DAA520"))
                    .putString(
                            offsetX,
                            centerY - 8 + i,
                            titleLines.get(i)
                    );
        }

        //Draw Options
        for (int i = 0; i < options.size(); i++) {
            TextColor color = TextColor.ANSI.WHITE;

            // Highlight selected Option
            if (i == this.selectedOption) color = TextColor.ANSI.YELLOW;

            // Draw Option
            if (i != options.size() - 1){
                textGraphics
                        .setForegroundColor(color)
                        .putString(offsetX, 6 + i, options.get(i));

            //Draw Back Button (special case)
            } else {
                textGraphics
                        .setForegroundColor(color)
                        .putString(
                                centerX - options.get(i).length() / 2,
                                screen.getTerminalSize().getRows() - 3,
                                options.get(i)
                        );
            }
        }

//        switch (selectedOption) {
//            case 0 ->
//            case 1 ->
//            case 2 ->
//            case 3 ->
//            case 4 ->
//            case 5 ->
//            case 6 ->
//            case 7 ->
//        }

        // Update Screen
        screen.refresh();
    }

    // When pressing Escape or Q or "Back Button" the game will return to the Main Menu
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

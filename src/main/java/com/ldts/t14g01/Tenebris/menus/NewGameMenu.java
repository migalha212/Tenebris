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

public class NewGameMenu implements Menu {
    private static final String name = "New Game";
    private int selectedOption;

    enum Difficulty{
        Easy,
        Medium,
        Champion,
        Heartless
    }

    public NewGameMenu(){
        this.selectedOption = 0;
    }


    @Override
    public void run(ScreenGetter screenGetter, State state) throws IOException, InterruptedException {

        Screen screen;

        //While this is the active Menu
        while(this.equals(state.currentMenu())){
            // get the up-to-date screen
            screen = screenGetter.getScreen();

            // Draw the menu
            draw(screen);

            // Delay
            Thread.sleep(50);

            // Read keystroke
            KeyStroke keyStroke = screen.pollInput();
            if (keyStroke != null){
                switch (keyStroke.getKeyType()){
                    case ArrowUp -> selectedOption = (selectedOption - 1 + Difficulty.values().length)
                            % Difficulty.values().length;
                    case ArrowDown -> selectedOption = (selectedOption + 1) % Difficulty.values().length;
                    //case Enter -> this.executeOption(state);
                    case Escape -> this.backToMain(state);
                    case EOF -> this.handleEOFCharacter(screenGetter, state);
                }
                if(keyStroke.getCharacter() != null) {
                    switch (keyStroke.getCharacter()) {
                        case 'Q', 'q' -> this.backToMain(state);
                        //case 'E' , 'e' -> this.executeOption(state);
                    }
                }
            }
        }
    }


    @Override
    public String getName() {
        return NewGameMenu.name;
    }


    private void draw(Screen screen) throws IOException{
        // Clear current screen
        screen.clear();

        // Get Screen center
        int centerX = screen.getTerminalSize().getColumns() / 2;
        int centerY = screen.getTerminalSize().getRows() / 2;

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
        for(Difficulty difficulty : Difficulty.values()){


            TextColor color = TextColor.ANSI.WHITE;

            // Highlight selected Option
            if(difficulty.ordinal() == selectedOption){
                color = TextColor.ANSI.YELLOW;
            }

            // Draw Option
            textGraphics
                    .setForegroundColor(color)
                    .putString(4, 10 + difficulty.ordinal(), difficulty.name());
        }

        // TO DO: Draw Option Description
        String description = "";
        switch (selectedOption){
            case 0 -> description = "Meant for beginners"; //to explore\nand learn the ways of Tenebris";
            case 1 -> description = "Increased combat difficulty";
            case 2 -> description = "Unforgiving challenge awaits";
            case 3 -> description = "Good Luck.";
        }

        textGraphics
                .setForegroundColor(TextColor.ANSI.WHITE)
                .putString(25,10  + selectedOption,description);


        screen.refresh();
    }

    // When pressing Escape or Q the game will return to the Main Menu
    private void backToMain(State state) {
        Menu mainMenu = new MainMenu();
        state.setNextMenu(mainMenu);
    }

    private void handleEOFCharacter(ScreenGetter screenGetter, State state) throws  IOException, InterruptedException{
        // Wait to give possible screen reload time
        Thread.sleep(250);

        Screen screen = screenGetter.getScreen();
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke != null)
            if (keyStroke.getKeyType() == KeyType.EOF) this.quit(state);
    }

    private void quit(State state) {state.quit();}
}

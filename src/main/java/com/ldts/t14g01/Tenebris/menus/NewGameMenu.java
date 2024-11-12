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
    private final List<String> options;

    public NewGameMenu(){
        this.selectedOption = 0;
        this.options = new ArrayList<>();
        setOptions();
    }
    
    private void setOptions(){
        options.add("Easy");
        options.add("Medium");
        options.add("Hard");
        options.add("Champion");
        options.add("HEARTLESS");

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
                    case ArrowUp -> selectedOption = (selectedOption - 1 + options.size()) % options.size();
                    case ArrowDown -> selectedOption = (selectedOption + 1) % options.size();
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
        for(int i = 0; i < this.options.size(); i++){
            String name = this.options.get(i);
            ;

            TextColor color = TextColor.ANSI.WHITE;

            // Highlight selected Option
            if(i == selectedOption){
                color = TextColor.ANSI.YELLOW;
            }

            // Draw Option
            textGraphics
                    .setForegroundColor(color)
                    .putString(4, 10 + i, name);
        }

        // TO DO: Draw Option Description

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

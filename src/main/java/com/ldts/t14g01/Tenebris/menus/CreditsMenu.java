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

public class CreditsMenu implements Menu {
    private static final String name = "Credits";

    public CreditsMenu(){}

    @Override
    public String getName() {
        return CreditsMenu.name;
    }

    @Override
    public void run(ScreenGetter screenGetter, State state) throws IOException, InterruptedException {
        //Get options
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
                    case Enter -> this.executeOption(state);
                    case Escape -> this.executeOption(state);
                    case EOF -> this.handleEOFCharacter(screenGetter, state);
                }
                if (keyStroke.getCharacter() != null) {
                    switch (keyStroke.getCharacter()) {
                        case 'Q', 'q', 'E', 'e' -> this.executeOption(state);
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

        // X axis Offset
        int offsetX = 4;

        // Draw Title
        List<String> title = new ArrayList<>();
        title.add(CreditsMenu.name);
        title.add("───────────");

        for (int i = 0; i < title.size(); i++) {
            textGraphics
                    .setForegroundColor(TextColor.Factory.fromString("#DAA520"))
                    .putString(
                            offsetX,
                            centerY - 8 + i,
                            title.get(i)
                    );
        }

        //Draw UC Info
        List<String> ucInfo = new ArrayList<>();
        ucInfo.add("2024/2025");
        ucInfo.add("FEUP L.EIC014 - LDTS");

        for (int i = 0; i < title.size(); i++) {
            textGraphics
                    .setForegroundColor(TextColor.ANSI.WHITE)
                    .putString(
                            screen.getTerminalSize().getColumns() - offsetX - ucInfo.get(i).length(),
                            centerY - 8 + i,
                            ucInfo.get(i)
                    );
        }

        //Draw Authors' Name
        List<String> nameAuthor = new ArrayList<>();
        nameAuthor.add("T14G01:");
        nameAuthor.add("Cláudio Meireles");
        nameAuthor.add("Dinis Silva");
        nameAuthor.add("Miguel Pereira");

        textGraphics
                .setForegroundColor(TextColor.ANSI.WHITE)
                .putString(
                        offsetX + 2,
                        centerY - 3,
                        nameAuthor.get(0)
                );

        for (int i = 1; i < nameAuthor.size(); i++) {
            textGraphics
                    .setForegroundColor(TextColor.ANSI.WHITE)
                    .putString(
                            offsetX + 3,
                            centerY + i - 2,
                            nameAuthor.get(i)
                    );
        }

        //Draw Regent and Supervisor Names
        List<String> names = new ArrayList<>();
        names.add("Rui Maranhão");
        names.add("Juvenaldo Carvalho");

        for (int i = 0; i < names.size(); i++){
            textGraphics
                    .setForegroundColor(TextColor.ANSI.WHITE)
                    .putString(
                            screen.getTerminalSize().getColumns() - offsetX - names.get(i).length(),
                            centerY + i - 1,
                            names.get(i)
                    );
        }

        //Draw Back Button
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
    private void executeOption(State state) {
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

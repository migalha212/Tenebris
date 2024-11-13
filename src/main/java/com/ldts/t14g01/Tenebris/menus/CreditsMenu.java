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

public class CreditsMenu implements Menu {
    private static final String name = "Credits";

    public CreditsMenu() {
    }

    @Override
    public void run(State state, ScreenGetter screenGetter) throws IOException, InterruptedException {
        // Get options
        Screen screen;

        // Get most up-to-date screen
        screen = screenGetter.getScreen();

        // Draw menu
        draw(screen);

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
        List<String> titleLines = new ArrayList<>();
        titleLines.add(CreditsMenu.name);
        titleLines.add("───────────");

        for (int i = 0; i < titleLines.size(); i++) {
            textGraphics
                    .setForegroundColor(TextColor.Factory.fromString("#DAA520"))
                    .putString(
                            offsetX,
                            centerY - 8 + i,
                            titleLines.get(i)
                    );
        }

        // Draw UC Info
        List<String> ucInfoLines = new ArrayList<>();
        ucInfoLines.add("2024/2025");
        ucInfoLines.add("FEUP L.EIC014 - LDTS");

        for (int i = 0; i < titleLines.size(); i++) {
            textGraphics
                    .setForegroundColor(TextColor.ANSI.WHITE)
                    .putString(
                            screen.getTerminalSize().getColumns() - offsetX - ucInfoLines.get(i).length(),
                            centerY - 8 + i,
                            ucInfoLines.get(i)
                    );
        }

        // Draw Authors
        List<String> authorsLines = new ArrayList<>();
        authorsLines.add("T14G01:");
        authorsLines.add("Cláudio Meireles");
        authorsLines.add("Dinis Silva");
        authorsLines.add("Miguel Pereira");

        // Authors title
        textGraphics
                .setForegroundColor(TextColor.ANSI.WHITE)
                .putString(
                        offsetX + 3,
                        centerY - 3,
                        authorsLines.getFirst()
                );

        // Authors names
        for (int i = 1; i < authorsLines.size(); i++) {
            textGraphics
                    .setForegroundColor(TextColor.ANSI.WHITE)
                    .putString(
                            offsetX + 3,
                            centerY + i - 2,
                            authorsLines.get(i)
                    );
        }

        // Draw Regent and Supervisor Names
        List<String> professorsLines = new ArrayList<>();
        professorsLines.add("Rui Maranhão");
        professorsLines.add("Juvenaldo Carvalho");

        for (int i = 0; i < professorsLines.size(); i++) {
            textGraphics
                    .setForegroundColor(TextColor.ANSI.WHITE)
                    .putString(
                            screen.getTerminalSize().getColumns() - offsetX - professorsLines.get(i).length(),
                            centerY + i - 1,
                            professorsLines.get(i)
                    );
        }

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
        state.setState(States.MAIN_MENU);
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

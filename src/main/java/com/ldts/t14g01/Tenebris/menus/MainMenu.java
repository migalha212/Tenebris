package com.ldts.t14g01.Tenebris.menus;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.ldts.t14g01.Tenebris.State;
import com.ldts.t14g01.Tenebris.screen.ScreenGetter;

import java.io.IOException;

public class MainMenu implements Menu {
    private static final String[] options = {"Play", "Settings", "Quit"};
    private int selectedOption = 0;

    public MainMenu() {
    }

    public void run(ScreenGetter screenGetter, State state) throws IOException, InterruptedException {
        boolean inMenu = true;
        Screen screen;
        while (inMenu) {
            screen = screenGetter.getScreen();
            draw(screen);
            KeyStroke keyStroke = screen.pollInput();
            if (keyStroke != null) {
                switch (keyStroke.getKeyType()) {
                    case ArrowUp -> selectedOption = (selectedOption - 1 + options.length) % options.length;
                    case ArrowDown -> selectedOption = (selectedOption + 1) % options.length;
                    case Enter -> inMenu = selectOption(screen);
                    case Escape -> inMenu = false;
                }
            }
            Thread.sleep(50);
        }
    }

    private void draw(Screen screen) throws IOException {
        screen.clear();
        int centerX = screen.getTerminalSize().getColumns() / 2;
        int centerY = screen.getTerminalSize().getRows() / 2;
        String title = "Tenebris";
        screen.newTextGraphics().putString(centerX - title.length() / 2, centerY - 4, title);

        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            if (i == selectedOption) {
                screen.newTextGraphics().setForegroundColor(TextColor.ANSI.YELLOW)
                        .putString(centerX - option.length() / 2 - 2, centerY + i, "> " + option + " <");
            } else {
                screen.newTextGraphics().setForegroundColor(TextColor.ANSI.WHITE)
                        .putString(centerX - option.length() / 2, centerY + i, option);
            }
        }
        screen.refresh();
    }

    private boolean selectOption(Screen screen) {
        switch (selectedOption) {
            case 0 -> {
                System.out.println("Starting Game...");
                return false;
            }
            case 1 -> System.out.println("Opening Settings...");
            case 2 -> {
                System.out.println("Closing game...");
                return false;
            }
        }
        return true;
    }
}

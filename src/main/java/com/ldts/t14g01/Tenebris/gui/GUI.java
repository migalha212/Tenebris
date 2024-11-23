package com.ldts.t14g01.Tenebris.gui;

import com.ldts.t14g01.Tenebris.utils.Position;

import java.io.IOException;

public interface GUI {
    // Screen Types
    enum Type {
        ARENA,
        MENU
    }

    // Colors
    enum Colors {
        WHITE,
        BLACK,
        YELLOW,
        BRIGHT_YELLOW,
        BRIGHT_GREEN,
        CYAN
    }

    static GUI getGUI() {
        return LanternaGUI.getGUI();
    }

    void setType(Type type) throws IOException;

    // Actions
    Action getAction() throws IOException, InterruptedException;

    // Drawing
    void drawText(String text, Position position, Colors foreGround, Colors backGround);

    // Screen Management
    void refresh() throws IOException;

    void clear();

    void close() throws IOException;

    // Utils
    boolean stable();

    Position getWindowSize();
}

package com.ldts.t14g01.Tenebris.gui;

import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.io.IOException;
import java.util.Set;

public interface GUI {
    // Screen Types
    enum Type {
        ARENA, MENU
    }

    // Colors
    enum Colors {
        WHITE, BLACK, YELLOW, BRIGHT_YELLOW, BRIGHT_GREEN, CYAN
    }

    // Dylan States
    enum AnimationState {
        IDLE_1, IDLE_2, FRONT_1, FRONT_2, BACK_1, BACK_2, LEFT_1, LEFT_2, RIGHT_1, RIGHT_2
    }

    // Monsters
    enum Monster {
        TENEBRIS_HARBINGER,
        TENEBRIS_HEAVY,
        TENEBRIS_PEON,
        TENEBRIS_SPIKED_SCOUT,
        TENEBRIS_WARDEN
    }

    // Particles
    static int DEATH_BLOOD_FRAME_COUNT = 16;

    static GUI getGUI() {
        return LanternaGUI.getGUI();
    }

    // Actions
    Action getAction() throws IOException, InterruptedException;

    Set<Action> getActiveActions();

    // Drawing
    void drawText(String text, Vector2D position, Colors foreGround, Colors backGround);

    void drawArenaBackGround();

    void drawDylan(Vector2D position, AnimationState state);

    void drawWall(Vector2D position);

    void drawSandbag(Vector2D position);

    void drawSpikes(Vector2D position);

    void drawDeathBlood(Vector2D position, int frameNumber);

    void drawMonster(Vector2D position, Monster monster, AnimationState state);

    // Screen Management
    void setType(Type type) throws IOException;

    void refresh() throws IOException;

    void clear();

    void close() throws IOException;

    Vector2D getWindowSize();
}

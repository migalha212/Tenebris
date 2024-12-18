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
        WHITE, BLACK, YELLOW, BRIGHT_YELLOW, BRIGHT_GREEN, GREEN, BLUE, RED, CYAN, ORANGE
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

    // Static Elements
    enum StaticElement {
        WALL,
        BREAKABLE_WALL,
        SANDBAG,
        SPIKE
    }

    // Particles && Effects
    enum ParticleEffect {
        DAMAGE_BLOOD,
        DEATH_BLOOD,
        SPELL_EXPLOSION,
        EXPLOSION,
        BREAKABLE_WALL_DAMAGE
    }

    int EXPLOSION_FRAME_COUNT = 16;
    int SPELL_EXPLOSION_FRAME_COUNT = 4;
    int DEATH_BLOOD_FRAME_COUNT = 16;
    int DAMAGE_BLOOD_FRAME_COUNT = 5;
    int BREAKABLE_WALL_DAMAGE_FRAME_COUNT = 4;

    // Projectiles
    enum Projectile {
        BULLET,
        EXPLOSIVE,
        SPELL
    }

    static GUI getGUI() {
        return LanternaGUI.getGUI();
    }

    // Actions
    Action getAction() throws IOException, InterruptedException;

    Set<Action> getActiveActions();

    // Drawing
    void drawText(String text, Vector2D position, Colors foreGround, Colors backGround);

    void drawArenaBackGround();
    void drawArenaUI(int maxHP, int hp, int selectedWeapon);

    void drawDylan(Vector2D position, AnimationState state);

    void drawMonster(Vector2D position, Monster monster, AnimationState state);

    void drawStaticElement(Vector2D position, StaticElement staticElement);

    void drawParticleEffect(Vector2D position, ParticleEffect particleEffect, int frameNumber);

    void drawProjectile(Vector2D position, Projectile projectile, Vector2D.Direction direction);

    // Screen Management
    void setType(Type type) throws IOException;

    void refresh() throws IOException;

    void clear();

    void close() throws IOException;

    Vector2D getWindowSize();
}

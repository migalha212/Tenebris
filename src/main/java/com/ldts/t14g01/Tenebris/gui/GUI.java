package com.ldts.t14g01.Tenebris.gui;

import com.ldts.t14g01.Tenebris.utils.Vector2D;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface GUI {
    // Screen Types
    enum Type {
        ARENA, MENU
    }

    // Colors
    enum Colors {
        WHITE,
        BLACK,
        YELLOW,
        BRIGHT_YELLOW,
        BRIGHT_GREEN,
        GREEN,
        BLUE,
        RED,
        CYAN,
        ORANGE
    }

    // Menu Titles
    enum Menus {
        MAIN_MENU,
        NEW_GAME_MENU
    }

    // Menu Options
    enum Menu_Options {
        EASY_DIFFICULTY,
        NORMAL_DIFFICULTY,
        CHAMPION_DIFFICULTY,
        HEARTLESS_DIFFICULTY,

        MAIN_MENU_NEW_GAME,
        MAIN_MENU_CONTINUE,
        MAIN_MENU_LOAD_GAME,
        MAIN_MENU_LEVELS,
        MAIN_MENU_HOW_TO_PLAY,
        MAIN_MENU_CREDITS,
        MAIN_MENU_EXIT
    }

    // Entity States
    enum AnimationState {
        IDLE_1,
        IDLE_2,
        FRONT_1,
        FRONT_2,
        BACK_1,
        BACK_2,
        LEFT_1,
        LEFT_2,
        RIGHT_1,
        RIGHT_2
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

    // Weapons
    enum Weapon {
        PISTOL,
        GRENADE_LAUNCHER
    }

    static GUI getGUI() {
        return LanternaGUI.getGUI();
    }

    // Actions
    Action getAction() throws IOException, InterruptedException;

    Set<Action> getActiveActions();

    // Drawing
    void drawText(String text, Vector2D position, Colors foreGround, Colors backGround);

    void drawMainMenu(List<Menu_Options> options, int selectedOption);

    void drawNewGameMenu(List<Menu_Options> options, int selectedOption);

    void drawArenaBackGround();

    void drawArenaUI(int maxHP, int hp);

    void drawWeapon(Weapon weapon, int numberOfBullets);

    void drawDylan(Vector2D position, AnimationState state);

    void drawMonster(Vector2D position, Monster monster, AnimationState state);

    void drawStaticElement(Vector2D position, StaticElement staticElement);

    void drawParticleEffect(Vector2D position, ParticleEffect particleEffect, int frameNumber);

    void drawProjectile(Vector2D position, Projectile projectile, Vector2D.Direction direction);

    // Screen Management
    void refresh() throws IOException;

    void close() throws IOException;

    Vector2D getWindowSize();
}

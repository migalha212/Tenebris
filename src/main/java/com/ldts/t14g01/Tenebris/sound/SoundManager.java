package com.ldts.t14g01.Tenebris.sound;

public interface SoundManager {

    enum SFX {
        // Player
        ENTITY_DAMAGE,
        DYLAN_DEATH,

        // Projectiles
        EXPLOSION,
        SHOOT,
        GRENADE,
        SPELL_FIRE,
        SPELL_COLLISION,
        PISTOL_RELOAD,
        GRENADE_RELOAD,

        // Menu
        MENU_SWITCH,
        MENU_SELECT,
        MENU_GO_BACK,
        LEVEL_COMPLETED,
        GAME_VICTORY,
        QUIT
    }


    enum Music {
        MENU_BACKGROUND,
        ARENA_BACKGROUND
    }

    static SoundManager getInstance() {
        return GlobalSoundManager.getInstance();
    }


    void playSFX(SFX sfx);

    void playMusic(Music music);
}

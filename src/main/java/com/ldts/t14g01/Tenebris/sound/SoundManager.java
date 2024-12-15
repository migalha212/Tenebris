package com.ldts.t14g01.Tenebris.sound;

public interface SoundManager {

    enum SFX {
        // Player
        ENTITY_DAMAGE,
        ENTITY_DEATH,
        SHOT,

        // Menu
        MENU_SWITCH

        // Arena
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

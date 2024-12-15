package com.ldts.t14g01.Tenebris.sound;

public interface SoundManager {

    public enum SFX{
        // player
        playerDamage,
        playerDeath,
        shot,

        // menu
        menuSwitch

        // arena

    }


    public enum Music{
        // menu
        menuBackground,

        // arena
        arenaBackground
    }


    public void playSFX(SFX sfx);
    public void playMusic(Music music);
    public void stopMusic(Music music);
}

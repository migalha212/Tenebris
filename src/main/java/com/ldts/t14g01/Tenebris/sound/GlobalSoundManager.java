package com.ldts.t14g01.Tenebris.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class GlobalSoundManager implements SoundManager {
    private static GlobalSoundManager instance;

    private final File shot;
    private final File entityDamage;
    private final File dylanDeath;

    private final File menuSwitch;

    private final Clip menuBackgroundMusic;
    private final Clip arenaBackgroundMusic;

    private GlobalSoundManager() {
        // Entity SFX
        this.shot = new File("src/main/resources/sfx/shot.wav");
        this.entityDamage = new File("src/main/resources/sfx/entity_damage.wav");
        this.dylanDeath = new File("src/main/resources/sfx/dylan_death.wav");

        // Menu SFX
        this.menuSwitch = new File("src/main/resources/sfx/menu_switch.wav");

        // Music
        this.arenaBackgroundMusic = this.open(new File("src/main/resources/music/arena_music.wav"));
        this.menuBackgroundMusic = this.open(new File("src/main/resources/music/menu_music.wav"));
        ((FloatControl) this.menuBackgroundMusic.getControl(FloatControl.Type.MASTER_GAIN)).setValue(-15.0f);
        ((FloatControl) this.arenaBackgroundMusic.getControl(FloatControl.Type.MASTER_GAIN)).setValue(-10.0f);
    }

    protected static GlobalSoundManager getInstance() {
        if (instance == null) instance = new GlobalSoundManager();
        return instance;
    }

    private Clip open(File file) {
        Clip sound;
        try {
            sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(file));
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
        return sound;
    }

    @Override
    public void playSFX(SFX sfx) {
        Clip sfxClip;
        switch (sfx) {
            case ENTITY_DAMAGE -> {
                sfxClip = this.open(this.entityDamage);
                ((FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(-10.0f);
            }
            case ENTITY_DEATH -> sfxClip = this.open(this.dylanDeath);
            case MENU_SWITCH -> {
                sfxClip = this.open(this.menuSwitch);
                ((FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(-10.0f);
            }
            case null, default -> throw new RuntimeException("Trying to Play non existent SFX");
        }

        if (sfxClip.isRunning()) sfxClip.stop();
        sfxClip.setFramePosition(0);
        sfxClip.start();
    }

    @Override
    public void playMusic(Music music) {
        this.stopMusic(music);
        Clip musicClip;
        switch (music) {
            case MENU_BACKGROUND -> musicClip = this.menuBackgroundMusic;
            case ARENA_BACKGROUND -> musicClip = this.arenaBackgroundMusic;
            case null, default -> throw new RuntimeException("Trying to Play non existent Music");
        }

        if (musicClip.isRunning()) return;
        musicClip.setFramePosition(0);
        musicClip.start();
        musicClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    private void stopMusic(Music music) {
        if (this.arenaBackgroundMusic.isRunning() && music != Music.ARENA_BACKGROUND) this.arenaBackgroundMusic.stop();
        if (this.menuBackgroundMusic.isRunning() && music != Music.MENU_BACKGROUND) this.menuBackgroundMusic.stop();
    }
}

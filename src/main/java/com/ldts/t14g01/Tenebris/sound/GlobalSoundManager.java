package com.ldts.t14g01.Tenebris.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class GlobalSoundManager implements SoundManager {
    private static GlobalSoundManager instance;

    private final File shoot;
    private final File grenade;
    private final File explosion;
    private final File spellCollision;
    private final File spellFire;
    private final File entityDamage;
    private final File dylanDeath;
    private final File pistolReload;
    private final File grenadeReload;

    private final File menuSwitch;
    private final File menuSelect;
    private final File menuGoBack;
    private final File levelCompleted;
    private final File victory;
    private final File quit;

    private final Clip menuBackgroundMusic;
    private final Clip arenaBackgroundMusic;

    private GlobalSoundManager() {
        // Entity SFX
        this.shoot = new File("src/main/resources/sfx/shoot.wav");
        this.grenade = new File("src/main/resources/sfx/grenade.wav");
        this.explosion = new File("src/main/resources/sfx/explosion.wav");
        this.spellFire = new File("src/main/resources/sfx/spell-fire.wav");
        this.spellCollision = new File("src/main/resources/sfx/spell-collision.wav");
        this.entityDamage = new File("src/main/resources/sfx/entity_damage.wav");
        this.dylanDeath = new File("src/main/resources/sfx/dylan_death.wav");
        this.pistolReload = new File("src/main/resources/sfx/pistol_reload.wav");
        this.grenadeReload = new File("src/main/resources/sfx/grenade_launcher_reload.wav");

        // Menu SFX
        this.menuSwitch = new File("src/main/resources/sfx/menu_switch.wav");
        this.menuSelect = new File("src/main/resources/sfx/menu_select.wav");
        this.menuGoBack = new File("src/main/resources/sfx/menu_go_back.wav");
        this.levelCompleted = new File("src/main/resources/sfx/level_completed.wav");
        this.victory = new File("src/main/resources/sfx/victory.wav");
        this.quit = new File("src/main/resources/sfx/quit.wav");

        // Music
        this.arenaBackgroundMusic = this.open(new File("src/main/resources/music/arena_music.wav"));
        this.menuBackgroundMusic = this.open(new File("src/main/resources/music/menu_music.wav"));
        ((FloatControl) this.menuBackgroundMusic.getControl(FloatControl.Type.MASTER_GAIN)).setValue(-10.0f);
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
                ((FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0.0f);
            }
            case DYLAN_DEATH -> {
                sfxClip = this.open(this.dylanDeath);
                ((FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0.0f);
            }
            case MENU_SWITCH -> {
                sfxClip = this.open(this.menuSwitch);
                ((FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0.0f);
            }
            case SHOOT -> {
                sfxClip = this.open(this.shoot);
                ((FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0.0f);
            }
            case GRENADE -> {
                sfxClip = this.open(this.grenade);
                ((FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0.0f);
            }
            case EXPLOSION -> {
                sfxClip = this.open(this.explosion);
                ((FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0.0f);
            }
            case SPELL_FIRE -> {
                sfxClip = this.open(this.spellFire);
                ((FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0.0f);
            }
            case SPELL_COLLISION -> {
                sfxClip = this.open(this.spellCollision);
                ((FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0.0f);
            }
            case PISTOL_RELOAD -> {
                sfxClip = this.open(this.pistolReload);
                ((FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0.0f);
            }
            case GRENADE_RELOAD -> {
                sfxClip = this.open(this.grenadeReload);
                ((FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0.0f);
            }
            case MENU_SELECT -> {
                sfxClip = this.open(this.menuSelect);
                ((FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0.0f);
            }
            case GAME_VICTORY -> {
                sfxClip = this.open(this.victory);
                ((FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0.0f);
            }
            case LEVEL_COMPLETED -> {
                sfxClip = this.open(this.levelCompleted);
                ((FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0.0f);
            }
            case MENU_GO_BACK -> {
                sfxClip = this.open(this.menuGoBack);
                ((FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0.0f);
            }
            case QUIT -> {
                sfxClip = this.open(this.quit);
                ((FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0.0f);
            }

            case null, default -> throw new RuntimeException("Trying to Play non existent SFX");
        }

        if (sfxClip.isRunning()) sfxClip.stop();
        sfxClip.setFramePosition(0);
        sfxClip.start();
        sfxClip.addLineListener(event -> {
            if (event.getType() == LineEvent.Type.STOP) sfxClip.close();
        });
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
        //musicClip.setFramePosition(0);
        musicClip.start();
        musicClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    private void stopMusic(Music music) {
        if (this.arenaBackgroundMusic.isRunning() && music != Music.ARENA_BACKGROUND)
            this.arenaBackgroundMusic.stop();
        if (this.menuBackgroundMusic.isRunning() && music != Music.MENU_BACKGROUND) this.menuBackgroundMusic.stop();
    }
}

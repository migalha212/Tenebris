package com.ldts.t14g01.Tenebris.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GlobalMusicManager {
    private static GlobalMusicManager instance;

    private Clip playerDamage;
    private Clip playerDeath;
    private Clip shot;
    private Clip menuBackgroundMusic;
    private Clip menuSwitch;


    private GlobalMusicManager() {
        try {
            // Player SFX
            this.playerDamage = open(new File("src/main/resources/sfx/player_damage.wav"));
            this.playerDeath = open(new File("src/main/resources/sfx/player_death.wav"));
            this.shot = open(new File("src/main/resources/sfx/shot.wav"));

            // Menu SFX
            this.menuSwitch = open(new File("src/main/resources/sfx/menu_switch.wav"));
            this.menuBackgroundMusic = open(new File("src/main/resources/music/menu_music.wav"));

            // Arena SFX

        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException("Unable to load Sound File Type");
        } catch (IOException e) {
            throw new RuntimeException("Unable to open file");
        } catch (LineUnavailableException e) {
            throw new RuntimeException("lineUnavailable");
        }
    }

    public static GlobalMusicManager getInstance() {
        if (instance == null) instance = new GlobalMusicManager();
        return instance;
    }

    private Clip open(File file) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        Clip music = AudioSystem.getClip();
        music.open(AudioSystem.getAudioInputStream(file));
        return music;
    }

    // menu related sfx and music
    public void playMenuBackground(){
        if(menuBackgroundMusic.isRunning()) return;
        menuBackgroundMusic.setFramePosition(0);
        menuBackgroundMusic.start();
        menuBackgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopMenuBackground(){
        if (!menuBackgroundMusic.isRunning()) return;
        menuBackgroundMusic.stop();
    }

    public void playMenuSwitch() {
        menuSwitch.setFramePosition(0);
        menuSwitch.start();
    }
}

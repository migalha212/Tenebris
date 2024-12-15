package com.ldts.t14g01.Tenebris.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class GlobalSoundManager implements SoundManager{
    private static GlobalSoundManager instance;

    private Clip playerDamage;
    private Clip playerDeath;
    private Clip shot;
    private Clip menuBackgroundMusic;
    private Clip menuSwitch;

    private GlobalSoundManager() {
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

    public static GlobalSoundManager getInstance() {
        if (instance == null) instance = new GlobalSoundManager();
        return instance;
    }

    private Clip open(File file) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        Clip music = AudioSystem.getClip();
        music.open(AudioSystem.getAudioInputStream(file));
        return music;
    }


    @Override
    public void playSFX(SFX sfx){
        Clip temp = null;
        switch (sfx){
            // player SFX
            case playerDamage -> temp = this.playerDamage;
            case playerDeath -> temp = this.playerDeath;
            case shot -> temp = this.shot;

            // menu SFX
            case menuSwitch -> temp = this.menuSwitch;
        }

        assert temp != null;
        //reset its placement
        temp.setFramePosition(0);
        temp.start();
    }

    @Override
    public void playMusic(Music music){
        Clip temp = null;
        switch (music){
            case Music.menuBackground -> temp = this.menuBackgroundMusic;
            case arenaBackground -> temp = null;
        }

        assert temp != null;
        if(temp.isRunning()) return;
        temp.setFramePosition(0);
        temp.start();
        temp.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    public void stopMusic(Music music){
        Clip temp = null;
        switch (music){
            case Music.menuBackground -> temp = this.menuBackgroundMusic;
            case arenaBackground -> temp = null;
        }

        assert temp != null;
        if(!temp.isRunning()) return;
        temp.stop();
    }
}

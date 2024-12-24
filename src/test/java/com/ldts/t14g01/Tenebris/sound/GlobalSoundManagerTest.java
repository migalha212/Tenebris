package com.ldts.t14g01.Tenebris.sound;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.Clip;
import java.util.List;

public class GlobalSoundManagerTest {
    @Test
    void Test1() {
        Assertions.assertSame(GlobalSoundManager.getInstance(), GlobalSoundManager.getInstance());
    }

    @Test
    void Test2() throws InterruptedException {
        GlobalSoundManager globalSoundManager = GlobalSoundManager.getInstance();
        List<Clip> musics = globalSoundManager.getMusics();
        for (Clip music : musics) Assertions.assertFalse(music.isRunning());


        globalSoundManager.playMusic(SoundManager.Music.MENU_BACKGROUND);
        Thread.sleep(10);
        Assertions.assertTrue(musics.getFirst().isRunning());
        Assertions.assertFalse(musics.getLast().isRunning());

        globalSoundManager.playMusic(SoundManager.Music.MENU_BACKGROUND);
        Thread.sleep(10);
        Assertions.assertTrue(musics.getFirst().isRunning());
        Assertions.assertFalse(musics.getLast().isRunning());

        globalSoundManager.playMusic(SoundManager.Music.ARENA_BACKGROUND);
        Thread.sleep(10);
        Assertions.assertFalse(musics.getFirst().isRunning());
        Assertions.assertTrue(musics.getLast().isRunning());

        globalSoundManager.playMusic(SoundManager.Music.ARENA_BACKGROUND);
        Thread.sleep(10);
        Assertions.assertFalse(musics.getFirst().isRunning());
        Assertions.assertTrue(musics.getLast().isRunning());

        globalSoundManager.playMusic(SoundManager.Music.MENU_BACKGROUND);
        Thread.sleep(10);
        Assertions.assertTrue(musics.getFirst().isRunning());
        Assertions.assertFalse(musics.getLast().isRunning());

        Assertions.assertThrows(RuntimeException.class, () -> globalSoundManager.playMusic(null));
    }

    @Test
    void Test3() throws InterruptedException {
        GlobalSoundManager globalSoundManager = GlobalSoundManager.getInstance();

        Clip clip;
        Clip lastClip = null;
        for (SoundManager.SFX sfx : SoundManager.SFX.values()) {
            globalSoundManager.playSFX(sfx);
            clip = globalSoundManager.getLastPlayedClip();
            Assertions.assertNotNull(clip);

            Thread.sleep(10);
            Assertions.assertTrue(clip.isRunning());

            Assertions.assertNotEquals(lastClip, clip);
            lastClip = clip;
        }

        Assertions.assertThrows(RuntimeException.class, () -> globalSoundManager.playSFX(null));
    }
}

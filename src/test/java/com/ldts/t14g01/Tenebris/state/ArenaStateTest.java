package com.ldts.t14g01.Tenebris.state;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.controller.arena.ArenaController;
import com.ldts.t14g01.Tenebris.model.arena.Arena;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import com.ldts.t14g01.Tenebris.view.View;
import com.ldts.t14g01.Tenebris.view.arena.ArenaView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class ArenaStateTest {
    @Test
    void Test1() {
        Arena arena = Mockito.mock(Arena.class);

        try (MockedStatic<SoundManager> soundManagerMockedStatic = Mockito.mockStatic(SoundManager.class)) {
            SoundManager soundManager = Mockito.mock(SoundManager.class);
            soundManagerMockedStatic.when(SoundManager::getInstance).thenReturn(soundManager);

            State state = new ArenaState(arena);
            Mockito.verify(soundManager, Mockito.times(1)).playMusic(SoundManager.Music.ARENA_BACKGROUND);

            Assertions.assertTrue(state.getView() instanceof ArenaView);
            Assertions.assertTrue(state.getController() instanceof ArenaController);

            View arenaView = state.getView();
            Controller arenaController = state.getController();
            Assertions.assertSame(arena, arenaView.getModel());
            Assertions.assertSame(arena, arenaController.getModel());

        }
    }
}

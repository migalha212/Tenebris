package com.ldts.t14g01.Tenebris.sound;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class SoundManagerTest {
    @Test
    void Test1() {
        try (MockedStatic<GlobalSoundManager> globalSoundManagerMockedStatic = Mockito.mockStatic(GlobalSoundManager.class)) {
            GlobalSoundManager globalSoundManager = Mockito.mock(GlobalSoundManager.class);
            globalSoundManagerMockedStatic.when(GlobalSoundManager::getInstance).thenReturn(globalSoundManager);
            Assertions.assertSame(globalSoundManager, SoundManager.getInstance());
        }
    }
}

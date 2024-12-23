package com.ldts.t14g01.Tenebris.state;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.model.menu.Menu;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import com.ldts.t14g01.Tenebris.view.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class MenuStateTest {
    @Test
    void Test1() {
        Menu menu = Mockito.mock(Menu.class);
        View view = Mockito.mock(View.class);
        Controller controller = Mockito.mock(Controller.class);

        Mockito.when(menu.getView()).thenReturn(view);
        Mockito.when(menu.getController()).thenReturn(controller);

        try (MockedStatic<SoundManager> soundManagerMockedStatic = Mockito.mockStatic(SoundManager.class)) {
            SoundManager soundManager = Mockito.mock(SoundManager.class);
            soundManagerMockedStatic.when(SoundManager::getInstance).thenReturn(soundManager);

            State state = new MenuState(menu);
            Mockito.verify(soundManager, Mockito.times(1)).playMusic(SoundManager.Music.MENU_BACKGROUND);

            Assertions.assertSame(view, state.getView());
            Assertions.assertSame(controller, state.getController());
        }
    }
}

package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.controller.Controller;
import com.ldts.t14g01.Tenebris.sound.SoundManager;
import com.ldts.t14g01.Tenebris.view.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.mockito.Mockito.*;

public class MenuTest {
    private Menu menu;

    @BeforeEach
    void setUp() {
        this.menu = new Menu() {
            @Override
            public List<String> getOptions() {
                return menu.options;
            }

            @Override
            public View<Menu> getView() {
                return null;
            }

            @Override
            public Controller<Menu> getController() {
                return null;
            }

        };

        this.menu.options.add("1");
        this.menu.options.add("2");
        this.menu.options.add("3");
    }

    @Test
    void EmptyMoveTest() {
        this.menu.options.clear();
        this.menu.moveDown();
        Assertions.assertEquals(0, this.menu.getSelectedOption());
        this.menu.moveUp();
        Assertions.assertEquals(0, this.menu.getSelectedOption());
    }

    @Test
    void MoveDownTest() {
        this.menu.moveDown();
        Assertions.assertEquals(1, this.menu.getSelectedOption());
        this.menu.moveDown();
        this.menu.moveDown();
        Assertions.assertEquals(0, this.menu.getSelectedOption());
    }

    @Test
    void MoveUpTest() {
        menu.moveUp();
        Assertions.assertEquals(2, this.menu.getSelectedOption());
        menu.moveUp();
        menu.moveUp();
        Assertions.assertEquals(0, this.menu.selectedOption);
    }

    @Test
    void soundCallsTest() {
        try (MockedStatic<SoundManager> mockedSoundManager = mockStatic(SoundManager.class)) {
            SoundManager mockSoundManager = mock(SoundManager.class);
            mockedSoundManager.when(SoundManager::getInstance).thenReturn(mockSoundManager);

            this.menu.moveUp();
            verify(mockSoundManager, times(1)).playSFX(SoundManager.SFX.MENU_SWITCH);
            this.menu.moveDown();
            verify(mockSoundManager, times(2)).playSFX(SoundManager.SFX.MENU_SWITCH);
        }
    }
}

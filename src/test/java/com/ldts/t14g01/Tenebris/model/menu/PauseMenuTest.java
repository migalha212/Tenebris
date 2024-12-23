package com.ldts.t14g01.Tenebris.model.menu;

import com.ldts.t14g01.Tenebris.model.arena.Arena;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PauseMenuTest {
    private PauseMenu pauseMenu;
    private Arena arena;

    @BeforeEach
    void setUp() throws IOException {
        this.arena = new Arena();
        this.pauseMenu = new PauseMenu(arena);
    }

    @Test
    void GetTests() {
        Assertions.assertEquals(this.pauseMenu, this.pauseMenu.getView().getModel());
        Assertions.assertEquals(this.pauseMenu, this.pauseMenu.getController().getModel());
        Assertions.assertEquals(this.arena, this.pauseMenu.getArena());
    }
}
